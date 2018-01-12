package com.foodorderingapp.daoImpl;

import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.model.Restaurant;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository("restaurantDAO")
@Transactional
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
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        try {
            sessionFactory.getCurrentSession().persist(restaurant);
            sessionFactory.getCurrentSession().flush();
            return restaurant;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean updateRestaurant(Restaurant restaurant) {
        try {
            sessionFactory
            .getCurrentSession()
            .update(restaurant);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
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
}
