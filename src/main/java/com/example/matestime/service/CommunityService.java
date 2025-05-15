package com.example.matestime.service;

import com.example.matestime.dao.CommunityDao;
import com.example.matestime.dao.UserCommunitiesDao;
import com.example.matestime.dao.UserDao;
import com.example.matestime.models.community.Community;
import com.example.matestime.models.community.CommunityDTO;
import com.example.matestime.models.user.User;
import com.example.matestime.models.userCommunities.UserCommunity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunityService {

    private final UserCommunitiesDao userCommunitiesDao;        // (final) sa mnijesze szanse ze obiekt zosatnie zmodyfikowany

    private final CommunityDao communityDao;

    private final UserDao userDao;

    public CommunityService(final UserCommunitiesDao userCommunitiesDao, final CommunityDao communityDao, final UserDao userDao) {
        //zapobiega modyfickajci obektow w konsturktoze
        this.userCommunitiesDao = userCommunitiesDao;
        this.communityDao = communityDao;
        this.userDao = userDao;
    }

    public CommunityDTO getCommunityById(int communityId) {
        Community community = communityDao.getCommunityById(communityId);
        System.out.println(community);
        List<UserCommunity> userCommunities = userCommunitiesDao.getUserListById(communityId);   //stream na liste
        System.out.println(userCommunities);
        List<Integer> listOfUsers = userCommunities.stream()
                .map(UserCommunity::getUserId)
                .collect(Collectors.toList());

        List<User> userList = userDao.getUsersByCommunityId(listOfUsers);

        CommunityDTO communityDTO = new CommunityDTO(communityId, community.getName(), userList);

        return communityDTO;                //jak tutaj debugowac?
    }


}
