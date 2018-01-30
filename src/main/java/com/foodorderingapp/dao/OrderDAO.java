package com.foodorderingapp.dao;

import com.foodorderingapp.dto.OrderListDto;
import com.foodorderingapp.dto.OrderListMapperDto;
import com.foodorderingapp.dto.UserListMapperDto;
import com.foodorderingapp.model.Orders;
import java.util.List;

public interface OrderDAO {
     Orders add(Orders orders);
     List<OrderListMapperDto> getOrders();
     void update(Orders orders);
     Orders getOrder(int orderId);
}
