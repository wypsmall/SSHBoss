package com.neo.user.service;

import com.neo.user.dao.IUserDao;
import com.neo.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by neowyp on 2016/2/24.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    public List getList() {
        return null;
    }

    @Transactional
    public void save(User user) {
        userDao.save(user);
    }
}
