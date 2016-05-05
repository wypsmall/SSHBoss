package com.neo.test;

import com.neo.user.dao.IUserDao;
import com.neo.user.model.User;
import com.neo.user.service.IUserService;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by neowyp on 2016/3/4.
 */
public class TestUserDao extends TestCase {
    private ApplicationContext ctx;

    @Override
    protected void setUp() throws Exception {
        ctx = new ClassPathXmlApplicationContext("spring/applicationContext*.xml");
        System.out.println("===================");
        String[] beanNames = ctx.getBeanDefinitionNames();
        for (int i = 0; i < beanNames.length; i++) {
//            System.out.println(i + "-" + beanNames[i] + " == "+ ctx.getBean(beanNames[i]).getClass().getName());
            System.out.println(i + "-" + beanNames[i]);
        }
        System.out.println("===================");
    }

    public void utestUserGet() {
        IUserService userService = (IUserService)ctx.getBean("userService");
        User user = userService.getOne(3);
        System.out.println("userInfo:" + user);

        IUserDao userDao = (IUserDao)ctx.getBean("userDao");
        user = userDao.getOne(1);
        System.out.println("userInfo:" + user);
    }

    public void testUserService() {
        IUserService userService = (IUserService)ctx.getBean("userService");
        userService.funcC_AB();
    }
}
