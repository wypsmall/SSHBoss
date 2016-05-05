package com.neo.test;

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
//            System.out.println(i + "-" + beanNames[i] + " == "+ ctx.getBean(beanNames[i]).getClass().getName());
            System.out.println(i + "-" + beanNames[i]);
        }

        System.out.println("===================");
/*        Map<String, Object> beans = ctx.getBeansOfType(Object.class);
        Set<String> beansKey = beans.keySet();
        int i = 0;
        for (Iterator it = beansKey.iterator(); it.hasNext(); ) {
            String s = (String) it.next();
//            System.out.println(i + "-[" + beans.get(s).getClass().getName() + "]   " + "-" + s);
            i++;
        }*/
        IUserService userService = (IUserService) ctx.getBean("userService");
        userService.getUserList();
        userService.getPersonList();

        Object o = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{IUserService.class}, new MyProxy());
        System.out.println("==>"  + o.getClass().getName());
    }

    public static class MyProxy implements InvocationHandler {

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }
}
