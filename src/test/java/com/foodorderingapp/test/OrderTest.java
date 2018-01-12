package com.foodorderingapp.test;

import com.foodorderingapp.dao.FoodDAO;
import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.Orders;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderTest {

    private static AnnotationConfigApplicationContext context;

    private static OrderDAO orderDAO;

    private static OrderDetailDAO orderDetailDAO;

    private static FoodDAO foodDAO;

    private static UserDAO userDAO;

    private Orders orders;

    private OrderDetail orderDetail;


    @BeforeClass
    public static void init() {

        context = new AnnotationConfigApplicationContext();
        context.scan("com.foodorderingapp");
        context.refresh();

        orderDAO = (OrderDAO) context.getBean("orderDAO");
        orderDetailDAO = (OrderDetailDAO) context.getBean("orderDetailDAO");
        userDAO = (UserDAO) context.getBean("userDAO");
        foodDAO = (FoodDAO) context.getBean("foodDAO");

    }

    @Test
    public void testAddOrder() {

//        User user = userDAO.getUser(15);
//        System.out.println("AAAAAA"+user.getFirstName());
//        orders = new Orders();
//        orders.setUser(user);
////        Assert.assertEquals(" Failed to added on order  ", true, orderDAO.addOrders(orders));
//
//        Food food = foodDAO.getFood(1);
//
//        orderDetail = new OrderDetail();
//
//        orders.setOrderId(64);
//        orderDetail.setOrders(orders);
//        orderDetail.setFood(food);
//        orderDetail.setQuantity(3);
//        orderDetail.setUnitPrice(food.getPrice());
//        int total=food.getPrice()*orderDetail.getQuantity();
//        orderDetail.setSubTotal(total);
//
////        Assert.assertEquals("Expected order detail to be available. ", true, orderDetailDAO.addOrderDetail(orderDetail));
//
//    }
//
//    @Test
//    public void testGetOrder() {
//
////        Assert.assertEquals(" Expected size of order to be 23 .",23, orderDAO.getorders().size());
//
//    }
//
//    @Test
//    public void testGetOrderDetail() {
//
////        Assert.assertEquals("Failed to added on orderDetail  ",11, orderDetailDAO.getOrderDetail().size());
//
//    }


    }
}
