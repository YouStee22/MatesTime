package com.example.matestime.service;

import com.example.matestime.dao.UserDao;
import com.example.matestime.models.InvalidEmailException;
import com.example.matestime.models.MissingDataException;
import com.example.matestime.models.UserDoesNotExists;
import com.example.matestime.models.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    //pokryc testami

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

    @Test
    public void testUpdatingUserThatDontExistsTest() {
        User user = new User(99,"Jan", "Kowalski");
        when(userDao.userExistsById(user.getId())).thenReturn(false);

        UserDoesNotExists exists = assertThrows(UserDoesNotExists.class, () -> userService.updateUser(user));

        assertEquals("User not found with ID: 99", exists.getMessage());
        verify(userDao, never()).updateUser(any());
    }

    @Test
    public void testAddingUserThatExistsTest() {
        User user = new User(2,"Jan", "123@wp.pl");

        assertThrows(InvalidEmailException.class, () -> userService.addUser(user));

        verify(userDao, never()).userExistsByEmail(user.getEmail());
        verify(userDao, never()).addUser(any(), any());
    }

    @Test
    public void getUserByIdTest() {
        when(userDao.getById(2)).thenReturn(Optional.of(user));

        User actualUser = userService.getUserById(2);

        assertEquals(user, actualUser);
        verify(userDao).getById(2);
    }

    @Test
    public void getUserThatDontExists() {
        when(userDao.getById(0)).thenReturn(Optional.of(user));

        User actualUser = userService.getUserById(0);

        assertEquals(user, actualUser);
        verify(userDao).getById(0);
    }


    @Test
    void getUserByIdThatDontExists() {
        int userId = 0;
        when(userDao.getById(userId)).thenReturn(Optional.empty());

        MissingDataException exception = assertThrows(
                MissingDataException.class,
                () -> userService.getUserById(userId)
        );

        assertEquals("User not found with ID: 0", exception.getMessage());
    }

    @Test
    public void getAllUsersTest() {
        List<User> expectedUsers = Arrays.asList(user);

        when(userDao.getAll()).thenReturn(expectedUsers);

        assertEquals(expectedUsers, userService.getAllUsers());
    }

    @Test
    public void getEmptyUsersTest() {
        List<User> expectedUsers = Collections.emptyList();

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