package com.evgen.dao.impl;

import com.evgen.dao.UserDAO;
import com.evgen.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
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

        // THIS DATE CONVERSION WORKS GOOD
        java.util.Date utilDate = user.getBirthday();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
        String strDate = formatter.format(utilDate);
        System.out.println("------> strDate = " + strDate);

        jdbcTemplate.update(
                "insert into user(role_id, first_name, last_name, birthday, email, password)" +
                " values (?, ?, ?, str_to_date(?, '%Y-%m-%d'), ?, ?)",
                user.getRoleId(), user.getFirstName(), user.getLastName(),
                strDate, user.getEmail(), user.getPassword()
        );

    }
}