package com.transaction.serviceImpl;

import com.transaction.dao.MenuDao;
import com.transaction.entity.Menu;
import com.transaction.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class MenuServiceImpl implements MenuService {

@Autowired
private MenuDao menuDao;
    /**
     * REQUIRED  日志：Creating new transaction with name
     * SUPPORTS  日志：Registering transaction synchronization for SqlSession不会创建新事物
     */
    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<Menu> selectAll() {
        return menuDao.selectAll();
    }

    @Override
    public void insertMenu(Menu menu) {
        menuDao.insertMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        menuDao.updateMenu(menu);
    }

    @Override
    public void deleteUser(Integer id) {
        menuDao.deleteUser(id);
    }
}
