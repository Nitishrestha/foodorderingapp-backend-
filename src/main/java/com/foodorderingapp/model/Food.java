package com.foodorderingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "tbl_food")
public class Food{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id",nullable=false,updatable = false)
    private int id;
    @Column(name="food_name")
    private String name;
    @Column(name = "food_price")
    private double price;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    private transient int restaurantId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        if(this.restaurant==null){
            this.restaurant=new Restaurant();
        }
        return restaurant;
    }


    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getRestaurantId() {
      if(restaurantId==0){
          return this.getRestaurant().getId();
      }
      return restaurantId;

    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
