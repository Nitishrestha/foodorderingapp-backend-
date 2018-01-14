package com.foodorderingapp.daoImpl;

import com.foodorderingapp.dao.FoodDAO;
import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.Restaurant;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository("foodDAO")
@Transactional
public class FoodDAOImpl implements FoodDAO {

    private final SessionFactory sessionFactory;
    private final RestaurantDAO restaurantDAO;

    @Autowired
    public FoodDAOImpl(SessionFactory sessionFactory, RestaurantDAO restaurantDAO) {
        this.sessionFactory = sessionFactory;
        this.restaurantDAO = restaurantDAO;
    }

    public boolean deleteFood(Food food) {
        try {
            sessionFactory.getCurrentSession().delete(food);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updateFood(Food food) {
        try {
            sessionFactory.getCurrentSession().update(food);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Food> getAll() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("FROM Food", Food.class)
                .getResultList();
    }

    public Food getFoodById(int id) {
        return sessionFactory.getCurrentSession().get(Food.class, id);
    }

    public List<Food> getFoodByRestaurantId(int id) {
        String query = "FROM Food WHERE restaurant= :restaurant";
        Restaurant restaurant = restaurantDAO.getRestaurantById(id);
        System.out.println("food dao called");
        System.out.println(restaurant);
        return sessionFactory
                .getCurrentSession()
                .createQuery(query, Food.class)
                .setParameter("restaurant", restaurant)
                .getResultList();
    }

    public List<Food> addFoodsToRestaurant(List<Food> foodList) {
        try {
            for (Food food : foodList) {
                food.getRestaurant().setId(food.getRestaurantId());
                sessionFactory
                        .getCurrentSession()
                        .persist(food);
            }
            sessionFactory.getCurrentSession().flush();
            return foodList;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Food getFoodByName(String foodName) {
        try {
            return sessionFactory
                    .getCurrentSession()
                    .createQuery("FROM Food where name=:foodName", Food.class)
                    .setParameter("foodName", foodName).
                            getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("foodName is not in the list");
        }
    }
}