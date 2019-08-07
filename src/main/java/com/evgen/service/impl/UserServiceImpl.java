package com.evgen.service.impl;

import com.evgen.dao.UserDAO;
import com.evgen.dto.user.UserDTO;
import com.evgen.entity.user.UserEntity;
import com.evgen.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<UserDTO> getAllUsers() {

        List<UserEntity> daos = userDAO.getAll();

        // CREATE CONVERSION TYPE
        Type listType = new TypeToken<List<UserDTO>>(){}.getType();

        // CONVERT LIST OF ENTITY OBJECTS TO A LIST OF DTOS OBJECTS
        List<UserDTO> dtos = new ModelMapper().map(daos, listType);

        return dtos;
    }

    @Override
    public UserDTO findByEmail(String email) {

        UserEntity userEntity =  userDAO.getOne(email);
        ModelMapper modelMapper = new ModelMapper();

        if (userEntity == null)
            return  null;

        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return userDTO;
    }

    @Override
    @Transactional
    public void add(UserDTO user) {

        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);

        System.out.println("----in add, userEntity: " + userEntity);
        userDAO.add(userEntity);
    }
}
