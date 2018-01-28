package com.foodorderingapp.test;

import com.foodorderingapp.dao.FoodDAO;
import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.dto.BillDto;
import com.foodorderingapp.dto.OrderDto;
import com.foodorderingapp.model.*;
import com.foodorderingapp.serviceImpl.OrdersServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class OrderTest {

    @Mock
    private OrderDAO orderDAO;

    @Mock
    private FoodDAO foodDAO;

    @Mock
    private OrderDetailDAO orderDetailDAO;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    OrdersServiceImpl ordersService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdd(){
        OrderDto orderDto=new OrderDto();
        BillDto billDto=new BillDto();
        User user=new User();
        user.setUserId(1);
        Orders orders=new Orders();
        Food food=new Food();
        food.setName("momo");
        OrderDetail orderDetail=new OrderDetail();

        Restaurant restaurant=new Restaurant();
        restaurant.setName("f1soft");

        when(userDAO.getUser(user.getUserId())).thenReturn(user);
        when(orderDAO.add(orders)).thenReturn(orders);
        when(foodDAO.getFoodByResName(food.getName(),restaurant.getAddress())).thenReturn(food);
        doNothing().when(userDAO).update(user);
        when(orderDetailDAO.add(anyObject())).thenReturn(orderDetail);
        Assert.assertEquals(ordersService.add(orderDto),billDto);
    }
}