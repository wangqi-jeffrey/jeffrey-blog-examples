package com.jeffrey.blog.examples.model;

import java.util.Date;

/**
 * Description:
 *
 * @author WQ
 * @date 2020/12/23 9:33 AM
 */
public class IdBuilderPO {
    private Integer id;

    private String des;

    private Long initNum;

    private Long currentThreshold;

    private Integer step;

    private String bizCode;

    private Integer version;

    private Integer isSeq;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Long getInitNum() {
        return initNum;
    }

    public void setInitNum(Long initNum) {
        this.initNum = initNum;
    }

    public Long getCurrentThreshold() {
        return currentThreshold;
    }

    public void setCurrentThreshold(Long currentThreshold) {
        this.currentThreshold = currentThreshold;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getIsSeq() {
        return isSeq;
    }

    public void setIsSeq(Integer isSeq) {
        this.isSeq = isSeq;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}