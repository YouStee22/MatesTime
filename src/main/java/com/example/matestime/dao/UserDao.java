package com.example.matestime.dao;


import com.example.matestime.user.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(User.class)
public interface UserDao {

    @SqlQuery("SELECT * FROM users WHERE id = :id")
    Optional<User> getById(@Bind("id") int id);

    @SqlQuery("SELECT * FROM users")
    List<User> getAll();
}