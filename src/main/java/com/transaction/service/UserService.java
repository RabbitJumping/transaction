package com.transaction.service;

import com.transaction.entity.User;

import java.util.List;

public interface UserService {
    public List<User> selectAll();
    public void insertUser(User user);
    public void updateUser(User user);
    public void  deleteUser(Integer id);
}
