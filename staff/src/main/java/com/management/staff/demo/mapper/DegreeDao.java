package com.management.staff.demo.mapper;

import com.management.staff.demo.entity.Degree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Degree)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-19 20:01:08
 */
@Mapper
public interface DegreeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Degree queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Degree> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param
     * @return 对象列表
     */
    List<Degree> queryAll();

    /**
     * 新增数据
     *
     * @param degree 实例对象
     * @return 影响行数
     */
    int insert(Degree degree);

    /**
     * 修改数据
     *
     * @param degree 实例对象
     * @return 影响行数
     */
    int update(Degree degree);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}