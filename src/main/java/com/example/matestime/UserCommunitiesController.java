package com.example.matestime;


import com.example.matestime.dao.UserCommunitiesDao;
import com.example.matestime.models.userCommunities.UserCommunity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/userCommunities/")
@CrossOrigin(origins = "*")
public class UserCommunitiesController {                    //dodac serwis

    private UserCommunitiesDao userCommunitiesDao;

    public UserCommunitiesController(UserCommunitiesDao userCommunitiesDao) {
        this.userCommunitiesDao = userCommunitiesDao;
    }

    @GetMapping()
    public List<UserCommunity> getAllUsersCommunity() {
        System.out.println(userCommunitiesDao.getAll());
        return userCommunitiesDao.getAll();
    }

    @GetMapping("/user/{userId}")
    public List<Integer> getCommunitiesIds(@PathVariable("userId") int userId) {
        return userCommunitiesDao.getCommunityIdsForUserId(userId);
    }
}
