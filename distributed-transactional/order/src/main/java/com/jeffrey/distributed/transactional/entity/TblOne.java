package com.jeffrey.distributed.transactional.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * tbl_one
 * @author
 */
@Data
public class TblOne implements Serializable {
    private Integer id;

    private String name;

    private static final long serialVersionUID = 1L;
}
