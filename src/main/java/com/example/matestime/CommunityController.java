package com.example.matestime;


import com.example.matestime.dao.UserCommunitiesDao;
import com.example.matestime.models.community.Community;
import com.example.matestime.dao.CommunityDao;
import com.example.matestime.models.community.CommunityDTO;
import com.example.matestime.models.community.CommunityDefinition;
import com.example.matestime.service.CommunityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/community/")
@CrossOrigin(origins = "*")
public class CommunityController {

    private final CommunityDao communityDao;

    private final UserCommunitiesDao userCommunitiesDao;

    private final CommunityService communityService;

    public CommunityController(CommunityDao communityDao, UserCommunitiesDao userCommunitiesDao, CommunityService communityService) {
        this.communityDao = communityDao;
        this.userCommunitiesDao = userCommunitiesDao;
        this.communityService = communityService;
    }

    //może przyjść obiekt community z samą nazwą bez osób i z listą osób
    @PostMapping("/add")
    public void addCommunity(@RequestBody Community community) {
        communityDao.addCommunitiy(community.getName());
    }

    @PostMapping("/addDto")
    public void addCommunityDefinition(@RequestBody CommunityDefinition communityDefinition) {
        System.out.println(communityDefinition);

        int idOfNewCommunity = communityDao.addCommunity(communityDefinition.getName());

        communityDefinition.getUsers().forEach(user -> {
            userCommunitiesDao.addCommunitiesIds(user, idOfNewCommunity);
        });

        //batch insert
    }

    @GetMapping("/all")
    public List<Community> getAllCommunity() {                        
        return communityDao.getAll();
    }

    @GetMapping("/{id}")
    public CommunityDTO getCommunityById(@PathVariable int id) {
        System.out.println(communityService.getCommunityById(id).toString());
        return communityService.getCommunityById(id);
    }

    @GetMapping("/communities")
    public Community getCommunityByName(@RequestParam String name) {
        System.out.println(communityService.getCommunityByName(name).toString());
        return communityService.getCommunityByName(name);
    }



}
