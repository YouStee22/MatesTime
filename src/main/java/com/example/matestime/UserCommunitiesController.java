package com.example.matestime;


import com.example.matestime.dao.UserCommunitiesDao;
import com.example.matestime.userCommunities.UserCommunities;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/userCommunities/")
@CrossOrigin(origins = "*")
public class UserCommunitiesController {                                            //może tak aby zwracać grupe i podświetlać ich członków
                                                                                    //z relacji?
    private UserCommunitiesDao userCommunitiesDao;

    public UserCommunitiesController(UserCommunitiesDao userCommunitiesDao) {
        this.userCommunitiesDao = userCommunitiesDao;
    }

    @GetMapping("/all")
    public List<UserCommunities> getAllUsers() {
        System.out.println(userCommunitiesDao.getAll());
        return userCommunitiesDao.getAll();
    }


}
