package com.evgen.dao;

import com.evgen.entity.user.UserEntity;

import java.util.List;

public interface UserDAO {

    List<UserEntity> getAll();

    UserEntity getOne(String email);

    int add(UserEntity user);

    UserEntity update(UserEntity user);

    void remove(UserEntity user);

    void removeWith(int id);

    UserEntity get(int id);




}
