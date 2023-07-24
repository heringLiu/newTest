package com.management.staff.demo.utils;

import com.management.staff.demo.entity.Permission;
import com.management.staff.demo.vo.RouterVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MenuTree {

    // 生成路由
    public static List<RouterVo> makeRouter(List<Permission> meuList, Long pid) {
        List<RouterVo> routerList = new ArrayList<RouterVo>();
        Optional.ofNullable(meuList).orElse(new ArrayList<Permission>())
                .stream().filter(item -> item != null && item.getParentId() == pid)
                .forEach(item -> {
                    RouterVo router = new RouterVo();
                    router.setName(item.getName());
                    router.setPath(item.getPath());
                    if (item.getParentId() == 0L) {
                        router.setComponent("Layout");
                        router.setAlwaysShow(true);
                    } else {
                        router.setComponent(item.getUrl());
                        router.setAlwaysShow(false);
                    }
                    router.setMeta(router.new Meta(item.getLabel(), item.getIcon(), item.getCode().split(",")));
                    List<RouterVo> children = makeRouter(meuList, item.getId());
                    router.setChildren(children);
                    routerList.add(router);
                });
        return routerList;
    }
    public static List<Permission> makeMenuTree(List<Permission> meuList, Long pid) {
        //创建
        List<Permission> permissionList = new ArrayList<Permission>();
        Optional.ofNullable(meuList).orElse(new ArrayList<Permission>())
                .stream().filter(item -> item != null && Objects.equals(item.getParentId(), pid))
                .forEach(item -> {
                    Permission permission = new Permission();
                    BeanUtils.copyProperties(item, permission);
                    List<Permission> children = makeMenuTree(meuList, item.getId());
                    permission.setChildren(children);
                    permissionList.add(permission);
                });
        return permissionList;

    }
}
