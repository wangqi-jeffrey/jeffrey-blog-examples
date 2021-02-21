package com.jeffrey.distributed.lock.order.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * tbl_order
 * @author
 */
@Data
public class TblOrder implements Serializable {
    private Integer orderId;

    private Integer orderStatus;

    private String orderDescription;

    private Integer driverId;

    private static final long serialVersionUID = 1L;
}
