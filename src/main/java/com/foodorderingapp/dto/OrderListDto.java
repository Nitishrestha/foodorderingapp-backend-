package com.foodorderingapp.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderListDto {

    private int orderId;
    private int userId;
    private List<FoodRes> foodResList=new ArrayList<FoodRes>();

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<FoodRes> getFoodResList() {
        return foodResList;
    }

    public void setFoodResList(List<FoodRes> foodResList) {
        this.foodResList = foodResList;
    }
}
