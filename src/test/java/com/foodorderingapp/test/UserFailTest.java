package com.foodorderingapp.test;

import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.dto.UserDto;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.User;
import com.foodorderingapp.serviceImpl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class UserFailTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    UserServiceImpl userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = DataNotFoundException.class)
    public void testFailToAdd(){
        User user=new User();
        user.setEmail("hari1@yahoo.com");

        UserDto dto=new UserDto();
        dto.setEmail("hari1@yahoo.com");

        when(userDAO.getUserByEmailId(user.getEmail())).thenReturn(new User());
        when(userDAO.addUser(user)).thenReturn(user);
        assertNull(userService.addUser(dto));
    }

    @Test(expected = DataNotFoundException.class)
    public void testVerifyUserWhenNull(){
        User user=new User();
        user.setUserPassword("ram");
        user.setEmail("rr");
        when(userDAO.getUserByEmail(user.getUserPassword(),user.getEmail())).thenReturn(null);
        Assert.assertEquals(userService.verifyUser(user.getUserPassword(),user.getEmail()).getEmail(),user.getEmail());
    }

    @Test(expected = DataNotFoundException.class)
    public void failTestGetUser(){
        User user=new User();
        user.setUserId(1);
        when(userDAO.getUser(user.getUserId())).thenReturn(null);
        Assert.assertNull(userService.getUser(user.getUserId()));
    }

    @Test(expected = DataNotFoundException.class)
    public void testUpdateUser(){
        User user=new User();
        user.setUserId(1);
        when(userDAO.getUser(user.getUserId())).thenReturn(null);
        doNothing().when(userDAO).update(user);
        Assert.assertNull(userService.getUser(user.getUserId()));
    }
}
