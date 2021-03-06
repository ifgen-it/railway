package com.evgen.dao.impl;

import com.evgen.dao.UserDAO;
import com.evgen.entity.user.UserEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Component
@Transactional
public class UserDAOImpl implements UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    @PersistenceContext(unitName = "emf")
    private EntityManager em;

    @Override
    public List<UserEntity> getAll() {
        String queryString = "select u from UserEntity u";
        Query query = em.createQuery(queryString);
        return query.getResultList();

    }

    @Override
    public UserEntity getByEmail(String email) {

        logger.info("Email:" + email);

        String queryString = "select u from UserEntity u where u.email = :email";
        Query query = em.createQuery(queryString);
        query.setParameter("email", email);
        List<UserEntity> users = (List<UserEntity>) query.getResultList();

        logger.info("Users: " + users);

        if (users.size() == 0)
            return null;
        else
            return users.get(0);
    }

    @Override
    public int add(UserEntity user) {

        // DON'T WORK -- MAY BY 'DATE' FORMAT JPA CANNOT CONVERT TO SQL.DATE
        logger.info("User: " + user);
        em.persist(user);
        return  user.getUserId();

    }

    @Override
    public UserEntity update(UserEntity user) {
        return em.merge(user);
    }

    @Override
    public void remove(UserEntity user) {
        if (!em.contains(user)){
            user = em.merge(user);
        }
        em.remove(user);
    }

    @Override
    public void removeWith(int id) {

        String queryString = "delete from UserEntity u where u.userId = " + id;
        Query query = em.createQuery(queryString);
        query.executeUpdate();
    }

    @Override
    public UserEntity get(int id) {
        return em.find(UserEntity.class, id);
    }


}
