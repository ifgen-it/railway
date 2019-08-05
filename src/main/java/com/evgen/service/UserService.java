package com.evgen.service;

import com.evgen.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getOne(String email);

    void add(User user);
}
