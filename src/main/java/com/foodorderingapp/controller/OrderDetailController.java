package com.foodorderingapp.controller;


import com.foodorderingapp.dto.OrderDetailDto;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.service.OrderDetailService;
import com.foodorderingapp.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;
    private final OrdersService ordersService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService,OrdersService ordersService){
        this.orderDetailService=orderDetailService;
        this.ordersService=ordersService;
    }

    @GetMapping
    public List<OrderDetailDto> getOrderDetail() {
        return orderDetailService.getOrderDetails();
    }
}