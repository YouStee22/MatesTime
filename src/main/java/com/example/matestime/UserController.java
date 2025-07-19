package com.example.matestime;


import com.example.matestime.service.UserService;
import com.example.matestime.models.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//JACKSON, JSON

@RestController
@RequestMapping("/api/users/")
//@CrossOrigin(origins = "*")
public class UserController {

   private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @PostMapping("/add")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }
}
//MySql nie pozwoli usunąć gdy sa inne powiązania w innych tablicach
//@RequestParam - wartosc po znaku zapytania
//@PathVaraible - wartosc po slaszu
//@RequestBody - dodatkowe wartosci