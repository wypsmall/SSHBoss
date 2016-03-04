package com.neo.test;

import com.neo.user.service.IUserService;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by neowyp on 2016/3/4.
 */
public class MyLoadXML extends TestCase {
    private ApplicationContext ctx;
    @Override
    protected void setUp() throws Exception {
        ctx = new ClassPathXmlApplicationContext("spring/applicationContext*.xml");
    }

    public void testEmpty() {
        System.out.println("===================");
        String[] beanNames = ctx.getBeanDefinitionNames();
        for (int i = 0; i < beanNames.length; i++) {
            System.out.println(i+"-"+beanNames[i]);
        }
        IUserService userService = (IUserService) ctx.getBean("userService");
        userService.getList();
    }
}
