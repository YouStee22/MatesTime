package com.example.matestime.models.community;


import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class CommunityDefinition {          //kalsa modelowa

    private final String name;

    private final List<Integer> users;

    public CommunityDefinition(final String name, final List<Integer> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("users", users)
                .toString();
    }
}