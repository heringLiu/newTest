package com.management.staff.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult {
    private Long id;
    private int code;
    private String token;
    private Long expireTime;
}
