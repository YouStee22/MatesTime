package com.example.matestime.service;

import com.example.matestime.dao.UserCommunitiesDao;
import com.example.matestime.dao.UserDao;
import com.example.matestime.models.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    private final UserCommunitiesDao userCommunitiesDao;

    public UserService(UserDao userDao, UserCommunitiesDao userCommunitiesDao) {
        this.userDao = userDao;
        this.userCommunitiesDao = userCommunitiesDao;
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
        userCommunitiesDao.deleteUserFromCommunity(id);
        userDao.deleteById(id);
    }
}
