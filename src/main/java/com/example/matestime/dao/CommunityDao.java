package com.example.matestime.dao;

import com.example.matestime.models.community.Community;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;


@RegisterBeanMapper(Community.class)
public interface CommunityDao {

    @SqlUpdate("INSERT INTO communities (name) VALUES (:name)")
    void addCommunitiy(@Bind("name") String name);

    @SqlUpdate("INSERT INTO communities (name) VALUES (:name)")                 //dwie te same metody
    @GetGeneratedKeys
    int addCommunity(@Bind("name") String name);

    @SqlQuery("SELECT * FROM communities")
    List<Community> getAll();

    @SqlQuery("SELECT * FROM communities WHERE id = :id")
    Community getCommunityById(@Bind("id") int id);

    @SqlQuery("SELECT * FROM communities WHERE name = :name")
    Community getCommunityByName(@Bind("name") String name);
}
