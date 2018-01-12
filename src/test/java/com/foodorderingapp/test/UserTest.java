package com.foodorderingapp.test;

import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.model.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class UserTest {


    private static AnnotationConfigApplicationContext context;

    private static UserDAO userDAO;

    private User user;

    @BeforeClass
    public static void init() {

        context = new AnnotationConfigApplicationContext();
        context.scan("com.foodorderingapp");
        context.refresh();

        userDAO = (UserDAO) context.getBean("userDAO");

    }

    @Test
    public void testAddOrder() {

        user = new User();

        user.setFirstName("laxman");
        user.setMiddleName("bahadur");
        user.setLastName("pant");
        user.setUserPassword("ram");
        user.setEmail("gl");
        user.setAddress("bkt");
        user.setContactNo("914891841");
        user.setBalance(5000);
        user.setUserRole("user");

        User user1 = userDAO.getUserByEmailId(user);
        if (user1 == null) {
            Assert.assertEquals("Fail to add on User", true, userDAO.addUser(user));

        } else if (user1 != null) {
            throw new IllegalArgumentException("plz rewite email");
        }
    }

    @Test
    public void testGetUser(){

        Assert.assertEquals("Fail to get on User",5,userDAO.getUsers());
    }


}
