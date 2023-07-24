package com.management.staff.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * (Users)实体类
 *
 * @author makejava
 * @since 2023-07-19 20:01:14
 */
@Data
public class Users implements Serializable,UserDetails {
    private static final long serialVersionUID = -78681432770167328L;
    /**
     * 用户主键
     */
   private Long id;
    /**
    * 用户账号
    */
    private String userId;
    /**
    * 用户密码
    */
    private String userPwd;
    /**
    * 用户名称
    */
    private String userName;


    private boolean isAccountNonExpired = true;

    private boolean isAccountNonLocked = true;

    private boolean isCredentialsNonExpired = true;

    private boolean isEnabled = true;

    @TableField(exist = false)
    Collection<? extends  GrantedAuthority> authorities;

    /*
    * 权限列表
    * */
    @TableField(exist = false)
    private List<Permission> permissionList;

    /*
    * 权限列表
    * */
    @TableField(exist = false)
    Collection<? extends GrantedAuthority> getAuthorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.userPwd;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }
}