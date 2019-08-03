package com.evgen.dao.impl;

import com.evgen.dao.UserDAO;
import com.evgen.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcTemplateUserDAO implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAll() {

        return jdbcTemplate.query("select * from user",
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    @Override
    public User getOne(String email) {
        return jdbcTemplate.query("select * from user where email = ?",
                new Object[]{email},
                new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public void add(User user) {

        jdbcTemplate.update(
                "insert into user(role_id, first_name, last_name, birthday, email, password)" +
                " values (?, ?, ?, ?, ?, ?)",
                user.getRoleId(), user.getFirstName(), user.getLastName(),
                user.getBirthday(), user.getEmail(), user.getPassword()
        );

    }
}