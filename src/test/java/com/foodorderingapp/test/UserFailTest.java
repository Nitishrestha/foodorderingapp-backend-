package com.foodorderingapp.test;

import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.model.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.text.html.parser.AttributeList;
import java.security.spec.ECField;
import java.util.List;

public class UserFailTest {


    private static AnnotationConfigApplicationContext context;

    private static UserDAO userDAO;

    private static User user;

    @BeforeClass
    public static void init() {

        context = new AnnotationConfigApplicationContext();
        context.scan("com.foodorderingapp");
        context.refresh();

        userDAO = (UserDAO) context.getBean("userDAO");


    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testFailAddUser() {

        user = new User();

//      user.setFirstName("binov");
        user.setMiddleName("bahadur");
        user.setLastName("pant");
        user.setUserPassword("ram");
        user.setEmail("gl");
        user.setAddress("bkt");
        user.setContactNo("914891841");
        user.setBalance(5000);
        user.setUserRole("user");


//        Assert.assertEquals("Fail to add on User", true, userDAO.addUser(user));
        try {

            userDAO.addUser(user);

        } catch (Exception e) {

            thrown.expect(NullPointerException.class);

        }
    }

//    @Test
//    public void testFailGetUser() {
//
//        List<User> userList=userDAO.getUsers();
//
//        if(userList==null){
//
//            thrown.expect(IndexOutOfBoundsException.class);
//        }
//
//    }
}
