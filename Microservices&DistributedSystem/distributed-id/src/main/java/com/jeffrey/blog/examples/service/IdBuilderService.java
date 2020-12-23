package com.jeffrey.blog.examples.service;

public interface IdBuilderService {
    /**
     * 根据本地步长度来生成唯一id(区间性递增)
     *
     * @return
     */
    Long unionId(int code);

    /**
     * 对于unionId的算法进行优化(连续性递增)
     *
     * @param code
     * @return
     */
    Long unionSeqId(int code);

    /**
     * 生成包含业务前缀的自增id(区间性递增)
     *
     * @param code
     * @return
     */
    String unionIdStr(int code);

    /**
     * 生成包含业务前缀的自增id(连续性递增)
     *
     * @param code
     * @return
     */
    String unionSeqIdStr(int code);
}