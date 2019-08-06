package com.evgen.dao;

import com.evgen.entity.user.UserEntity;

import java.util.List;

public interface UserDAO {

    List<UserEntity> getAll();

    UserEntity getOne(String email);

    void add(UserEntity user);
}
