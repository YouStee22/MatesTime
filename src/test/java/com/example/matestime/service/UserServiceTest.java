package com.example.matestime.service;

import com.example.matestime.dao.UserDao;
import com.example.matestime.models.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    private User user = new User(2,"Jan", "Kowalski");

    @Test
    public void testAddingUserTest() {
        userService.addUser(user);

        verify(userDao).addUser(user.getName(), user.getEmail());
    }

    //shouldGetAllUsers

    @Test
    public void getUserByIdTest() {         //drugi test jezeli nie zwroci nic
        when(userDao.getById(2)).thenReturn(Optional.of(user));

        User actualUser = userService.getUserById(2);

        assertEquals(user, actualUser);
        verify(userDao).getById(2);
    }

    @Test
    public void getAllUsersTest() {
        List<User> expectedUsers = Arrays.asList(user);

        when(userDao.getAll()).thenReturn(expectedUsers);

        assertEquals(expectedUsers, userService.getAllUsers());
    }

    @Test
    public void deleteUserByIdTest() {
        int atx = 2;
        userService.delete(atx);

        verify(userDao).deleteById(atx);
    }
}