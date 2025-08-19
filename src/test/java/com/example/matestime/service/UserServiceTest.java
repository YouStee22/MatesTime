package com.example.matestime.service;

import com.example.matestime.dao.UserCommunitiesDao;
import com.example.matestime.dao.UserDao;
import com.example.matestime.models.InvalidEmailException;
import com.example.matestime.models.MissingDataException;
import com.example.matestime.models.MissingUserException;
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

    @Mock
    private UserDao userDao;

    @Mock
    private UserCommunitiesDao userCommunitiesDao;

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
        //given
        User user = new User(99,"Jan", "Kowalski");
        when(userDao.userExistsById(user.getId())).thenReturn(false);

        //when
        MissingUserException exists = assertThrows(MissingUserException.class, () -> userService.updateUser(user));

        //then
        assertEquals("User not found with ID: 99", exists.getMessage());
        verify(userDao, never()).updateUser(any());
    }

    @Test
    public void testAddingUserThatExistsTest() {
        //given
        User user = new User(2,"Jan", "123@wp.pl");

        //given
        assertThrows(InvalidEmailException.class, () -> userService.addUser(user));

        //then
        verify(userDao, never()).userExistsByEmail(user.getEmail());
        verify(userDao, never()).addUser(any(), any());
    }

    @Test
    public void getUserByIdTest() {
        //given
        when(userDao.getById(2)).thenReturn(Optional.of(user));

        //when
        User actualUser = userService.getUserById(2);

        //then
        assertEquals(user, actualUser);
        verify(userDao).getById(2);
    }

    @Test
    public void getUserThatDontExistsTest() {
        //given
        when(userDao.getById(0)).thenReturn(Optional.of(user));
        //when
        User actualUser = userService.getUserById(0);
        //then
        assertEquals(user, actualUser);
        verify(userDao).getById(0);
    }


    @Test
    void getUserByIdThatDontExistsTest() {
        //given
        int userId = 0;
        when(userDao.getById(userId)).thenReturn(Optional.empty());

        //when
        MissingDataException exception = assertThrows(
                MissingDataException.class,
                () -> userService.getUserById(userId)
        );

        //then
        assertEquals("User not found with ID: 0", exception.getMessage());
    }

    @Test
    public void getAllUsersTest() {
        //given
        List<User> expectedUsers = Arrays.asList(user);

        //when
        when(userDao.getAll()).thenReturn(expectedUsers);

        //then
        assertEquals(expectedUsers, userService.getAllUsers());
    }

    @Test
    public void getEmptyUsersTest() {
        //given
        List<User> expectedUsers = Collections.emptyList();

        //when
        when(userDao.getAll()).thenReturn(expectedUsers);

        //then
        assertEquals(expectedUsers, userService.getAllUsers());
    }

    @Test
    public void deleteUserByIdTest() {
        //given
        int atx = 2;
        when(userDao.userExistsById(atx)).thenReturn(true);

        //when
        userService.delete(atx);

        //then
        verify(userCommunitiesDao).deleteUserFromCommunity(atx);
        verify(userDao).deleteById(atx);
    }

    @Test
    public void deleteUserThatDontExistsTest() {
        //given
        int atx = 0;

        //when
        when(userDao.userExistsById(atx)).thenReturn(false);

        //then
        assertThrows(MissingUserException.class, () -> userService.delete(atx));
        verify(userDao, never()).deleteById(anyInt());
    }
}