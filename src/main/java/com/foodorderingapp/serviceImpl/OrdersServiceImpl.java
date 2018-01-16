package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.FoodDAO;
import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.dto.*;
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

        try{
            List<OrderListMapperDto> orderListMapperDtoList=orderDAO.getOrders();
            List<OrderListDto> orderListDtoList=new ArrayList<OrderListDto>();

            for(OrderListMapperDto orderListMapperDto:orderListMapperDtoList){
                OrderListDto orderListDto=new OrderListDto();
                List<FoodRes> foodResList=new ArrayList();
                orderListDto.setOrderId(orderListMapperDto.getOrderId());
                orderListDto.setUserId(orderListMapperDto.getUserId());
                orderListDto.setOrderedDate(orderListMapperDto.getOrderedDate());
                List<OrderDetail> orderDetailList=orderDetailDAO.getOrderDetailByOrderId(orderListMapperDto.getOrderId());

                for(OrderDetail orderDetail:orderDetailList){
                    FoodRes foodRes = new FoodRes();
                    foodRes.setFoodName(orderDetail.getFoodName());
                    foodRes.setFoodPrice(orderDetail.getFoodPrice());
                    foodRes.setQuantity(orderDetail.getQuantity());
                    foodRes.setRestaurantName(orderDetail.getRestaurantName());
                    foodResList.add(foodRes);
                    orderListDto.setFoodResList(foodResList);
                }
                orderListDtoList.add(orderListDto);
            }
            return  orderListDtoList;
        }catch(RuntimeException e){
            throw new RuntimeException("Cannot find order");
        }
    }
    public List<UserListDto> getByUserId(int userId) {

        try {
            List<UserListMapperDto> userListMapperDtos = userDAO.getByUserId(userId);
            List<UserListDto> userListDtoList = new ArrayList<>();

            for (UserListMapperDto userListMapperDto : userListMapperDtos) {

                UserListDto userListDto = new UserListDto();
                List<FoodRes> foodResList = new ArrayList<>();
                userListDto.setUserId(userListMapperDto.getUserId());
                userListDto.setOrderId(userListMapperDto.getOrderId());
                userListDto.setFirstName(userListMapperDto.getFirstName());
                userListDto.setMiddleName(userListMapperDto.getMiddleName());
                userListDto.setLastName(userListMapperDto.getLastName());
                userListDto.setOrderedDate(userListMapperDto.getOrderedDate());
                List<OrderDetail> orderDetailList = orderDetailDAO.getOrderDetailByOrderId(userListMapperDto.getOrderId());

                for (OrderDetail orderDetail : orderDetailList) {
                    FoodRes foodRes = new FoodRes();
                    foodRes.setRestaurantName(orderDetail.getRestaurantName());
                    foodRes.setQuantity(orderDetail.getQuantity());
                    foodRes.setFoodPrice(orderDetail.getFoodPrice());
                    foodRes.setFoodName(orderDetail.getFoodName());
                    foodResList.add(foodRes);
                    userListDto.setFoodResList(foodResList);
                }
                userListDtoList.add(userListDto);
            }
            return userListDtoList;
        }
        catch (Exception e) {
            throw new RuntimeException("cannot find user list");
        }

    }

    public void update(int orderId) {
        Orders orders1=orderDAO.getOrder(orderId);
        orders1.setConfirm(true);
        orderDAO.update(orders1);
    }
}

