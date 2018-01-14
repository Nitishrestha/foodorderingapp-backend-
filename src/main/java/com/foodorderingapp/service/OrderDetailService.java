package com.foodorderingapp.service;

import com.foodorderingapp.dto.OrderDetailDto;
import com.foodorderingapp.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetailDto> getOrderDetails();
}
