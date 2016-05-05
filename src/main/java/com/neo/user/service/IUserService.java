package com.neo.user.service;

import com.neo.user.model.User;

import java.util.List;

/**
 * Created by neowyp on 2016/2/24.
 */
public interface IUserService {

    public List getUserList();
    public List getPersonList();
    public void save(User user);
    public User getOne(Integer id);

    public void funcA();
    public void funcB();
    public void funcC_AB();
}
