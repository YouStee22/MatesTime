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

    @SqlUpdate("DELETE FROM user_communities WHERE user_id = :id")
    void deleteUserFromCommunity(@Bind("id") int id);

    @SqlUpdate("DELETE FROM user_communities WHERE community_id = :community_id")
    void deleteCommunityFromCommunityRelation(@Bind("community_id") int community_id);

    @SqlQuery("SELECT * FROM user_communities WHERE community_id = :communityId")
    List<UserCommunity> getUserListById(@Bind("communityId") int communityId);

    @SqlUpdate("INSERT INTO user_communities (user_id, community_id) VALUES (:user_id, :community_id)")
    void addUserToCommunity(@Bind("user_id") int userId, @Bind("community_id") int communityId);

//    @SqlUpdate("UPDATE user_communities SET community_id = :community_id WHERE user_id = :user_id")
//    void updateCommunityForUser(@Bind("user_id") int userId, @Bind("community_id") int communityId);

    @SqlUpdate("""
    INSERT INTO user_communities (user_id, community_id)
    VALUES (:user_id, :community_id)
    ON DUPLICATE KEY UPDATE community_id = VALUES(community_id)
    """)
    void upsertUserCommunity(@Bind("user_id") int userId, @Bind("community_id") int communityId);
}
