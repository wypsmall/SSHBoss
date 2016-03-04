package com.neo.user.service;

import com.neo.account.service.IAccountService;
import com.neo.user.dao.IUserDao;
import com.neo.user.model.User;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by neowyp on 2016/2/24.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    //    @Autowired(required =false) //因为 required=false，如果spring找不到的话会赋空值进去
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IAccountService accountService;
    public List getList() {
        System.out.println("=========getList=============userDao:" + userDao);
        return null;
    }

    @Transactional
    public void save(User user) {
        userDao.save(user);
/*        User tmp = new User();
        tmp.setName("123456789012345678901234567890");
        tmp.setPassword("pwd-123");
        userDao.save(tmp);*/
    }

    public User getOne(Integer id) {
        return userDao.getOne(id);
    }


}
