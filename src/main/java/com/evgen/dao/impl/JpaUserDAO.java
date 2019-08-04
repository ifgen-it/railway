package com.evgen.dao.impl;

import com.evgen.dao.UserDAO;
import com.evgen.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class JpaUserDAO implements UserDAO {

    @PersistenceContext(unitName = "emf")
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        String queryString = "select u from User u";
        Query query = entityManager.createQuery(queryString);
        return query.getResultList();

    }

    @Override
    public User getOne(String email) {
        String queryString = "select u from User u where email = :email";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("email", email);
        List<User> users = query.getResultList();

        if (users.size() == 0)
            return null;
        else
            return users.get(0);
    }

    @Override
    @Transactional
    public void add(User user) {

        // DON'T WORK -- MAY BY 'DATE' FORMAT JPA CANNOT CONVERT TO SQL.DATE
        // entityManager.persist(user);

        java.util.Date utilDate = user.getBirthday();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
        String strDate = formatter.format(utilDate);
        System.out.println("------> strDate = " + strDate);

        Query query = entityManager.createNativeQuery(
                "insert into user(role_id, first_name, last_name, birthday, email, password)" +
                        " values (?, ?, ?, str_to_date(?, '%Y-%m-%d'), ?, ?)");
        query.setParameter(1, user.getRoleId());
        query.setParameter(2, user.getFirstName());
        query.setParameter(3, user.getLastName());
        query.setParameter(4, strDate);
        query.setParameter(5, user.getEmail());
        query.setParameter(6, user.getPassword());

        query.executeUpdate();
    }

}
