package com.foodorderingapp.dao;

import com.foodorderingapp.model.Restaurant;

import java.util.List;

public interface RestaurantDAO {
    Restaurant addRestaurant(Restaurant restaurant);
    boolean deleteRestaurant(Restaurant restaurant);
    boolean updateRestaurant(Restaurant restaurant);
    List<Restaurant> getAll();
    Restaurant getRestaurantById(int id);
    int deactivate(int id);
    int activate(int id);
    boolean getStatus(int id);
    Restaurant getRestaurantByName(String restaurantName);
}
