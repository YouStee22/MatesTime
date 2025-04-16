package com.example.matestime;


import com.example.matestime.dao.UserDao;
import com.example.matestime.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userDao.getById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @GetMapping("/{id}")
    public void delete(@PathVariable int id) {
        userDao.getById(id);
    }
}