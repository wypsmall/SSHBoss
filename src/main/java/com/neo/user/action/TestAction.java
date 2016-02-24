package com.neo.user.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

/**
 * Created by neowyp on 2016/2/23.
 */
@ParentPackage("basePackage")
//使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为struts2Test
@Action(value="strust2Test")
//使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Namespace("/")
public class TestAction {

    public void testFunc() {
        System.out.println("==>" + System.currentTimeMillis());
    }
}
