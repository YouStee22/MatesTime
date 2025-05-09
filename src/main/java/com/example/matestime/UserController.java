package com.example.matestime;


import com.example.matestime.community.Community;
import com.example.matestime.dao.CommunityDao;
import com.example.matestime.dao.UserDao;
import com.example.matestime.service.UserService;
import com.example.matestime.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//JACKSON, JSON

@RestController
@RequestMapping("/api/users/")                  //nowy kontroler
@CrossOrigin(origins = "*")
public class UserController {                                           //Co z CrossOrigin i dodawaniem?
    //FlatService -> schedule -> 80 li ijka
    //Flyway -> pocytac (flatmates folder flyway)
    //dodać wartswe serwisową, serwis rozwamia  z dao dla testów

   private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping("/{id}")                                                //status code - 200, 404 itd...
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {//@RequestParam - wartosc po znaku zapytania
        userService.delete(id);                     //@PathVaraible - wartosc po slaszu
    }                                                  //@RequestBody - dodatkowe wartosci
}