package com.example.matestime;


import com.example.matestime.dao.UserDao;
import com.example.matestime.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//JACKSON, JSON

@RestController
@RequestMapping("/api/users/")
@CrossOrigin(origins = "*")
public class UserController {
    //FlatService -> schedule -> 80 li ijka
    //Flyway -> pocytac (flatmates folder flyway)
    //dodać wartswe serwisową, serwis rozwamia  z dao dla testów

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping("/add")
    public void addUser(@RequestBody User user) {
        userDao.addUser(user.getName(), user.getEmail());
    }

    @GetMapping("/{id}")                                                //status code - 200, 404 itd...
    public User getUserById(@PathVariable int id) {
        return userDao.getById(id)                                        //Czy sytuacja spodziewana
                .orElseThrow(() -> new RuntimeException("User not found"));
//        return userDao.getById(id);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {//@RequestParam - wartosc po znaku zapytania
        userDao.deleteById(id);                        //@PathVaraible - wartosc po slaszu
    }                                               //@RequestBody - dodatkowe wartosci
}