package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TOPSHI KREATS on 11/29/2017.
 */

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {


    private final RestaurantDAO restaurantDAO;

    @Autowired
    public RestaurantServiceImpl(RestaurantDAO restaurantDAO){
        this.restaurantDAO=restaurantDAO;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantDAO.addRestaurant(restaurant);
    }

    public boolean deleteRestaurant(Restaurant restaurant) {
        return restaurantDAO.deleteRestaurant(restaurant);
    }

    public boolean updateRestaurant(Restaurant restaurant, int id) {
        Restaurant restaurant1 =restaurantDAO.getRestaurantById(id);
        restaurant1.setName(restaurant.getName());
        restaurant1.setAddress(restaurant.getAddress());
        restaurant1.setContact(restaurant.getContact());
        return restaurantDAO.updateRestaurant(restaurant1);
    }
    public List<Restaurant> getAll() {
        return restaurantDAO.getAll();
    }
    public Restaurant getRestaurantById(int id) {
        return restaurantDAO.getRestaurantById(id);
    }

    public int deactivate(int id) {
        if(!restaurantDAO.getRestaurantById(id).equals(null)){
            if(getStatus(id)!=false) {
                restaurantDAO.deactivate(id);
            }
        }
        return id;
    }

    public int activate(int id) {
        if(!restaurantDAO.getRestaurantById(id).equals(null)){
            if(getStatus(id)!=true) {
                restaurantDAO.activate(id);
            }
        }
        return id;
    }

    public boolean getStatus(int id) {
        return restaurantDAO.getStatus(id);
    }
}