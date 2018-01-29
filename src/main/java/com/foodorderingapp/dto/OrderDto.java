package com.foodorderingapp.dto;

import java.util.List;

public class OrderDto {

    private int userId;
    private List<FoodQuantity> foodList=null;

    public OrderDto(int userId, List<FoodQuantity> foodList) {
        this.userId = userId;
        this.foodList = foodList;
    }

    public OrderDto(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<FoodQuantity> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodQuantity> foodList) {
        this.foodList = foodList;
    }
}
