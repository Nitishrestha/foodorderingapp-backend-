package com.foodorderingapp.service;

import com.foodorderingapp.dto.BillDto;
import com.foodorderingapp.dto.OrderDto;
import com.foodorderingapp.dto.OrderListDto;

import java.util.List;

public interface OrdersService {
    BillDto add(OrderDto orderDto);
    List<OrderListDto> getOrder();
    void update(int orderId);
}
