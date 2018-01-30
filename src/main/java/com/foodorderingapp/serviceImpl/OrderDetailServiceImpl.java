package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dto.OrderDetailDto;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailDAO orderDetailDAO;

    @Autowired
    public  OrderDetailServiceImpl(OrderDetailDAO orderDetailDAO){
        this.orderDetailDAO=orderDetailDAO;
    }

    @Override
    public OrderDetail add(OrderDetail orderDetail) {
        return orderDetailDAO.add(orderDetail);
    }

    public List<OrderDetailDto> getOrderDetails() {
      return  orderDetailDAO.getOrderDetail();
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrderId(int orderId) {
        return orderDetailDAO.getOrderDetailByOrderId(orderId);
    }
}

