package com.example.matestime;


import com.example.matestime.community.Community;
import com.example.matestime.dao.CommunityDao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/community/")
@CrossOrigin(origins = "*")
public class CommunityController {

    private final CommunityDao communityDao;

    public CommunityController(CommunityDao communityDao) {
        this.communityDao = communityDao;
    }

    @PostMapping("/addCommunity")
    public void addCommunity(@RequestBody Community community) {
        communityDao.addCommunitiy(community.getId(), community.getName());
    }

    @GetMapping("/all")
    public List<Community> getAllCommunity() {                         //Nie działa a powinno
        return communityDao.getAll();
    }
}
