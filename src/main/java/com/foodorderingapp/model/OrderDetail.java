package com.foodorderingapp.model;

import com.foodorderingapp.dto.Food;

import javax.persistence.*;

@Entity
@Table(name="tbl_order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_detail_id")
    private int orderDetailId;
    @Column(name="quantity")
    private int quantity;
    @Column(name="unit_price")
    private double unitPrice;
    @Column(name="sub_total")
    private double subTotal;


    @ManyToOne
    @JoinColumn(name="food_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Orders orders;


    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
