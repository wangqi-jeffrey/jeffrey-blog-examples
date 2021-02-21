package com.jeffrey.blog.examples.service;

import com.jeffrey.blog.examples.mapper.IdBuilderMapper;
import com.jeffrey.blog.examples.model.IdBuilderPO;
import com.jeffrey.blog.examples.model.LocalSeqId;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@Service
public class IdBuilderServiceImpl implements IdBuilderService, InitializingBean {
    private static ConcurrentHashMap<Integer, BitSet> bitSetMap = new ConcurrentHashMap<>();
    private static Map<Integer, IdBuilderPO> idBuilderNotSeqMap;
    private static Map<Integer, IdBuilderPO> idBuilderSeqMap;
    private static Map<Integer, LocalSeqId> localSeqMap;
    private static Map<Integer, Boolean> newBuilderMap;
    private final static Object monitor = new Object();
    private final static Integer NEED_SEQ = 0; // 递增

    @Resource
    private IdBuilderMapper idBuilderMapper;

    private int idBuilderIndex;

    @Override
    public Long unionId(int code) {
        //考虑到锁升级问题，在高并发场景下使用synchronized要比cas更佳
        synchronized (monitor) {
            IdBuilderPO idBuilderPO = idBuilderNotSeqMap.get(code);
            if (idBuilderPO == null) {
                return null;
            }
            boolean isNew = newBuilderMap.get(code);
            if (isNew) {
                //预防出现id生成器网络中断问题
                IdBuilderPO newIdBuilderPO = this.refreshIdBuilderConfig(idBuilderPO);
                if (newIdBuilderPO == null) {
                    log.error("[unionId] refreshIdBuilderConfig出现异常");
                    return null;
                }
                idBuilderPO.setCurrentThreshold(newIdBuilderPO.getCurrentThreshold());
                newBuilderMap.put(code, false);
            }
            long initNum = idBuilderPO.getCurrentThreshold();
            int step = idBuilderPO.getStep();
            int randomIndex = RandomUtils.nextInt((int) initNum, (int) initNum + step);
            BitSet bitSet = bitSetMap.get(code);
            if (bitSet == null) {
                bitSet = new BitSet();
                bitSetMap.put(code, bitSet);
            }
            Long id;
            int countTime = 0;
            while (true) {
                boolean indexExist = bitSet.get(randomIndex);
                countTime++;
                if (!indexExist) {
                    bitSet.set(randomIndex);
                    id = Long.valueOf(randomIndex);
                    break;
                }
                //如果重试次数大于了空间的0.75则需要重新获取新的id区间 测试之后得出 循环一千万次随机函数，16gb内存条件下，大约耗时在124ms左右
                if (countTime >= step * 0.75) {
                    //扩容需要修改表配置
                    IdBuilderPO newIdBuilderPO = this.updateIdBuilderConfig(idBuilderPO);
                    if (newIdBuilderPO == null) {
                        log.error("重试超过100次没有更新自增id配置成功");
                        return null;
                    }
                    initNum = newIdBuilderPO.getCurrentThreshold();
                    step = newIdBuilderPO.getStep();
                    idBuilderPO.setCurrentThreshold(initNum);
                    bitSet.clear();
                    log.info("[unionId] 扩容IdBuilder，new idBuilderPO is {}", idBuilderPO);
                }
                randomIndex = RandomUtils.nextInt((int) initNum, (int) initNum + step);
            }
            return id;
        }
    }

    @Override
    public Long unionSeqId(int code) {
        synchronized (monitor) {
            LocalSeqId localSeqId = localSeqMap.get(code);
            IdBuilderPO idBuilderPO = idBuilderSeqMap.get(code);
            if (idBuilderPO == null || localSeqId == null) {
                log.error("[unionSeqId] code 参数有误，code is {}", code);
                return null;
            }
            boolean isNew = newBuilderMap.get(code);
            long result = localSeqId.getCurrentId();
            localSeqId.setCurrentId(result + 1);
            if (isNew) {
                //预防出现id生成器网络中断问题
                IdBuilderPO updateIdBuilderPO = this.refreshIdBuilderConfig(idBuilderPO);
                if (updateIdBuilderPO == null) {
                    log.error("[unionSeqId] refreshIdBuilderConfig出现异常");
                    return null;
                }
                newBuilderMap.put(code, false);
                localSeqId.setCurrentId(updateIdBuilderPO.getCurrentThreshold());
                localSeqId.setNextUpdateId(updateIdBuilderPO.getCurrentThreshold() + updateIdBuilderPO.getStep());
            }
            //需要更新本地步长
            if (localSeqId.getCurrentId() >= localSeqId.getNextUpdateId()) {
                IdBuilderPO newIdBuilderPO = this.updateIdBuilderConfig(idBuilderPO);
                if (newIdBuilderPO == null) {
                    log.error("[unionSeqId] updateIdBuilderConfig出现异常");
                    return null;
                }
                idBuilderPO.setCurrentThreshold(newIdBuilderPO.getCurrentThreshold());
                localSeqId.setCurrentId(newIdBuilderPO.getCurrentThreshold());
                localSeqId.setNextUpdateId(newIdBuilderPO.getCurrentThreshold() + newIdBuilderPO.getStep());
                log.info("[unionSeqId] 扩容IdBuilder，new localSeqId is {}", localSeqId);
            }
            return result;
        }
    }

    /**
     * 刷新id生成器的配置
     *
     * @param idBuilderPO
     */
    private IdBuilderPO refreshIdBuilderConfig(IdBuilderPO idBuilderPO) {
        IdBuilderPO updateResult = this.updateIdBuilderConfig(idBuilderPO);
        if (updateResult == null) {
            log.error("更新数据库配置出现异常,idBuilderPO is {}", idBuilderPO);
            throw new RuntimeErrorException(new Error("更新数据库配置出现异常,idBuilderPO is " + idBuilderPO.toString()));
        }
        return updateResult;
    }

    /**
     * 考虑分布式环境下 多个请求同时更新同一行数据的情况
     *
     * @param idBuilderPO
     * @return
     */
    private IdBuilderPO updateIdBuilderConfig(IdBuilderPO idBuilderPO) {
        int updateResult = -1;
        //假设重试过程中出现网络异常，那么使用cas的时候必须要考虑退出情况 极限情况下更新100次
        for (int i = 0; i < 100; i++) {
            IdBuilderPO newIdBuilderPO = idBuilderMapper.selectOneForUpdate(idBuilderPO.getId());
            updateResult = idBuilderMapper.updateCurrentThreshold(newIdBuilderPO.getCurrentThreshold() + newIdBuilderPO.getStep(), newIdBuilderPO.getId(), newIdBuilderPO.getVersion());
            if (updateResult > 0) {
                return newIdBuilderPO;
            }
        }
        return null;
    }

    @Override
    public String unionIdStr(int code) {
        Long id = this.unionId(code);
        if (id == null)
            return null;

        IdBuilderPO idBuilderPO = idBuilderNotSeqMap.get(code);
        return idBuilderPO.getBizCode() + idBuilderIndex + id;
    }

    @Override
    public String unionSeqIdStr(int code) {
        Long id = this.unionSeqId(code);
        if (id == null)
            return null;

        IdBuilderPO idBuilderPO = idBuilderSeqMap.get(code);
        return idBuilderPO.getBizCode() + idBuilderIndex + id;
    }

    @Override
    public void afterPropertiesSet() {
        List<IdBuilderPO> idBuilderPOS = idBuilderMapper.selectAll();
        idBuilderNotSeqMap = new ConcurrentHashMap<>(idBuilderPOS.size());
        newBuilderMap = new ConcurrentHashMap<>(idBuilderPOS.size());
        idBuilderSeqMap = new ConcurrentHashMap<>(idBuilderPOS.size());
        localSeqMap = new ConcurrentHashMap<>(0);
        // 每次重启到时候，都需要将之前的上一个区间的id全部抛弃，使用新的步长区间
        for (IdBuilderPO idBuilderPO : idBuilderPOS) {
            if (idBuilderPO.getIsSeq() == NEED_SEQ) {
                idBuilderSeqMap.put(idBuilderPO.getId(), idBuilderPO);
                LocalSeqId localSeqId = new LocalSeqId();
                localSeqId.setNextUpdateId(idBuilderPO.getCurrentThreshold() + idBuilderPO.getStep());
                localSeqId.setCurrentId(idBuilderPO.getCurrentThreshold());
                localSeqMap.put(idBuilderPO.getId(), localSeqId);
            } else {
                idBuilderNotSeqMap.put(idBuilderPO.getId(), idBuilderPO);
            }
            newBuilderMap.put(idBuilderPO.getId(), true);
        }
        this.idBuilderIndex = Integer.parseInt(System.getProperty("idBuilder.index"));
    }

}
