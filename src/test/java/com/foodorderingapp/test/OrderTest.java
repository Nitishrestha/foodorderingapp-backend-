package com.foodorderingapp.test;

import com.foodorderingapp.dao.FoodDAO;
import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.dto.FoodQuantity;
import com.foodorderingapp.dto.OrderDto;
import com.foodorderingapp.model.*;
import com.foodorderingapp.serviceImpl.OrdersServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

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

        User user=new User();
        user.setUserId(1);
        Orders orders=new Orders();

        Food food=new Food("momo",100,new Restaurant());
        OrderDto orderDto=new OrderDto(1,new ArrayList<>());

        Restaurant restaurant=new Restaurant("f1soft","hattisar","9817651648",new ArrayList<>());

        OrderDetail orderDetail=new OrderDetail();
        FoodQuantity foodQuantity=new FoodQuantity(food.getName(),food.getPrice(),restaurant.getName(),1);

        when(userDAO.getUser(orderDto.getUserId())).thenReturn(user);
        when(orderDAO.add(orders)).thenReturn(orders);
        when(foodDAO.getFoodByResName(foodQuantity.getFoodName(),foodQuantity.getRestaurantName())).thenReturn(food);
        doNothing().when(userDAO).update(user);
        when(orderDetailDAO.add(anyObject())).thenReturn(orderDetail);
        Assert.assertNotNull(ordersService.add(orderDto));
    }
}