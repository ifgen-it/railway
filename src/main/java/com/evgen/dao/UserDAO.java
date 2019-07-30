package com.evgen.dao;

import com.evgen.model.User;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class UserDAO {

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

    public List<User> getAll() throws SQLException {

        List<User> users = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("select * from user");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            User user = new User();
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            users.add(user);
        }
        return users;
    }

    public User getOne(String email) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select * from user where email = ?");

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(User user) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "insert into user(role_id, first_name, last_name, birthday, email, password)\n" +
                        "values (2, ?, ?, '2000-02-02', ?, '12345')"
        );
        ps.setString(1, user.getFirstName());
        ps.setString(2,user.getLastName());
        ps.setString(3,user.getEmail());
        ps.execute();

    }
}