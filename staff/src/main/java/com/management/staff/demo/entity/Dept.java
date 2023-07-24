package com.management.staff.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Dept)实体类
 *
 * @author makejava
 * @since 2023-07-19 20:01:14
 */
@Data
public class Dept implements Serializable {
    private static final long serialVersionUID = -32341432325231708L;
    /**
    * 部门id
    */
    private Integer deptCode;
    /**
    * 部门名称
    */
    private String deptName;

}