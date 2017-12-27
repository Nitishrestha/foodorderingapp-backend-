package com.foodorderingapp.service;

import com.foodorderingapp.dto.OrderDto;
import com.foodorderingapp.model.Orders;

import java.util.List;

public interface OrdersService {
    void addOrders(OrderDto ordersDto);
    List<Orders> getOrders();
}
