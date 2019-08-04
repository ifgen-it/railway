package com.evgen.dao.impl;


import com.evgen.dao.UserDAO;
import com.evgen.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class HibernateUserDAO implements UserDAO {

    @Autowired
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

        // DON'T WORKS
        //currentSession().save(user);

        java.util.Date utilDate = user.getBirthday();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
        String strDate = formatter.format(utilDate);
        System.out.println("------> strDate = " + strDate);

        Session session = currentSession();
        session.beginTransaction();

        NativeQuery<User> query = session.createNativeQuery("insert into user(role_id, first_name, last_name, birthday, email, password) " +
                "values (?, ?, ?, str_to_date(?, '%Y-%m-%d'), ?, ?)");

        query.setParameter(1, user.getRoleId());
        query.setParameter(2, user.getFirstName());
        query.setParameter(3, user.getLastName());
        query.setParameter(4, strDate);
        query.setParameter(5, user.getEmail());
        query.setParameter(6, user.getPassword());

        query.executeUpdate();

        session.getTransaction().commit();
        session.close();

    }

}
