package com.management.staff.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.management.staff.demo.entity.Emp;
import com.management.staff.demo.vo.CollectDataExcelVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Emp)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-19 20:01:14
 */
@Mapper
public interface EmpDao extends BaseMapper<Emp> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Emp queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Emp> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param emp 实例对象
     * @return 对象列表
     */
    IPage<Emp> queryAll(IPage<Emp> page, Emp emp);

    /**
     * 新增数据
     *
     * @param emp 实例对象
     * @return 影响行数
     */
    int insert(Emp emp);

    /**
     * 修改数据
     *
     * @param emp 实例对象
     * @return 影响行数
     */
    int update(Emp emp);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(int id);

    // 导出Excel
    List<CollectDataExcelVo> queryExcel();
}