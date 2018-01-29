package com.foodorderingapp.controller;

import com.foodorderingapp.commons.GenericResponse;
import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.commons.WebUrlConstant;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.service.FoodService;
import com.foodorderingapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebUrlConstant.Restaurant.RESTAURANT_API)
public class RestaurantController {

    private static RestaurantService restaurantService;
    private static FoodService foodService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, FoodService foodService) {
        this.restaurantService = restaurantService;
        this.foodService = foodService;
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAll();
    }

    @PostMapping
    public Restaurant addRestaurant(@RequestBody @Valid Restaurant restaurant) {
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
    public int activateRestaurant(@PathVariable int id) {
        restaurantService.activate(id);
        return id;
    }

    @GetMapping(value = "/{id}/deactivate")
    public int deactivateRestaurant(@PathVariable int id) {
        restaurantService.deactivate(id);
        return id;
    }

    @GetMapping(value = "/page/{firstResult}/{maxResult}")
    public ResponseEntity<GenericResponse> getPaginatedRestaurant(@PathVariable int firstResult, @PathVariable int maxResult) {
        PageModel pageModel = new PageModel();
        pageModel.setFirstResult(firstResult);
        pageModel.setMaxResult(maxResult);
        List<Restaurant> restaurant = restaurantService.getPaginatedRestaurant(pageModel);
        if (restaurant == null && restaurant.size() == 0) {
            throw new DataNotFoundException("Record not found !!");
        }

        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResponseData(restaurant);
        genericResponse.setPageModel(pageModel);
        long count = restaurantService.countRestaurant();
        pageModel.setCount(count);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }
}