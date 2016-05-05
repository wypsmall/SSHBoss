package com.neo.user.service;

import com.neo.account.service.IAccountService;
import com.neo.user.dao.IPersonDao;
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
//@Setter
public class UserServiceImpl implements IUserService {
    //    @Autowired(required =false) //因为 required=false，如果spring找不到的话会赋空值进去
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IPersonDao personDao;
    @Autowired
    private IAccountService accountService;

    public List getUserList() {
        System.out.println("=========getUserList=============userDao:" + userDao);
        return null;
    }

    public List getPersonList() {
        System.out.println("=========getPersonList=============personDao:" + personDao);
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

    @Transactional
    public User getOne(Integer id) {
        return userDao.getOne(id);
    }

    @Transactional
    public void funcA() {
        User tmp = new User();
        tmp.setName("funcA-01-"+System.currentTimeMillis());
        tmp.setPassword("funcA-01");
        userDao.save(tmp);
        tmp = new User();
        tmp.setName("funcA-02-"+System.currentTimeMillis());
        tmp.setPassword("funcA-02");
        userDao.save(tmp);
    }

    @Transactional
    public void funcB() {
        User tmp = new User();
        tmp.setName("funcB-01-"+System.currentTimeMillis());
        tmp.setPassword("funcB-01");
        userDao.save(tmp);
        tmp = new User();
        tmp.setName( "1234567890123456789012345678901234567890");
        tmp.setPassword("funcB-02");
        userDao.save(tmp);
    }
//    @Transactional
    public void funcC_AB() {
        funcA();
        //funcB();
    }

}
