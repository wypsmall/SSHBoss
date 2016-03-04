package com.neo;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by neowyp on 2016/3/4.
 */
public class SpringContextLoaderListener implements ServletContextListener {
    private static WebApplicationContext springContext;

    public static WebApplicationContext getSpringContext() {
        return springContext;
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        springContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        try {
            System.out.println("===================");
            String[] beanNames = springContext.getBeanDefinitionNames();
            for (int i = 0; i < beanNames.length; i++) {
                System.out.println(i + "-" + beanNames[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
