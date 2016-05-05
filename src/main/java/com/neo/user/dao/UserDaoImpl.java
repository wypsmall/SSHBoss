package com.neo.user.dao;

import com.neo.user.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by neowyp on 2016/2/24.
 */
@Repository("userDao")
public class UserDaoImpl implements IUserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Serializable save(User user) {
        try {
            return sessionFactory.getCurrentSession().save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public User getOne(Integer id) {
        try {
            return (User) sessionFactory.getCurrentSession().get(User.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
