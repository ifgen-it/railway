package com.evgen.dao;

import com.evgen.entity.user.RoleEntity;

public interface RoleDAO {

    RoleEntity get(int id);

    RoleEntity getRoleByName(String roleName);
}
