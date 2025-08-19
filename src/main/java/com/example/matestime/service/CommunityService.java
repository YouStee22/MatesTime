package com.example.matestime.service;

import com.example.matestime.CommunityController;
import com.example.matestime.dao.CommunityDao;
import com.example.matestime.dao.UserCommunitiesDao;
import com.example.matestime.dao.UserDao;
import com.example.matestime.models.CommunityAlreadyExistsException;
import com.example.matestime.models.MissingCommunityException;
import com.example.matestime.models.MissingDataException;
import com.example.matestime.models.community.Community;
import com.example.matestime.models.community.CommunityDTO;
import com.example.matestime.models.community.CommunityDefinition;
import com.example.matestime.models.user.User;
import com.example.matestime.models.userCommunities.UserCommunity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service                                                //tak aby przy przekazywaniu obiektu nke zmienac warstwy serwisowej
public class CommunityService {                         //Zawsze musi być konwencja controller -> service -> ( repository) -> dao?

    private final UserCommunitiesDao userCommunitiesDao;        // (final) sa mnijesze szanse ze obiekt zosatnie zmodyfikowany

    private final CommunityDao communityDao;

    private final UserDao userDao;

    private final Logger logger = LogManager.getLogger(CommunityController.class);

    public CommunityService(final UserCommunitiesDao userCommunitiesDao, final CommunityDao communityDao, final UserDao userDao) {
        this.userCommunitiesDao = userCommunitiesDao;
        this.communityDao = communityDao;
        this.userDao = userDao;
    }

    public void addCommunity(Community community) {
        if (communityDao.existsByName(community.getName())) {
            throw new IllegalArgumentException("Community already exists");
        }

        communityDao.addCommunity(community.getName());
    }

    public List<Community> getAll() {
        return communityDao.getAll();
    }

    public void addCommunityDefinition(@RequestBody CommunityDefinition communityDefinition) {
        if (communityDao.existsByName(communityDefinition.getName())) {
            throw new CommunityAlreadyExistsException("Community already exists");
        } else {
            int idOfNewCommunity = communityDao.addCommunity(communityDefinition.getName());

            communityDefinition.getUsers().forEach(user -> {
                userCommunitiesDao.addUserToCommunity(user, idOfNewCommunity);
            });
        }

        logger.info("Received community definition: {}", communityDefinition);
    }

    public void updateCommunity(CommunityDefinition communityDefinition) {
        if (!communityDao.existsByName(communityDefinition.getName())) {
            throw new MissingCommunityException("Community does not exists");
        } else {
            communityDao.updateCommunity(communityDefinition);


            communityDefinition.getUsers().forEach(user -> {
                userCommunitiesDao.upsertUserCommunity(user, communityDefinition.getId());      //upset
            });
        }
    }

    public Community getCommunityByName(String name) {
        if (communityDao.existsByName(name)) {
            return communityDao.getCommunityByName(name);
        }
        throw new MissingCommunityException("Community does not exists");
    }

    public CommunityDTO getCommunityById(int communityId) {

        Community community = Optional.ofNullable(communityDao.getCommunityById(communityId))
                .orElseThrow(() -> new MissingDataException("Community not found with ID: " + communityId));

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
        if (communityDao.existsById(id)) {
            userCommunitiesDao.deleteCommunityFromCommunityRelation(id);
            communityDao.deleteCommunityById(id);
        } else {
            throw new MissingCommunityException("Community does not exists");
        }
    }
}
