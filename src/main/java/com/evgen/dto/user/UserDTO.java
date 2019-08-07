package com.evgen.dto.user;


import com.evgen.entity.user.RoleEntity;

import java.io.Serializable;
import java.util.Date;

public class UserDTO implements Serializable {

    private int userId;

    private RoleDTO role;

    private String firstName;

    private String lastName;

    private Date birthday;

    private String email;

    private String password;

    public UserDTO(int userId, RoleDTO role, String firstName, String lastName, Date birthday, String email, String password) {
        this.userId = userId;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }

    public UserDTO() {
        this.role = new RoleDTO();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
