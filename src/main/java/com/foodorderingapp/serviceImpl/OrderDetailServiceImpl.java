package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dto.OrderDetailDto;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailDAO orderDetailDAO;

    @Autowired
    public  OrderDetailServiceImpl(OrderDetailDAO orderDetailDAO){
        this.orderDetailDAO=orderDetailDAO;
    }

    public List<OrderDetailDto> getOrderDetails() {
      return  orderDetailDAO.getOrderDetail();
    }

    public List<OrderDetail> getByUserId(int userId) {
        return orderDetailDAO.getOrderDetailByOrderId(userId);
    }
}

