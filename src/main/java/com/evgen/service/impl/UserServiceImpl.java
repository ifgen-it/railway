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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private UserDAO userDAO;

    private RoleDAO roleDAO;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<UserEntity> entities = userDAO.getAll();
        List<UserDTO> dtos = new ArrayList<>();

        for (UserEntity user : entities ) {
            UserDTO userDTO = UserDTO.entityToDTO(user);
            dtos.add(userDTO);
        }

        return dtos;
    }

    @Override
    public UserDTO getByEmail(String email) {

        UserEntity userEntity =  userDAO.getByEmail(email);

        if (userEntity == null)
            return  null;

        UserDTO userDTO = UserDTO.entityToDTO(userEntity);
        return userDTO;
    }

    @Override
    public int addUser(UserDTO user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = UserDTO.dtoToEntity(user);

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

        entities.forEach(item -> dtos.add(RoleDTO.entityToDTO(item)));

        return dtos;
    }

    @Override
    public UserDTO getUser(int userId) {

        UserEntity userEntity = userDAO.get(userId);

        if (userEntity == null){
            return null;
        } else {
            return UserDTO.entityToDTO(userEntity);
        }
    }

    @Override
    public RoleDTO getRole(int roleId) {

        return RoleDTO.entityToDTO(roleDAO.get(roleId));
    }

    @Override
    public RoleDTO getRoleByName(String roleName) {

        return RoleDTO.entityToDTO(roleDAO.getRoleByName(roleName));
    }

    @Override
    public List<TicketDTO> getTickets(int userId) {

        List<TicketEntity>  ticketEntities = userDAO.get(userId).getTickets();

        List<TicketDTO> dtos = new ArrayList<>();
        ticketEntities.forEach(item -> dtos.add(TicketDTO.entityToDTO(item)));

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
