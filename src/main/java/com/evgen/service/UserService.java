package com.evgen.service;

import com.evgen.dto.user.RoleDTO;
import com.evgen.dto.user.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getByEmail(String email);

    int addUser(UserDTO user);

    void removeWith(int id);

    List<RoleDTO> getAllRoles();

    UserDTO getUser(int userId);

    RoleDTO getRole(int roleId);

}
