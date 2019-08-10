package com.evgen.dao;

import com.evgen.entity.user.RoleEntity;

import java.util.List;

public interface RoleDAO {

    RoleEntity get(int id);

    RoleEntity getRoleByName(String roleName);

    List<RoleEntity> getAll();
}
