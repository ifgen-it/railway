package com.evgen.service;

import com.evgen.dao.UserDAO;
import com.evgen.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("jpaUserDAO")
    private UserDAO userDAO;

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User getOne(String email) {
        return userDAO.getOne(email);
    }

    @Override
    @Transactional
    public void add(User user) {
        userDAO.add(user);
    }
}
