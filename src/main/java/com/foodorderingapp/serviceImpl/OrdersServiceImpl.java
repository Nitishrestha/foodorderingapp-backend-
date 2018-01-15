package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.FoodDAO;
import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.dto.BillDto;
import com.foodorderingapp.dto.FoodQuantity;
import com.foodorderingapp.dto.OrderDto;
import com.foodorderingapp.dto.OrderListDto;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.model.User;
import com.foodorderingapp.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("orderService")
public class OrdersServiceImpl implements OrdersService {

    private final UserDAO userDAO;
    private final FoodDAO foodDAO;
    private final OrderDAO orderDAO;
    private final OrderDetailDAO orderDetailDAO;

    @Autowired
    public OrdersServiceImpl(UserDAO userDAO,FoodDAO foodDAO,OrderDAO orderDAO,OrderDetailDAO orderDetailDAO){
        this.userDAO=userDAO;
        this.foodDAO=foodDAO;
        this.orderDAO=orderDAO;
        this.orderDetailDAO=orderDetailDAO;
    }

    double balance;
    public BillDto add(OrderDto orderDto) {

        BillDto bal=new BillDto();
        List<Food> foodList=new ArrayList<Food>();
        User user=userDAO.getUser(orderDto.getUserId());
        if(user == null){
            throw new RuntimeException("User not found.");
        }

        Orders orders = new Orders();
        orders.setUser(user);
        orders.setConfirm(false);
        orderDAO.add(orders);

        OrderDetail orderDetail = new OrderDetail();

        for(FoodQuantity foodQuantity : orderDto.getFoodList()) {

            orderDetail.setOrders(orders);
            orderDetail.setQuantity(foodQuantity.getQuantity());
            orderDetail.setFoodName(foodQuantity.getFoodName());
            orderDetail.setRestaurantName(foodQuantity.getRestaurantName());
            orderDetail.setQuantity(foodQuantity.getQuantity());
            orderDetail.setFoodPrice(foodQuantity.getFoodPrice());
            Food food=foodDAO.getFoodByName(foodQuantity.getFoodName());
            if(foodQuantity.getFoodPrice()!=food.getPrice()){
                throw new RuntimeException("food price is not in the list");
            }

            if(foodQuantity.getQuantity()<=0){
                    throw new IllegalArgumentException("quantity should be greater than 0");
                }
            foodList.add(food);
            int amount=foodQuantity.getQuantity()*foodQuantity.getFoodPrice();
            balance=user.getBalance()-amount;
            user.setBalance(balance);
            userDAO.update(user);
            bal.setBalance(balance);
            bal.setFoodList(foodList);
            orderDetailDAO.add(orderDetail);
        }
        return  bal;
        }

    public List<OrderListDto> getOrder() {
        return  orderDAO.getOrders();
    }

    public void update(int orderId) {
        Orders orders1=orderDAO.getOrder(orderId);
        orders1.setConfirm(true);
        orderDAO.update(orders1);
    }
}

