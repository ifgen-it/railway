package com.evgen.dao.impl;

import com.evgen.dao.UserDAO;
import com.evgen.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
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
        entityManager.persist(user);
    }

}
