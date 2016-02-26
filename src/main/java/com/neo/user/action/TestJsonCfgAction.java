package com.neo.user.action;

import com.neo.user.model.User;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by neowyp on 2016/2/23.
 */
@ParentPackage("base")
@Namespace("/watson")
@Results({
        @Result(name = "json", type = "json", params = {"root", "dataMap"})
})
public class TestJsonCfgAction {

    private String page;
    private String rows;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    private Map<String, Object> dataMap;


    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    @Action(value = "json")
    public String getUserInfo() {
        dataMap = new HashMap<String, Object>();
        User user = new User();
        user.setName("张三1");
        user.setPassword("123");
        dataMap.put("user", user);
        // 放入一个是否操作成功的标识
        dataMap.put("success", true);
        return "json";
    }

    @Action(value = "userList")
    public String getUserList() {
        System.out.println("page:" + page + "rows" + rows);
        dataMap = new HashMap<String, Object>();
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setName("张-" + i);
            user.setPassword("PWD-" + System.currentTimeMillis());
            list.add(user);
        }
        dataMap.put("total", 16);
        dataMap.put("rows", list);
        return "json";
    }

    //设置key属性不作为json的内容返回
/*    @JSON(serialize=false)
    public String getKey() {
        return key;
    }*/
}
