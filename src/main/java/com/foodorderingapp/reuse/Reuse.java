package com.foodorderingapp.reuse;

import com.foodorderingapp.dto.FoodRes;
import com.foodorderingapp.model.OrderDetail;

public class Reuse {

    public static FoodRes addFoodRes(OrderDetail orderDetail){
        FoodRes foodRes=new FoodRes();
        foodRes.setRestaurantName(orderDetail.getRestaurantName());
        foodRes.setQuantity(orderDetail.getQuantity());
        foodRes.setFoodPrice(orderDetail.getFoodPrice());
        foodRes.setFoodName(orderDetail.getFoodName());
        return foodRes;
    }
}
