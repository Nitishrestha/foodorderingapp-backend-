
package com.foodorderingapp.test;

import com.foodorderingapp.Application;
import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.dto.UserDto;
import com.foodorderingapp.exception.NotFoundException;
import com.foodorderingapp.model.User;
import com.foodorderingapp.service.UserService;
import com.foodorderingapp.serviceImpl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class UserTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    UserServiceImpl userService;

          @Before
              public void setUp() throws Exception {
            MockitoAnnotations.initMocks(this);
      }


         @Test (expected = NotFoundException.class)
   public void testAdd(){

        User user=new User();
        user.setFirstName("hari");
        user.setMiddleName("bahadur");
        user.setLastName("rai");
        user.setUserPassword("hari");
        user.setEmail("hari1@yahoo.com");
        user.setAddress("ktm");
        user.setContactNo("981615475");

        UserDto dto=new UserDto();
        dto.setEmail("hari1@yahoo.com");
        System.out.println(dto);
        System.out.println(userDAO);
        when(userDAO.getUserByEmailId(user)).thenReturn(new User());
        when(userDAO.addUser(user)).thenReturn(user);
        userService.addUser(dto);
        /*System.out.println("user service"+userService);*/
        /*Assert.assertNotNull(userService.addUser(dto).getUserId());*/
    }
}
