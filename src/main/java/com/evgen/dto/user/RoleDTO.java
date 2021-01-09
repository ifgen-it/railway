package com.evgen.dto.user;

import com.evgen.entity.user.RoleEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoleDTO implements Serializable {

    private int roleId;

    private String roleName;

    private List<Integer> users;

    public RoleDTO() {

    }

    public RoleDTO(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public static RoleDTO entityToDTO(RoleEntity entity){
        RoleDTO dto = new RoleDTO(entity.getRoleId(), entity.getRoleName());

        List<Integer> users = new ArrayList<>();
        entity.getUsers().forEach(u -> users.add(u.getUserId()));
        dto.setUsers(users);
        return dto;
    }
    public static RoleEntity dtoToEntity(RoleDTO dto){
        return new RoleEntity(dto.getRoleId() ,dto.getRoleName());
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Integer> getUsers() {
        return users;
    }

    public void setUsers(List<Integer> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
