package com.transaction.service;

import com.transaction.entity.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> selectAll();
    public void insertMenu(Menu menu);
    public void updateMenu(Menu menu);
    public void deleteUser(Integer id);
}
