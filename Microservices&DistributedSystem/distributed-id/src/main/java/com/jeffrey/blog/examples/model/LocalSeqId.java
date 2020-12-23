package com.jeffrey.blog.examples.model;

/**
 * Description:
 *
 * @author WQ
 * @date 2020/12/23 9:35 AM
 */
public class LocalSeqId {

    private Long nextUpdateId;

    private Long currentId;

    public Long getNextUpdateId() {
        return nextUpdateId;
    }

    public void setNextUpdateId(Long nextUpdateId) {
        this.nextUpdateId = nextUpdateId;
    }

    public Long getCurrentId() {
        return currentId;
    }

    public void setCurrentId(Long currentId) {
        this.currentId = currentId;
    }
}