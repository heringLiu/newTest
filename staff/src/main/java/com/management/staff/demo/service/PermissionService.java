package com.management.staff.demo.service;

import com.management.staff.demo.entity.Permission;
import java.util.List;

/**
 * (SysPermission)表服务接口
 *
 * @author makejava
 * @since 2023-07-21 01:28:47
 */
public interface PermissionService {
    /**
     * 根据用户id查询权限列表
     *
     * @param id
     * @return 对象列表
     */
    List<Permission> findPermissionListByUserId(Long id);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Permission queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Permission> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    Permission insert(Permission sysPermission);

    /**
     * 修改数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    Permission update(Permission sysPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}