package com.example.matestime.service;

import com.example.matestime.dao.UserDao;
import com.example.matestime.models.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(User user) {
        userDao.addUser(user.getName(), user.getEmail());
    }

    public User getUserById(int id) {
        return userDao.getById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public void delete(int id) {
        userDao.deleteById(id);
    }
}
