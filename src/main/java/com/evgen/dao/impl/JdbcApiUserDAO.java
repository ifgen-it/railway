package com.evgen.dao.impl;

import com.evgen.dao.UserDAO;

import com.evgen.model.User;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class JdbcApiUserDAO implements UserDAO {

    private static Connection conn;

    static {
        String url = null;
        String username = null;
        String password = null;

        // LOAD DB PROPERTIES
        try (InputStream in = UserDAO.class
                .getClassLoader().getResourceAsStream("persistence.properties")) {

            Properties properties = new Properties();
            properties.load(in);

            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // ACQUIRE DB CONNECTION
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAll() {

        List<User> users = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("select * from user");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                User newUser = getUser(rs);
                users.add(newUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getOne(String email) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select * from user where email = ?");

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = getUser(rs);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(User user) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(
                    "insert into user(role_id, first_name, last_name, birthday, email, password)\n" +
                            "values (?, ?, ?, str_to_date(?, '%Y-%m-%d'), ?, ?)"
            );


            // THIS DATE CONVERSION DO NOT WORKS -- ERROR: -1 DAY WHEN YOU WANT TO INSERT IN MYSQL
            java.util.Date utilDate = user.getBirthday();
            long time = utilDate.getTime();
            System.out.println("---> utilDate = " + utilDate);
            System.out.println("---> time = " + time);

            java.sql.Date sqlDate = new java.sql.Date(time);
            System.out.println("------> sqlDate = " + sqlDate);

            // THIS DATE CONVERSION WORKS GOOD
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
            String strDate = formatter.format(utilDate);
            System.out.println("------> strDate = " + strDate);

            ps.setInt(1, user.getRoleId());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, strDate);
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPassword());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User getUser(ResultSet rs) throws SQLException {

        User user = new User();

        user.setUserId(rs.getInt("user_id"));
        user.setRoleId(rs.getInt("role_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setBirthday(rs.getDate("birthday"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setCreated(rs.getTimestamp("created"));

        return user;
    }

}
