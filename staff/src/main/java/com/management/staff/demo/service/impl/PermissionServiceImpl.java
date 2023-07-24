package com.management.staff.demo.service.impl;

import com.management.staff.demo.mapper.PermissionMapper;
import com.management.staff.demo.entity.Permission;
import com.management.staff.demo.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysPermission)表服务实现类
 *
 * @author makejava
 * @since 2023-07-21 01:28:49
 */
@Service("sysPermissionService")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 根据用户id查询权限列表
     *
     * @param id
     * @return 对象列表
     */
    @Override
    public List<Permission> findPermissionListByUserId(Long id) {
        return permissionMapper.findPermissionListByUserId(id);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Permission queryById(Long id) {
        return this.permissionMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Permission> queryAllByLimit(int offset, int limit) {
        return this.permissionMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission insert(Permission sysPermission) {
        this.permissionMapper.insert(sysPermission);
        return sysPermission;
    }

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission update(Permission permission) {
        this.permissionMapper.update(permission);
        return this.queryById(permission.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.permissionMapper.deleteById(id) > 0;
    }
}