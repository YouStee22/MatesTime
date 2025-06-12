package com.example.matestime;


import com.example.matestime.models.community.Community;
import com.example.matestime.dao.CommunityDao;
import com.example.matestime.models.community.CommunityDTO;
import com.example.matestime.models.community.CommunityDefinition;
import com.example.matestime.service.CommunityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/community/")
@CrossOrigin(origins = "*")
public class CommunityController {

    private final CommunityDao communityDao;

    private final CommunityService communityService;

    private final Logger logger = LogManager.getLogger(CommunityController.class);

    public CommunityController(CommunityDao communityDao, CommunityService communityService) {
        this.communityDao = communityDao;
        this.communityService = communityService;
    }

    @PostMapping("/add")
    public void addCommunity(@RequestBody Community community) {
        communityDao.addCommunity(community.getName());
    }

    @PostMapping("/addDto")
    public void addCommunityDefinition(@RequestBody CommunityDefinition communityDefinition) {
        System.out.println(communityDefinition);

        int idOfNewCommunity = communityDao.addCommunity(communityDefinition.getName());

        communityDefinition.getUsers().forEach(user -> {
            communityService.getUserCommunitiesDao().addUserToCommunity(user, idOfNewCommunity);
        });

        logger.info("Received community definition: {}", communityDefinition);
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
