package com.management.staff.demo.controller;

import com.management.staff.demo.entity.Permission;
import com.management.staff.demo.service.PermissionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysPermission)表控制层
 *
 * @author makejava
 * @since 2023-07-21 01:28:50
 */
@RestController
@Api(tags = "权限信息")
@RequestMapping("sysPermission")
public class PermissionController {
    /**
     * 服务对象
     */
    @Resource
    private PermissionService permissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("selectOne")
//    public Permission selectOne(Long id) {
//        return this.permissionService.queryById(id);
//    }

}