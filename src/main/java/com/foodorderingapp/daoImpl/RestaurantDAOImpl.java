package com.foodorderingapp.daoImpl;

import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.exception.NotFoundException;
import com.foodorderingapp.model.Restaurant;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("restaurantDAO")
public class RestaurantDAOImpl implements RestaurantDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public RestaurantDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public boolean deleteRestaurant(Restaurant restaurant) {
        try {
            sessionFactory.getCurrentSession().delete(restaurant);
            return true;
        } catch (Exception e) {
            throw new NotFoundException("restaurant doesnot exits");
        }
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        try {
            sessionFactory.getCurrentSession().persist(restaurant);
            sessionFactory.getCurrentSession().flush();
            return restaurant;
        } catch (Exception e) {
            throw new NotFoundException("cannot add restaurant");
        }
    }

    public boolean updateRestaurant(Restaurant restaurant) {
        try {
            sessionFactory
            .getCurrentSession()
            .update(restaurant);
            return true;
        } catch (Exception e) {
            throw new NotFoundException("cannot update restaurant");
        }
    }

    public List<Restaurant> getAll() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("FROM Restaurant", Restaurant.class)
                .getResultList();
    }

    public Restaurant getRestaurantById(int id) {
        return sessionFactory.getCurrentSession().get(Restaurant.class, id);
    }

    public int deactivate(int id) {
        Restaurant restaurant = getRestaurantById(id);
        restaurant.setActive(false);
        updateRestaurant(restaurant);
        return id;
    }

    public int activate(int id) {
        Restaurant restaurant = getRestaurantById(id);
        restaurant.setActive(true);
        updateRestaurant(restaurant);
        return id;
    }

    public boolean getStatus(int id) {
        Restaurant restaurant =
                sessionFactory
                        .getCurrentSession()
                        .createQuery("FROM Restaurant where id= :id", Restaurant.class)
                        .setParameter("id", id)
                        .getSingleResult();
        return restaurant.isActive();
    }

    @Override
    public Restaurant getRestaurantByName(String restaurantName) {
        return  sessionFactory
                        .getCurrentSession()
                        .createQuery("FROM Restaurant where name= :restaurantName", Restaurant.class)
                        .setParameter("restaurantName", restaurantName)
                        .getSingleResult();
    }
}
