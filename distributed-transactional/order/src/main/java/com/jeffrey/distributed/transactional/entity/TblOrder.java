package com.jeffrey.distributed.transactional.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tbl_order
 * @author
 */
@Data
public class TblOrder implements Serializable {
    private Integer orderId;

    private String buyer;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
