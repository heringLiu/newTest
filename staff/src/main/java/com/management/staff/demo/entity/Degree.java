package com.management.staff.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Degree)实体类
 *
 * @author makejava
 * @since 2023-07-19 20:01:06
 */
@Data
public class Degree implements Serializable {
    private static final long serialVersionUID = 816110820734550369L;
    /**
    * 学历id
    */
    private Integer empDegreeCode;
    /**
    * 学历名称
    */
    private String empDegreeName;


}