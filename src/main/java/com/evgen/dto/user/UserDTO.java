package com.evgen.dto.user;

import com.evgen.entity.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDTO implements Serializable {

    private int userId;

    private RoleDTO role;

    private String firstName;

    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    private Date birthday;

    private String email;

    private String password;

    private List<Integer> tickets;


    public static UserDTO entityToDTO(UserEntity entity){
        UserDTO dto = new UserDTO(entity.getUserId(),
                RoleDTO.entityToDTO(entity.getRole()),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthday(),
                entity.getEmail(),
                entity.getPassword());

        List<Integer> tickets = new ArrayList<>();
        entity.getTickets().forEach(t -> tickets.add(t.getTicketId()));
        dto.setTickets(tickets);
        return dto;
    }
    public static UserEntity dtoToEntity(UserDTO dto){
        return new UserEntity(dto.getUserId(),
                RoleDTO.dtoToEntity(dto.getRole()),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getBirthday(),
                dto.getEmail(),
                dto.getPassword());
    }

    public UserDTO(int userId, RoleDTO role, String firstName, String lastName, Date birthday, String email, String password) {
        this.userId = userId;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }

    public UserDTO() {

    }

    public List<Integer> getTickets() {
        return tickets;
    }

    public void setTickets(List<Integer> tickets) {
        this.tickets = tickets;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", role=" + role.getRoleName() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
