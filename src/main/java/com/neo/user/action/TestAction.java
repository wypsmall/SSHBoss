package com.neo.user.action;

import com.neo.user.model.User;
import com.neo.user.service.IUserService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by neowyp on 2016/2/23.
 */
@ParentPackage("basePackage")
//使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为struts2Test
@Action(value = "strust2Test")
//使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Namespace("/")
public class TestAction {

    @Autowired
    private IUserService userService;

    public void testFunc() {
        System.out.println("===>" + System.currentTimeMillis());
        User user = new User();
        user.setName("U用户-" + System.currentTimeMillis());
        user.setPassword("Pwd-" + System.currentTimeMillis());
        userService.save(user);
        System.out.println("===>" + System.currentTimeMillis());
    }
}
