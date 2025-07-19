package com.example.matestime.service;

import com.example.matestime.dao.UserCommunitiesDao;
import com.example.matestime.dao.UserDao;
import com.example.matestime.models.DuplicateUserException;
import com.example.matestime.models.InvalidEmailException;
import com.example.matestime.models.MissingDataException;
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

    public void updateUser(User user) {
        //sprawdzac czy user istnieje
        userDao.updateUser(user);
    }

    public void addUser(User user) {                        //***
        if (user.getEmail().equals("123@wp.pl")) {
            throw new InvalidEmailException();
        }

        if (userDao.userExistsByEmail(user.getEmail())) {
            throw new DuplicateUserException();
        } else {
            userDao.addUser(user.getName(), user.getEmail());
        }
    }

    public User getUserById(int id) {
        return userDao.getById(id)
                .orElseThrow(() -> new MissingDataException("User not found with ID: " + id));
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public void delete(int id) {
        userCommunitiesDao.deleteUserFromCommunity(id);
        userDao.deleteById(id);
    }
}
