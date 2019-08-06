package com.evgen.service;

import com.evgen.dto.user.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAll();

    UserDTO getOne(String email);

    void add(UserDTO user);
}
