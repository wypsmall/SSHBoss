package com.neo.user.dao;

import com.neo.user.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by neowyp on 2016/2/24.
 */
@Repository("personDao")
public class PersonDaoImpl implements IPersonDao {
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

    public User getOne(Integer id) {
        return (User) sessionFactory.getCurrentSession().get("id", id);
    }
}
