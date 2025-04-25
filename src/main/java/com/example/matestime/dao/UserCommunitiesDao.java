package com.example.matestime.dao;

import com.example.matestime.userCommunities.UserCommunities;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(UserCommunities.class)
public interface UserCommunitiesDao {

//    @SqlUpdate("INSERT INTO user_communities (id, name) VALUES (:id, :name)")
//    void addCommunitiy(@Bind("id") int id, @Bind("name") String name);

    @SqlQuery("SELECT * FROM user_communities")
    List<UserCommunities> getAll();
}
