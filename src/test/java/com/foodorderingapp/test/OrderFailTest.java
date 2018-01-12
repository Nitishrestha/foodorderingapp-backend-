package com.foodorderingapp.test;

import com.foodorderingapp.dao.FoodDAO;
import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.model.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderFailTest {

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
        foodDAO = (FoodDAO) context.getBean("foodDAO");
        userDAO=(UserDAO)context.getBean("userDAO");
    }


    ExceptionClass exceptionClass=new ExceptionClass();
    @Test
    public void testAddOrder() {

//        Food food1=new Food();

        User user = userDAO.getUser(20);
    //      System.out.println(user.getFirstName());

        try {

            orders = new Orders();
            orders.setUser(user);

            if(user==null) {

              exceptionClass.throwNullPointerException();
                System.out.println("AAAA");
            }

//            Assert.assertEquals( true, orderDAO.addOrders(orders));

        }catch(Exception e){

            System.out.println("BBBB"+e);
        }



//        Food food = foodDAO.getFood(1);
//        if (food == null) {
//
//            thrown.expect(NullPointerException.class);
//        }
//
//        Orders orders = orderDAO.getorder(1);
//
//        orderDetail = new OrderDetail();
//        orderDetail.setOrders(orders);
//        orderDetail.setFood(food);
//        orderDetail.setQuantity(3);
//
//
//
//        try {
//
//            Assert.assertEquals("Failed to added on orderDetail  ", true, orderDetailDAO.addOrderDetail(orderDetail));
//
//             if(orders == null) {
//
//                 thrown.expect(NullPointerException.class);
//             }
//
//        } catch (Exception e) {
//
//           System.out.println("AAAAAAA" + e);
//        }
    }
}
