package com.example.matestime.models.community;

import com.example.matestime.models.user.User;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class CommunityDTO {                  //kalsa modelowa

    private final int id;

    private final String name;

    private final List<User> users;

    public CommunityDTO(final int id, final String name, final List<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("users", users)
                .toString();
    }
}