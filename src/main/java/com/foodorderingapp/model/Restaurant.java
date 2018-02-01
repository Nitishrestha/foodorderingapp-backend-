package com.foodorderingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foodorderingapp.model.Food;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TOPSHI KREATS on 11/29/2017.
 */
@Entity
@Table(name = "tbl_restaurant")
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id",nullable=false,updatable = false)
    private int id;

    @Column(name = "restaurant_name")
    @NotNull(message = "Please enter the restaurant's name!!!")
    @Size(min=3,max=30,message = "Restaurant's name must be between 3 and 30!!!")
    private String name;

    @Column(name = "restaurant_address")
    @NotBlank(message = "Please enter the restaurant's address!!!")
    @Size(min=3,max=50, message = "Address must be between 3 and 50!!!")
    private String address;

    @Column(name = "restaurant_contact")
    @NotBlank(message = "Please enter the restaurant's contact!!!")
    @Size(min=7,max=10, message = "Contact must be between 7 and  10!!!")
    private String contact ;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Food> foodList =null;

    @Column(name = "status")
    private boolean isActive = true;

    public Restaurant(String name, String address, String contact, List<Food> foodList) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.foodList = foodList;
    }

    public Restaurant() {
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", foodList=" + foodList +
                '}';
    }
}
