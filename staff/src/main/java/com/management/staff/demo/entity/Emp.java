package com.management.staff.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Emp)实体类
 *
 * @author makejava
 * @since 2023-07-19 20:01:14
 */
@Data
public class Emp implements Serializable {
    private static final long serialVersionUID = 915822510089423867L;

    private int num;
    /**
    * 职工ID
    */
    private String id;
    /**
    * 职工姓名
    */
    private String name;
    /**
    * 性别 0 女 1 男
    */
    private String sex;
    /**
    * 职工年龄
    */
    private Integer age;
    /**
    * 部门
    */
    private String empDeptName;
    /**
     * 部门代码
     */
    private String empDeptCode;
    /**
    * 学历
    */
    private String empDegreeName;
    /**
     * 学历代码
     */
    private String empDegreeCode;


}