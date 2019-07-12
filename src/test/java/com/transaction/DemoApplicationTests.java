package com.transaction;

import com.transaction.service.MenuService;
import com.transaction.service.TestService;
import com.transaction.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    TestService testService;
    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;


    @Test
    public void contextLoads() {
        System.out.println(testService.testTransaction());//事物未回滚
    }
    @Test
    public void contextLoads2()  {
        System.out.println(testService.testTransaction2());
    }
    @Test
    public void contextLoads3()  {
        System.out.println(testService.testTransaction3());
    }
    @Test
    public void contextLoads4()  {
        System.out.println(testService.testTransaction4());
    }


    @Test
    public void contextLoads5()  {
        System.out.println(userService.selectAll());
    }


    @Test
    public void contextLoads6()  {
        System.out.println(menuService.selectAll());
    }
    @Test
    public void contextLoads7(){
        System.out.println(testService.testNestedTransaction());
    }
}
