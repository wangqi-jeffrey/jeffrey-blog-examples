package com.jeffrey.distributed.transactional.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * tbl_two
 * @author
 */
@Data
public class TblTwo implements Serializable {
    private Integer id;

    private String name;

    private static final long serialVersionUID = 1L;
}
