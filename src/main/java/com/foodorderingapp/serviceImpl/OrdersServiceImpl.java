package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.FoodDAO;
import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.dto.Food;
import com.foodorderingapp.dto.FoodQuantity;
import com.foodorderingapp.dto.OrderDto;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.model.User;
import com.foodorderingapp.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrdersServiceImpl implements OrdersService{

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private FoodDAO foodDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private OrderDetailDAO orderDetailDAO;

    public void addOrders(OrderDto orderDto) {

        User user=userDAO.getUser(orderDto.getUserId());
        if(user == null){
            throw new RuntimeException("User not found.");
        }

        Orders orders = new Orders();
        orders.setUser(user);
        orderDAO.addOrders(orders);


        OrderDetail orderDetail = new OrderDetail();

        for(FoodQuantity foodQuantity : orderDto.getFoodList()) {

            Food food=foodDAO.getFoodById(foodQuantity.getFoodId());
            double userBalance=user.getBalance();
            double foodPrice = food.getPrice();
            int quantity=foodQuantity.getQuantity();

            if(userBalance>foodPrice){

                orderDetail.setUnitPrice(foodPrice);
                orderDetail.setOrders(orders);
                orderDetail.setFood(food);
                orderDetail.setQuantity(foodQuantity.getQuantity());

                if(foodQuantity.getQuantity()<=0){

                    throw new IllegalArgumentException("quantity should be greater than 0");
                }

                double subTotal=quantity*foodPrice;
                orderDetail.setSubTotal(subTotal);
                orderDetailDAO.addOrderDetail(orderDetail);

            }
        }
    }

    public List<Orders> getOrders() {
       return  orderDAO.getorders();
    }


}
