package com.example.matestime;


import com.example.matestime.service.UserService;
import com.example.matestime.models.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//JACKSON, JSON

@RestController
@RequestMapping("/api/users/")                  //nowy kontroler
@CrossOrigin(origins = "*")
public class UserController {
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
    public void delete(@PathVariable int id) {
        userService.deleteRelatedId(id);
        userService.delete(id);
    }
}
//MySql nie pozwoli usunąć gdy sa inne powiązania w innych tablicach
//@RequestParam - wartosc po znaku zapytania
//@PathVaraible - wartosc po slaszu
//@RequestBody - dodatkowe wartosci