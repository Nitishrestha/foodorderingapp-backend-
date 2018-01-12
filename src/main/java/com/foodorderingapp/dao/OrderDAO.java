package com.foodorderingapp.dao;

import com.foodorderingapp.dto.OrderListDto;
import com.foodorderingapp.model.Orders;
import java.util.List;

public interface OrderDAO {
     Boolean add(Orders orders);
     List<OrderListDto> getOrders();
     void update(Orders orders);
     Orders getOrder(int orderId);
}
