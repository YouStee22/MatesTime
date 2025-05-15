package com.example.matestime;


import com.example.matestime.models.community.Community;
import com.example.matestime.dao.CommunityDao;
import com.example.matestime.models.community.CommunityDTO;
import com.example.matestime.service.CommunityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/community/")
@CrossOrigin(origins = "*")
public class CommunityController {

    private final CommunityDao communityDao;

    private final CommunityService communityService;

    public CommunityController(CommunityDao communityDao, CommunityService communityService) {
        this.communityDao = communityDao;
        this.communityService = communityService;
    }

    @PostMapping("/addCommunity")
    public void addCommunity(@RequestBody Community community) {
        communityDao.addCommunitiy(community.getId(), community.getName());
    }

    @GetMapping("/all")
    public List<Community> getAllCommunity() {                        
        return communityDao.getAll();
    }

    @GetMapping("/getUsers/{id}")
    public CommunityDTO getCommunity(@PathVariable int id) {
        CommunityDTO communityDTO  = communityService.getCommunityById(id);
        System.out.println(communityDTO);
        return communityDTO;
    }
}
