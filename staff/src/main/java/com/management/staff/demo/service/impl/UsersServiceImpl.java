package com.management.staff.demo.service.impl;

import com.management.staff.demo.entity.Users;
import com.management.staff.demo.mapper.UsersMapper;
import com.management.staff.demo.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Users)表服务实现类
 *
 * @author makejava
 * @since 2023-07-19 20:01:14
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {
    @Resource
    private UsersMapper usersMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public Users queryByUserId(String userId) {
        return this.usersMapper.queryByUserId(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Users> queryAllByLimit(int offset, int limit) {
        return this.usersMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @Override
    public Users insert(Users users) {
        this.usersMapper.insert(users);
        return users;
    }

    /**
     * 修改数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @Override
    public Users update(Users users) {
        this.usersMapper.update(users);
        return this.queryByUserId(users.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userId) {
        return this.usersMapper.deleteById(userId) > 0;
    }


//    @Override
//    public User findUserByUserName(String username) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//        queryWrapper.eq("user_id", username);
//
//        return basemap.;
//    }
}