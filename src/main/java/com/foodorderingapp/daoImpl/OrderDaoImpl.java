package com.foodorderingapp.daoImpl;

import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("orderDAO")
@Transactional
public class OrderDaoImpl implements OrderDAO{


    @Autowired
    private SessionFactory sessionFactory;

    public void addOrders(Orders orders) {


          sessionFactory.getCurrentSession().save(orders);
        }




    public List<Orders> getorders() {

        return sessionFactory.getCurrentSession().createQuery("FROM Orders",Orders.class).getResultList();
    }
}
