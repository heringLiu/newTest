package com.management.staff.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {

    private Long id;
    private String name;
    private String avatar;
    private String introduction;
    private Object[] roles;

}
