package com.evgen.dao.impl;


import com.evgen.dao.UserDAO;
import com.evgen.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HibernateUserDAO implements UserDAO {

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //@Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<User> getAll() {
        return currentSession().createQuery("from com.evgen.model.User", User.class).list();
    }

    @Override
    public User getOne(String email) {
        Query<User> q = currentSession().createQuery(
                "from com.evgen.model.User where email = :email", User.class
        );
        q.setParameter("email", email);
        return q.list().stream().findAny().orElse(null);
    }

    @Override
    public void add(User user) {
        currentSession().save(user);
    }

}
