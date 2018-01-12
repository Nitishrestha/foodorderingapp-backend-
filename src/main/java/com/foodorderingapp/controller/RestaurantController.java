package com.foodorderingapp.controller;

import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.service.FoodService;
import com.foodorderingapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    private static RestaurantService restaurantService;
    private static FoodService foodService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, FoodService foodService){
        this.restaurantService = restaurantService;
        this.foodService = foodService;
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAll();
    }

    @PostMapping
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        restaurantService.addRestaurant(restaurant);
        return restaurant;
    }

    @GetMapping(value = "/{id}")
    public Restaurant getRestaurantById(@PathVariable int id) {
        return restaurantService.getRestaurantById(id);
    }

    @DeleteMapping(value = "/{id}")
    public int deleteRestaurant(@PathVariable int id) {
        restaurantService.deleteRestaurant(getRestaurantById(id));
        return id;
    }

    @PutMapping(value = "/{id}")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable int id) {
        restaurantService.updateRestaurant(restaurant, id);
        return restaurant;
    }

    @GetMapping(value = "/{id}/foods")
    public List<Food> getFoodsByRestaurant(@PathVariable int id) {
        return foodService.getFoodByRestaurantId(id);
    }

    @GetMapping(value = "/{id}/activate")
    public int activateRestaurant(@PathVariable int id){
        restaurantService.activate(id);
        return id;
    }

    @GetMapping(value = "/{id}/deactivate")
    public int deactivateRestaurant(@PathVariable int id){
        restaurantService.deactivate(id);
        return id;
    }
}