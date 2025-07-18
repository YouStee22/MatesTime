package com.example.matestime.service;

import com.example.matestime.dao.CommunityDao;
import com.example.matestime.dao.UserCommunitiesDao;
import com.example.matestime.dao.UserDao;
import com.example.matestime.models.community.Community;
import com.example.matestime.models.community.CommunityDTO;
import com.example.matestime.models.user.User;
import com.example.matestime.models.userCommunities.UserCommunity;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

import java.util.List;
import java.util.Optional;
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

    public Community getCommunityByName(String name) {
        return communityDao.getCommunityByName(name);
    }

    public void deleteCommunityFromCommunityRelation(int community_id) {
        userCommunitiesDao.deleteCommunityFromCommunityRelation(community_id);
    }

    public CommunityDTO getCommunityById(int communityId) {

        Community community = Optional.ofNullable(communityDao.getCommunityById(communityId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Community not found with ID " + communityId));

        List<UserCommunity> userCommunities = userCommunitiesDao.getUserListById(communityId);

        List<Integer> listOfUsers = userCommunities.stream()
                .map(UserCommunity::getUserId)
                .collect(Collectors.toList());

        List<User> userList = listOfUsers.isEmpty() ? Collections.emptyList()
                : Optional.ofNullable(userDao.getUsersByCommunityId(listOfUsers))
                .orElse(Collections.emptyList());

        CommunityDTO communityDTO = new CommunityDTO(communityId, community.getName(), userList);

        return communityDTO;
    }

    public UserCommunitiesDao getUserCommunitiesDao() {
        return userCommunitiesDao;
    }

    public void deleteCommunity(int id) {
        communityDao.deleteCommunityById(id);
    }
}
