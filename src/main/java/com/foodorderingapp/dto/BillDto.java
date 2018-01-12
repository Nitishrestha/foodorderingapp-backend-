package com.foodorderingapp.dto;

import com.foodorderingapp.model.Food;

import java.util.ArrayList;
import java.util.List;

public class BillDto {

    private int balance;
    private List<Food> foodList=new ArrayList<Food>();

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
}
