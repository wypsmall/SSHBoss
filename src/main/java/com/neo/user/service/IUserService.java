package com.neo.user.service;

import com.neo.user.model.User;

import java.util.List;

/**
 * Created by neowyp on 2016/2/24.
 */
public interface IUserService {

    public List getList();
    public void save(User user);
}
