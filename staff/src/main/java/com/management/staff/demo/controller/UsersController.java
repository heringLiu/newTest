package com.management.staff.demo.controller;

import com.management.staff.demo.common.entity.ResultWrapper;
import com.management.staff.demo.entity.Permission;
import com.management.staff.demo.entity.UserInfo;
import com.management.staff.demo.entity.Users;
import com.management.staff.demo.service.UsersService;
import com.management.staff.demo.utils.MenuTree;
import com.management.staff.demo.vo.RouterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * (Users)表控制层
 *
 * @author makejava
 * @since 2023-07-19 20:01:14
 */
@RestController
@RequestMapping("users")
@Api(tags = "用户管理")
public class UsersController {
    /**
     * 服务对象
     */
    @Resource
    private UsersService usersService;

    /**
     * 获取用户信息
     *
     * @param
     * @return 单条数据
     */
    @GetMapping("getInfo")
    @ApiOperation(value = "获取用户信息")
    public ResultWrapper getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return ResultWrapper.error().message("获取用户信息失败");
        }
        Users user = (Users) authentication.getPrincipal();
        List<Permission> permissionList = user.getPermissionList();
        Object[] roles = permissionList.stream().filter(Objects::nonNull).map(Permission::getCode).toArray();
        UserInfo userInfo = new UserInfo(user.getId(), user.getUsername(), null, null, roles);
        return ResultWrapper.success(userInfo);
    }

    /**
     * 获取用户菜单
     *
     * @param
     * @return 单条数据
     */
    @ApiOperation(value = "获取菜单信息")
    @GetMapping("getMenuList")
    public ResultWrapper getMenuList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return ResultWrapper.error().message("获取用户信息失败");
        }
        Users user = (Users) authentication.getPrincipal();
        List<Permission> permissionList = user.getPermissionList();
        List<Permission> collect = permissionList.stream()
                .filter(item -> item != null && item.getType() != 2)
                .collect(Collectors.toList());

        List<RouterVo> routerVoList = MenuTree.makeRouter(collect, 0L);
        return ResultWrapper.success(routerVoList).message("菜单数据获取成功");

    }



}