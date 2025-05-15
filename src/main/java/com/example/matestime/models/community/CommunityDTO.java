package com.example.matestime.models.community;

import com.example.matestime.models.user.User;

import java.util.List;

public class CommunityDTO {

    private int id;

    private String name;

    private List<User> users;

    public CommunityDTO(int id, String name, List<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public CommunityDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "CommunityDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users.toString() +
                '}';
    }
}