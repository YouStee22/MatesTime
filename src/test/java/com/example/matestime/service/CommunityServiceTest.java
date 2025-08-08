package com.example.matestime.service;

import com.example.matestime.dao.CommunityDao;
import com.example.matestime.dao.UserCommunitiesDao;
import com.example.matestime.dao.UserDao;
import com.example.matestime.models.community.Community;
import com.example.matestime.models.community.CommunityDTO;
import com.example.matestime.models.community.CommunityDefinition;
import com.example.matestime.models.user.User;
import com.example.matestime.models.userCommunities.UserCommunity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CommunityServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private CommunityDao communityDao;

    @Mock
    private UserCommunitiesDao userCommunitiesDao;

    @InjectMocks
    private CommunityService communityService;

    private Community community = new Community(1, "Piłka nożna");

    private CommunityDefinition definition = new CommunityDefinition(1, "Chess Club", List.of(1, 2));


    @Test
    public void testAddingCommunityTest() {
        communityService.addCommunity(community);

        verify(communityDao).addCommunity(community.getName());
    }

    @Test
    public void testAddingCommunityThatAlreadyExists() {
        when(communityDao.existsByName("Piłka nożna")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () ->
                        communityService.addCommunity(community),
                "Community already exists"
        );

        verify(communityDao).existsByName("Piłka nożna");
    }

    @Test
    public void testAddingCommunityDefinition() {
        when(communityDao.addCommunity(definition.getName())).thenReturn(42);
        communityService.addCommunityDefinition(definition);

        verify(communityDao).addCommunity(definition.getName());
        verify(userCommunitiesDao).addUserToCommunity(1, 42);
        verify(userCommunitiesDao).addUserToCommunity(2, 42);
    }

    @Test
    public void testUpdateCommunity() {
        communityService.updateCommunity(definition);

        verify(communityDao).updateCommunityName(definition.getId(), definition.getName());
        verify(userCommunitiesDao).upsertUserCommunity(1, definition.getId());
        verify(userCommunitiesDao).upsertUserCommunity(2, definition.getId());
    }

    @Test
    public void testGetCommunityById() {
        int communityId = 10;

        List<UserCommunity> userCommunities = List.of(
                new UserCommunity(1, communityId),
                new UserCommunity(2, communityId)
        );

        List<User> users = List.of(
                new User(1, "Alice", "asdasd@wp.pl"),
                new User(2, "Bob", "sdasd@wp.pl")
        );

        when(communityDao.getCommunityById(communityId)).thenReturn(community);
        when(userCommunitiesDao.getUserListById(communityId)).thenReturn(userCommunities);
        when(userDao.getUsersByCommunityId(List.of(1, 2))).thenReturn(users);

        CommunityDTO dto = communityService.getCommunityById(communityId);

        assertEquals(communityId, dto.getId());
        assertEquals("Piłka nożna", dto.getName());
        assertEquals(users, dto.getUsers());

        verify(communityDao).getCommunityById(communityId);
        verify(userCommunitiesDao).getUserListById(communityId);
        verify(userDao).getUsersByCommunityId(List.of(1, 2));
    }

    @Test
    void testDeleteCommunity() {
        int communityId = 42;

        communityService.deleteCommunity(communityId);

        verify(userCommunitiesDao).deleteCommunityFromCommunityRelation(communityId);
        verify(communityDao).deleteCommunityById(communityId);
    }
}