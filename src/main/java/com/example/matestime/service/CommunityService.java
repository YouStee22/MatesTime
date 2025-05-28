package com.example.matestime.service;

import com.example.matestime.dao.CommunityDao;
import com.example.matestime.dao.UserCommunitiesDao;
import com.example.matestime.dao.UserDao;
import com.example.matestime.models.community.Community;
import com.example.matestime.models.community.CommunityDTO;
import com.example.matestime.models.user.User;
import com.example.matestime.models.userCommunities.UserCommunity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

import java.util.ArrayList;
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

    public CommunityDTO getCommunityById(int communityId) {                         //walidacja czy id istnieje rowieniz
        Community community = communityDao.getCommunityById(communityId);

        if (community == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Community with ID " + communityId + " not found");
        }

        System.out.println(community.getName());
        List<UserCommunity> userCommunities = Optional.ofNullable(userCommunitiesDao.getUserListById(communityId)).orElse(Collections.emptyList());


        List<Integer> listOfUsers = userCommunities.stream()
                .map(UserCommunity::getUserId)
                .collect(Collectors.toList());

        System.out.println("List of users ids: " + listOfUsers);

        List<User> userList = Optional.ofNullable(userDao.getUsersByCommunityId(listOfUsers))
                .orElse(Collections.emptyList());

        System.out.println("list of users: " + userList);

        CommunityDTO communityDTO = new CommunityDTO(communityId, community.getName(), userList);
        System.out.println("Final obj " + communityDTO);

        return communityDTO;                ///dlaczego po szukaniu id community która nie zawiera listy uzytkownikow nie zwraca obiektu?
    }


}
