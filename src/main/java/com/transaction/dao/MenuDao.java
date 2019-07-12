package com.transaction.dao;

import com.transaction.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
public interface MenuDao {
    public List<Menu> selectAll();
    public void insertMenu( Menu menu);
    public void updateMenu( Menu menu);
    public void deleteUser(Integer id);

}
