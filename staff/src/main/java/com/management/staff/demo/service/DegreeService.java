package com.management.staff.demo.service;

import com.management.staff.demo.entity.Degree;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Degree)表服务接口
 *
 * @author makejava
 * @since 2023-07-19 20:01:10
 */
public interface DegreeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Degree queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Degree> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param degree 实例对象
     * @return 实例对象
     */
    Degree insert(Degree degree);

    /**
     * 修改数据
     *
     * @param degree 实例对象
     * @return 实例对象
     */
    Degree update(Degree degree);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List queryAll();
}