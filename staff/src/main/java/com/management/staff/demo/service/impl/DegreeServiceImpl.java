package com.management.staff.demo.service.impl;

import com.management.staff.demo.entity.Degree;
import com.management.staff.demo.mapper.DegreeDao;
import com.management.staff.demo.service.DegreeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Degree)表服务实现类
 *
 * @author makejava
 * @since 2023-07-19 20:01:11
 */
@Service("degreeService")
public class DegreeServiceImpl implements DegreeService {
    @Resource
    private DegreeDao degreeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Degree queryById(Integer id) {
        return this.degreeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Degree> queryAllByLimit(int offset, int limit) {
        return this.degreeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param degree 实例对象
     * @return 实例对象
     */
    @Override
    public Degree insert(Degree degree) {
        this.degreeDao.insert(degree);
        return degree;
    }

    /**
     * 修改数据
     *
     * @param degree 实例对象
     * @return 实例对象
     */
    @Override
    public Degree update(Degree degree) {
        this.degreeDao.update(degree);
        return this.queryById(degree.getEmpDegreeCode());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.degreeDao.deleteById(id) > 0;
    }

    @Override
    public List queryAll() {
        return degreeDao.queryAll();
    }
}