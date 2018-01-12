package com.foodorderingapp.dao;

import com.foodorderingapp.dto.OrderDetailDto;
import com.foodorderingapp.model.OrderDetail;
import java.util.List;

public interface OrderDetailDAO {
     List<OrderDetailDto> getOrderDetail();
     List<OrderDetail> getOrderDetailByOrderId(int orderId);
     void add(OrderDetail orderDetail);
}
