package com.management.staff.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.management.staff.demo.entity.Emp;
import com.management.staff.demo.mapper.EmpDao;
import com.management.staff.demo.service.EmpService;
import com.management.staff.demo.vo.CollectDataExcelVo;
import com.management.staff.demo.vo.EmpQueryVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Emp)表服务实现类
 *
 * @author makejava
 * @since 2023-07-19 20:01:14
 */
@Service("empService")
public class EmpServiceImpl implements EmpService {
    @Resource
    private EmpDao empDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Emp queryById(String id) {
        return this.empDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Emp> queryAllByLimit(int offset, int limit) {
        return this.empDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param emp 实例对象
     * @return 实例对象
     */
    @Override
    public Emp insert(Emp emp) {
        this.empDao.insert(emp);
        return emp;
    }

    /**
     * 修改数据
     *
     * @param emp 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Emp emp) {
        return this.empDao.update(emp);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return false;
    }


    /**
    * 查询所有职工信息
    * */
    @Override
    public List queryAll(Emp emp) {
//        return this.empDao.queryAll(emp);
        return null;
    }

    @Override
    public boolean addEmp(Emp emp) {
        if (this.empDao.insert(emp) == 1) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int deleteEmp(int id) {
        return this.empDao.deleteById(id);
    }

    @Override
    public List<CollectDataExcelVo> queryExcel() {
        List<CollectDataExcelVo> list = this.empDao.queryExcel();
        return list;
    }

    @Override
    public IPage<Emp> findUserListByPage(IPage<Emp> page, Emp emp) {
//        //创建条件构造器对象
//        QueryWrapper<Emp> queryWrapper = new QueryWrapper<Emp>();
//        //名字
//        queryWrapper.like(!ObjectUtils.isEmpty(empQueryVo.getName()), "name",empQueryVo.getName());
//        // 部门
//        queryWrapper.eq(!ObjectUtils.isEmpty(empQueryVo.getEmpDegreeCode()), "emp_dept_code",empQueryVo.getEmpDegreeCode());
//        // 学历
//        queryWrapper.eq(!ObjectUtils.isEmpty(empQueryVo.getEmpDegreeCode()), "emp_degree_code",empQueryVo.getEmpDegreeCode());

        return empDao.queryAll(page, emp);
    }
}