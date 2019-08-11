package com.evgen.service.impl;

import com.evgen.dao.RoleDAO;
import com.evgen.dao.UserDAO;
import com.evgen.dto.user.RoleDTO;
import com.evgen.dto.user.UserDTO;
import com.evgen.entity.user.RoleEntity;
import com.evgen.entity.user.UserEntity;
import com.evgen.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public List<UserDTO> getAllUsers() {

        List<UserEntity> entities = userDAO.getAll();
        List<UserDTO> dtos = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        for (UserEntity user : entities ) {
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            dtos.add(userDTO);
        }

        return dtos;
    }

    @Override
    public UserDTO getByEmail(String email) {

        UserEntity userEntity =  userDAO.getByEmail(email);
        ModelMapper modelMapper = new ModelMapper();

        if (userEntity == null)
            return  null;

        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return userDTO;
    }

    @Override
    public int addUser(UserDTO user) {

        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);

        System.out.println("----> in addUser, userEntity: " + userEntity);

        return userDAO.add(userEntity);
    }

    @Override
    public void removeWith(int id) {
        userDAO.removeWith(id);
    }

    @Override
    public List<RoleDTO> getAllRoles() {

        List<RoleEntity> entities = roleDAO.getAll();
        List<RoleDTO> dtos = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        entities.forEach(item -> dtos.add(modelMapper.map(item, RoleDTO.class)));

        return dtos;
    }

    @Override
    public UserDTO getUser(int userId) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDAO.get(userId), UserDTO.class);
    }

    @Override
    public RoleDTO getRole(int roleId) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roleDAO.get(roleId), RoleDTO.class);
    }
}
