package com.example.matestime.dao;

import com.example.matestime.models.userCommunities.UserCommunity;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterBeanMapper(UserCommunity.class)
public interface UserCommunitiesDao {

//    @SqlUpdate("INSERT INTO user_communities (id, name) VALUES (:id, :name)")
//    void addCommunitiy(@Bind("id") int id, @Bind("name") String name);

    @SqlQuery("SELECT * FROM user_communities")
    List<UserCommunity> getAll();

    @SqlQuery("SELECT * FROM user_communities WHERE community_id = :communityId")
    List<UserCommunity> getUserListById(@Bind("communityId") int communityId);

    @SqlUpdate("INSERT INTO user_communities (user_id, community_id) VALUES (:user_id, :community_id)")
    void addCommunitiesIds(@Bind("user_id") int userId, @Bind("community_id") int communityId);
}
