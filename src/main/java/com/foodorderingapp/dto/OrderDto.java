package com.foodorderingapp.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private int userId;
    private boolean confirm;
    private List<FoodQuantity> foodList=new ArrayList<FoodQuantity>();

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

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }
}
