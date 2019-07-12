package com.transaction.serviceImpl;

import com.transaction.dao.MenuDao;
import com.transaction.dao.UserDao;
import com.transaction.entity.Menu;
import com.transaction.entity.User;
import com.transaction.service.MenuService;
import com.transaction.service.TestService;
import com.transaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


@Service
public class TestServiceImpl implements TestService {
    @Autowired
   private UserDao userDao;
    @Autowired
   private MenuDao menuDao;
    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;

    /**
     * 未加任何属性的@Transactional注解只能在抛出RuntimeException或者Error时才会触发事务的回滚，常见的非RuntimeException是不会触发事务的回滚的
     * 要在抛出 非RuntimeException时也触发回滚机制，需要我们在注解上添加 rollbackFor = { Exception.class }属性。，事务回滚的前提是添加@Transactional注解的方法中不含有try{...}catch{...}捕获异常，使得程序运行过程中出现异常能顺利抛出，从而触发事务回滚。
     * 有try catche时异常被捕获，@Transactional（）不会回滚
     *
     * @return
     */
    @Override
    @Transactional
    public boolean testTransaction() {//此方法事物不会回滚
        User user=new User(2,"li2","111111");
        Menu menu=new Menu(2,"branch","02");
        userDao.insertUser(user);
        try {
            float a=2/0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 我们往往需要在方法中进行异常的捕获，从而对异常进行判断，为客户端返回提示信息。
     * 但是此时由于异常的被捕获，导致事务的回滚没有被触发，导致事务的失败
     * @return
     */
    //解决方法一： 使用@Transactional注解，抛出@Transactional注解默认识别的RuntimeException
    @Override
    @Transactional
    public boolean testTransaction2() {
        User user=new User(2,"li2","111111");
        Menu menu=new Menu(2,"branch","02");
        try {
            userDao.insertUser(user);
            float a=2/0;
        }catch (Exception e){
            e.printStackTrace();
            throw new  RuntimeException("");
        }
        return true;
    }
    //解决方法二： 使用@Transactional(rollbackFor = { Exception.class })，抛出捕获的非RuntimeException异常
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean testTransaction3() {
        User user=new User(6,"li2","111111");
        Menu menu=new Menu(2,"branch","02");
        try {
            userDao.insertUser(user);
            float a=2/0;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return true;
    }
//方法三：手动回滚，上面两个在catch{...}中抛出异常的方法都有个不足之处，就是不能在catch{...}中存在return子句，所以设置手动回滚，当捕获到异常时，手动回滚，同时返回前台提示信息
    @Override
    @Transactional
    public boolean testTransaction4() {
        User user=new User(2,"li2","111111");
        Menu menu=new Menu(2,"branch","02");
        try {
            userDao.insertUser(user);
            float a=2/0;
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

/**
 * 事物嵌套测试
 * userService，menuService分别有自己的事物
 * REQUIRED 日志：Participating in existing transaction（事物的传播行为，加入现有的业务）
 * Initiating transaction rollback
 *  传播属性都是required,如果外层事物无异常，内部事务出现异常，那么外部事物包括的所有业务全部回滚。内部事务设不设rollbackfor属性无关。
 *  Application exception overridden by commit exception应用自定义异常被事务异常覆盖了
 *  Global transaction is marked as rollback-only but transactional code requested commit 把主事物标记成了rollback-only
 *  UnexpectedRollbackException
 */
    @Override
//    @Transactional(noRollbackFor = Exception.class)
    public boolean testNestedTransaction() {
        User user=new User(8,"li3","111111");
        Menu menu=new Menu(9,"branch","04");

        try {
        userService.insertUser(user);
        }catch (Exception e){
           e.printStackTrace();
        }
        menuService.insertMenu(menu);
//        float f=3/0;
        return true;
    }

}
