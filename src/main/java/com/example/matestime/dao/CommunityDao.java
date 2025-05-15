package com.example.matestime.dao;

import com.example.matestime.models.community.Community;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;


@RegisterBeanMapper(Community.class)
public interface CommunityDao {

    @SqlUpdate("INSERT INTO communities (id, name) VALUES (:id, :name)")
    void addCommunitiy(@Bind("id") int id, @Bind("name") String name);

    @SqlQuery("SELECT * FROM communities")
    List<Community> getAll();

    @SqlQuery("SELECT * FROM communities WHERE id = :id")
    Community getCommunityById(@Bind("id") int id);
}
