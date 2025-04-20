package com.example.matestime.dao;

import com.example.matestime.group.Group;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;


@RegisterBeanMapper(Group.class)
public interface GroupDao {

    @SqlQuery("INSERT INTO groups (id, name) VALUES (:id, :name)")
    void addGroup(@Bind("id") int id, @Bind("name") String name);

    @SqlQuery("SELECT * FROM groups")
    List<Group> getAll();
}
