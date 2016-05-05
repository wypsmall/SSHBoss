package com.neo.test;

import com.neo.user.model.User;
import com.neo.user.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by neowyp on 2016/3/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext*.xml")
public class MySpringTest {

    @Autowired
    ApplicationContext ctx;

//    @Test
    public void testGetBeans() {
        String[] beanNames = ctx.getBeanDefinitionNames();
        for (int i = 0; i < beanNames.length; i++) {
            System.out.println(i+"-"+beanNames[i]);
        }
    }
//    @Test
    public void testUserService() {
        IUserService userService = (IUserService) ctx.getBean("userService");
        userService.getUserList();
//        System.out.println("[1]==>"+ctx.getBean("userDao"));
        /*User user = userService.getOne(1);
        System.out.println(user);*/
//        User user = new User();
//        user.setName("A用户-" + System.currentTimeMillis());
//        user.setPassword("Pwd-" + System.currentTimeMillis());
//        userService.save(user);
//        System.out.println("[2]==>"+ctx.getBean("userDao"));
    }

    @Test
    public void emptyFunc() {

    }
}
