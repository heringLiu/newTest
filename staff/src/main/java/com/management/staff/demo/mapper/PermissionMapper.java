package com.management.staff.demo.mapper;

import com.management.staff.demo.entity.Permission;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SysPermission)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-21 01:26:13
 */
public interface PermissionMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Permission queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Permission> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);
    /**
     * 根据用户id查询权限列表
     *
     * @param id
     * @return 对象列表
     */
    List<Permission> findPermissionListByUserId(Long id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param permission 实例对象
     * @return 对象列表
     */
    List<Permission> queryAll(Permission permission);

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 影响行数
     */
    int insert(Permission permission);

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 影响行数
     */
    int update(Permission permission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}