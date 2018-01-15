package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.FoodDAO;
import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("foodService")
public class FoodServiceImpl implements FoodService{

    private final FoodDAO foodDAO;
    private final RestaurantDAO restaurantDAO;

    @Autowired
    public FoodServiceImpl(FoodDAO foodDAO,RestaurantDAO restaurantDAO){
            this.foodDAO=foodDAO;
            this.restaurantDAO=restaurantDAO;
    }

    public boolean deleteFood(Food food) {
        return foodDAO.deleteFood(food);
    }

    public boolean updateFood(Food food,int id) {
        Food food1 = foodDAO.getFoodById(id);
        food1.setName(food.getName());
        food1.setPrice(food.getPrice());
        return foodDAO.updateFood(food1);
    }

    public List<Food> getAll() {
        return foodDAO.getAll();
    }

    public Food getFoodById(int id) {
        return foodDAO.getFoodById(id);
    }

    public List<Food> getFoodByRestaurantId(int id) {
        return foodDAO.getFoodByRestaurantId(id);
    }

    public List<Food> addFoodsToRestaurant(List<Food> foodList) {
        return foodDAO.addFoodsToRestaurant(foodList);
    }

    public Food getFoodByName(String foodName) {
        return foodDAO.getFoodByName(foodName);
    }
}
