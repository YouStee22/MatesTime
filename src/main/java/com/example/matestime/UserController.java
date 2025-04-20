package com.example.matestime;


import com.example.matestime.dao.GroupDao;
import com.example.matestime.dao.UserDao;
import com.example.matestime.group.Group;
import com.example.matestime.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//JACKSON, JSON

@RestController
@RequestMapping("/api/users/")
@CrossOrigin(origins = "*")
public class UserController {                                           //Co z CrossOrigin i dodawaniem?
    //FlatService -> schedule -> 80 li ijka
    //Flyway -> pocytac (flatmates folder flyway)
    //dodać wartswe serwisową, serwis rozwamia  z dao dla testów

    private final UserDao userDao;

    private final GroupDao groupDao;

    public UserController(UserDao userDao, GroupDao groupDao) {
        this.userDao = userDao;
        this.groupDao = groupDao;
    }

    @PostMapping("/add")
    public void addUser(@RequestBody User user) {
        userDao.addUser(user.getName(), user.getEmail());
    }

    @GetMapping("/{id}")                                                //status code - 200, 404 itd...
    public User getUserById(@PathVariable int id) {
        return userDao.getById(id)                                        //Czy sytuacja spodziewana
                .orElseThrow(() -> new RuntimeException("User not found"));         //czy to dlaetego ze RuntimeException jest na wyjsciu?
//        return userDao.getById(id);                                               //bo nigdy nie ma user not found
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {//@RequestParam - wartosc po znaku zapytania
        userDao.deleteById(id);                        //@PathVaraible - wartosc po slaszu
    }                                                  //@RequestBody - dodatkowe wartosci

    @PostMapping("/addGroup")
    public void addGroup(@RequestBody Group group) {
        groupDao.addGroup(group.getId(), group.getName());
    }

    @GetMapping("/groups")
    public List<Group> getAllGroups() {                         //Nie działa a powinno
        return groupDao.getAll();
    }
}