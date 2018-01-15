package com.foodorderingapp.controller;

import com.foodorderingapp.dto.BillDto;
import com.foodorderingapp.dto.OrderDto;
import com.foodorderingapp.dto.OrderListDto;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.service.OrderDetailService;
import com.foodorderingapp.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrdersController {

    private final OrdersService ordersService;
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrdersController(OrdersService ordersService,OrderDetailService orderDetailService){
        this.ordersService=ordersService;
        this.orderDetailService=orderDetailService;
    }

    @PostMapping
    public BillDto addOrder(@RequestBody OrderDto orderDto) {
        return ordersService.add(orderDto);
    }

    @GetMapping
    public List<OrderListDto> getOrder(){
        return ordersService.getOrder();
    }

    @PutMapping("/{orderId}")
    public String update(@PathVariable int orderId){
        ordersService.update(orderId);
        return "Order updated successfully!";
    }
}
