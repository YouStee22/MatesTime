package com.example.matestime;

import com.example.matestime.models.community.Community;
import com.example.matestime.models.community.CommunityDTO;
import com.example.matestime.models.community.CommunityDefinition;
import com.example.matestime.service.CommunityService;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/community/")
@CrossOrigin(origins = "*")
public class CommunityController {                              //controller bez dao

    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @CrossOrigin
    @PutMapping("/update")                                   //jak dodać jeszcze aktuaizowanie powiązań gdy zmienią sie uczestnicy?
    public void updateCommunity(@RequestBody CommunityDefinition communityDefinition) {
        communityService.updateCommunity(communityDefinition);
    }

    @PostMapping("/add")
    public void addCommunity(@RequestBody Community community) {
        communityService.addCommunity(community);
    }

    @PostMapping("/addDto")
    public void addCommunityDefinition(@RequestBody CommunityDefinition communityDefinition) {          //Tutaj poprawic
        communityService.addCommunityDefinition(communityDefinition);// + sprawdzenia uzytkowika lub powiazanie jesli istneije
    }

    @GetMapping("/all")
    public List<Community> getAllCommunity() {                        
        return communityService.getAll();
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

    @DeleteMapping("/{id}")
    @Transaction
    public void deleteCommunityById(@PathVariable int id) {
        communityService.deleteCommunity(id);
    }

}
