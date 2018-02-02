package com.foodorderingapp.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class FoodQuantity {

    @NotBlank(message = "This field is required.")
    @Size(min=3,max=30,message = "food name must be between 3 and 30.")
    private String foodName;
    private double foodPrice;
    @NotBlank(message = "This field is required.")
    @Size(min=3,max=30,message = "restaurant name must be between 3 and 30.")
    private String restaurantName;
    @NotBlank(message = "This field is required.")
    private int quantity;

    public FoodQuantity(String foodName, double foodPrice, String restaurantName, int quantity) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.restaurantName = restaurantName;
        this.quantity = quantity;
    }

    public FoodQuantity() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
