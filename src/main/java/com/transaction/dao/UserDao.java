package com.transaction.dao;

import com.transaction.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
public interface UserDao {
    public List<User> selectAll();
    public void insertUser( User user);
    public void updateUser( User user);
    public void  deleteUser(Integer id);
}
