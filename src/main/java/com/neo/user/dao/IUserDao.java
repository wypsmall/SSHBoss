package com.neo.user.dao;

import com.neo.user.model.User;

import java.io.Serializable;

/**
 * Created by neowyp on 2016/2/24.
 */
public interface IUserDao {

    public Serializable save(User user);
    public User getOne(Integer id);
}
