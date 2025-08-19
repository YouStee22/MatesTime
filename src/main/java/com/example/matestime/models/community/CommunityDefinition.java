package com.example.matestime.models.community;


import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class CommunityDefinition {          //kalsa modelowa

    private final int id;

    private final String name;

    private final List<Integer> users;

    private final String description;

    public CommunityDefinition(int id, String name, List<Integer> users, String description) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getUsers() {
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