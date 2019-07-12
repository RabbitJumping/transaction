package com.transaction.serviceImpl;

import com.transaction.dao.UserDao;
import com.transaction.entity.User;
import com.transaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service


public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 测试查询业务的事物
     * 方法上不加@Transactional注解
     * 日志：Closing non transactional SqlSession （这种情况说明没有开启Spring的事务管理，所以关闭一个非事务的SqlSession。）
     */
    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    @Transactional
    public void insertUser(User user) {
        User user1=null;
        userDao.insertUser(user);
        user1.getUid();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
    }
}
