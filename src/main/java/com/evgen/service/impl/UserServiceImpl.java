package com.evgen.service.impl;

import com.evgen.dao.RoleDAO;
import com.evgen.dao.UserDAO;
import com.evgen.dto.ticket.TicketDTO;
import com.evgen.dto.user.RoleDTO;
import com.evgen.dto.user.UserDTO;
import com.evgen.entity.ticket.TicketEntity;
import com.evgen.entity.user.RoleEntity;
import com.evgen.entity.user.UserEntity;
import com.evgen.service.UserService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);

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
        UserEntity userEntity = userDAO.get(userId);

        if (userEntity == null){
            return null;
        } else {
            return modelMapper.map(userEntity, UserDTO.class);
        }
    }

    @Override
    public RoleDTO getRole(int roleId) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roleDAO.get(roleId), RoleDTO.class);
    }

    @Override
    public RoleDTO getRoleByName(String roleName) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roleDAO.getRoleByName(roleName), RoleDTO.class);
    }

    @Override
    public List<TicketDTO> getTickets(int userId) {

        List<TicketEntity>  ticketEntities = userDAO.get(userId).getTickets();

        List<TicketDTO> dtos = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        ticketEntities.forEach(item -> dtos.add(modelMapper.map(item, TicketDTO.class)));

        return dtos;
    }

    @Override
    public void changeRole(int userId, String roleName) {
        UserEntity userEntity = userDAO.get(userId);
        RoleEntity roleEntity = roleDAO.getRoleByName(roleName);

        userEntity.setRole(roleEntity);
        userDAO.update(userEntity);
    }
}
