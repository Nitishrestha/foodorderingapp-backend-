package com.foodorderingapp.controller;

import com.foodorderingapp.dto.Food;
import com.foodorderingapp.dto.FoodQuantity;
import com.foodorderingapp.dto.OrderDto;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.model.User;
import com.foodorderingapp.service.OrderDetailService;
import com.foodorderingapp.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @PostMapping
    @ResponseBody
    public void addOrder(@RequestBody OrderDto orderDto) {

        ordersService.addOrders(orderDto);

    }

    @GetMapping
    public List<Orders> getOrders(){
        return  ordersService.getOrders();
    }

}
