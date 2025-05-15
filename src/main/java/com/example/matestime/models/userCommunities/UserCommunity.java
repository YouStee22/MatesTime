package com.example.matestime.models.userCommunities;

public class UserCommunity {

    private int userId;

    private int communityId;

    public UserCommunity() {
    }

    public UserCommunity(int userId, int communityId) {
        this.userId = userId;
        this.communityId = communityId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }
}
