package com.evgen.dao.impl;

import com.evgen.dao.UserDAO;
import com.evgen.entity.user.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class UserDAOImpl implements UserDAO {

    @PersistenceContext(unitName = "emf")
    private EntityManager em;

    @Override
    public List<UserEntity> getAll() {
        String queryString = "select u from UserEntity u";
        Query query = em.createQuery(queryString);
        return query.getResultList();

    }

    @Override
    public UserEntity getOne(String email) {

        System.out.println("---> email:" + email + "$");

        String queryString = "select u from UserEntity u where u.email = :email";
        Query query = em.createQuery(queryString);
        query.setParameter("email", email);
        List<UserEntity> users = (List<UserEntity>) query.getResultList();

        System.out.println("---> Users: " + users);

        if (users.size() == 0)
            return null;
        else
            return users.get(0);
    }

    @Override
    @Transactional
    public void add(UserEntity user) {

        // DON'T WORK -- MAY BY 'DATE' FORMAT JPA CANNOT CONVERT TO SQL.DATE
        /*System.out.println("user: " + user);
        em.persist(user);*/

        java.util.Date utilDate = user.getBirthday();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
        String strDate = formatter.format(utilDate);
        System.out.println("------> strDate = " + strDate);

        Query query = em.createNativeQuery(
                "insert into user(role_id, first_name, last_name, birthday, email, password)" +
                        " values (?, ?, ?, str_to_date(?, '%Y-%m-%d'), ?, ?)");
        query.setParameter(1, user.getRole().getRoleId());
        query.setParameter(2, user.getFirstName());
        query.setParameter(3, user.getLastName());
        query.setParameter(4, strDate);
        query.setParameter(5, user.getEmail());
        query.setParameter(6, user.getPassword());

        query.executeUpdate();
    }

}
