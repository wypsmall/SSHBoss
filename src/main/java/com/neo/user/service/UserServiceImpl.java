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
        User tmp = new User();
        tmp.setName("123456789012345678901234567890");
        tmp.setPassword("pwd-123");
        userDao.save(tmp);
    }
}
