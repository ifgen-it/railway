package com.evgen.dto.user;

import com.evgen.entity.user.UserEntity;

import java.io.Serializable;
import java.util.List;

public class RoleDTO implements Serializable {

    private int roleId;

    private String roleName;

    private List<UserDTO> users;

    public RoleDTO() {
        roleId = 2;
        roleName = "ROLE_USER";
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

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", users=" + users +
                '}';
    }
}
