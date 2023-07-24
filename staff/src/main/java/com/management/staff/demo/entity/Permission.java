package com.management.staff.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * (SysPermission)实体类
 *
 * @author makejava
 * @since 2023-07-21 01:26:13
 */
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = -67060669402940399L;
    /**
    * 权限编号
    */
    private Long id;
    /**
    * 权限名称
    */
    private String label;
    /**
    * 父权限ID
    */
    private Long parentId;
    /**
    * 父权限名称
    */
    private String parentName;
    /**
    * 授权标识符
    */
    private String code;
    /**
    * 路由地址
    */
    private String path;
    /**
    * 路由名称
    */
    private String name;
    /**
    * 授权路径
    */
    private String url;
    /**
    * 权限类型(0-目录 1-菜单 2-按钮)
    */
    private int type;
    /**
    * 图标
    */
    private String icon;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 修改时间
    */
    private Date updateTime;
    /**
    * 备注
    */
    private String remark;
    /**
    * 排序
    */
    private Integer orderNum;
    /**
    * 是否删除(0-未删除，1-已删除)
    */
    private int isDelete;

    /**
    * 子菜单列表
    * */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
   private List<Permission> children = new ArrayList<Permission>();
    /**
     * 菜单还是按钮
     * */
    @TableField(exist = false)
    private String value;

    /**
     * 是否展开
     * */
    private boolean open;
}