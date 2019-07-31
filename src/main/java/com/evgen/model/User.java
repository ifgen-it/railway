package com.evgen.model;

import java.sql.Timestamp;
import java.util.Date;

public class User {

    private int userId;
    private int roleId;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String email;
    private String password;
    private Timestamp created;

    public User(String firstName, String lastName, Date birthday, String email, String password) {
        this.roleId = 2;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public int getUserId() {
        return userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getPassword() {
        return password;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
        this.roleId = 2;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
