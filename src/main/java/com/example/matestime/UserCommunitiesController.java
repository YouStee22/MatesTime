package com.example.matestime;


import com.example.matestime.dao.UserCommunitiesDao;
import com.example.matestime.models.userCommunities.UserCommunity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/userCommunities/")
@CrossOrigin(origins = "*")
public class UserCommunitiesController {

    private UserCommunitiesDao userCommunitiesDao;

    public UserCommunitiesController(UserCommunitiesDao userCommunitiesDao) {
        this.userCommunitiesDao = userCommunitiesDao;
    }

    @GetMapping("/all")
    public List<UserCommunity> getAllUsers() {
        System.out.println(userCommunitiesDao.getAll());
        return userCommunitiesDao.getAll();
    }
}
