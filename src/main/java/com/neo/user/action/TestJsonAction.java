package com.neo.user.action;

import com.neo.user.model.User;
import com.opensymphony.xwork2.Action;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by neowyp on 2016/2/23.
 */
public class TestJsonAction {

    private Map<String,Object> dataMap;


    public Map<String, Object> getDataMap() {
        return dataMap;
    }


    public String getUserInfo() {
        dataMap = new HashMap<String, Object>();
        User user = new User();
        user.setName("张三");
        user.setPassword("123");
        dataMap.put("user", user);
        // 放入一个是否操作成功的标识
        dataMap.put("success", true);
        return Action.SUCCESS;
    }

    //设置key属性不作为json的内容返回
/*    @JSON(serialize=false)
    public String getKey() {
        return key;
    }*/
}
