package com.foodorderingapp.daoImpl;

import com.foodorderingapp.config.DBConnection;
import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.dto.FoodRes;
import com.foodorderingapp.dto.UserListDto;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("userDAO")
@Transactional
public class UserDaoImpl implements UserDAO {


    private  final SessionFactory sessionFactory;
    private  final OrderDetailDAO orderDetailDAO;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory,OrderDetailDAO orderDetailDAO ){
        this.sessionFactory=sessionFactory;
        this.orderDetailDAO=orderDetailDAO;
    }

    public Boolean addUser(User user) {
        try
        {
            sessionFactory.getCurrentSession().persist(user);
            return true;
        }
         catch(Exception ex) {
             System.out.println(ex.getMessage());
            return false;

        }
    }

    public List<User> getUsers() {
        return sessionFactory.getCurrentSession().createQuery("FROM User", User.class).getResultList();
    }

    public User getUser(int userId) {
        return sessionFactory.getCurrentSession().get(User.class, userId);
    }

    public User getUserByEmail(User user) {

        try {
            User user1 = sessionFactory
                    .getCurrentSession()
                    .createQuery("FROM User WHERE email=:email AND userPassword=:userPassword", User.class)
                    .setParameter("email", user.getEmail())
                    .setParameter("userPassword", user.getUserPassword()).getSingleResult();
            return user1;

        } catch (Exception ex) {
            throw new RuntimeException("User not found");
        }
    }

    public User getUserByEmailId(User user) {

        try {
            User user1 = sessionFactory.getCurrentSession().
                    createQuery("FROM User WHERE email=:email", User.class).
                    setParameter("email", user.getEmail()).
                    getSingleResult();
            System.out.println(user);
            return user1;
        } catch (Exception e) {
          return null;
        }
    }

    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    public List<UserListDto> getByUserId(int userId) {
        try(Connection con = DBConnection.getConnection()){

            String sql="SELECT tbl_orders.order_id , tbl_users.first_name ,tbl_users.middle_name,tbl_users.last_name,tbl_orders.user_id " +
                    "FROM tbl_orders\n" +
                    "INNER JOIN  tbl_users ON tbl_orders.user_id=tbl_users.user_id  \n" +
                    "WHERE tbl_orders.user_id=?\n" +
                    "AND tbl_orders.confirm=true";
            PreparedStatement pst =con.prepareStatement(sql);
            pst.setInt(1,userId);
            ResultSet rs = pst.executeQuery();
            List<UserListDto> userListDtoList=new ArrayList<UserListDto>();
            while(rs.next()) {

                UserListDto userListDto=new UserListDto();
                List<FoodRes> foodResList=new ArrayList<FoodRes>();
                userListDto.setUserId(rs.getInt("user_id"));
                userListDto.setOrderId(rs.getInt("order_id"));
                userListDto.setFirstName(rs.getString("first_name"));
                userListDto.setMiddleName(rs.getString("middle_name"));
                userListDto.setLastName(rs.getString("last_name"));
                List<OrderDetail> orderDetailList=orderDetailDAO.getOrderDetailByOrderId(rs.getInt("order_id"));

                for(OrderDetail orderDetail:orderDetailList){

                    FoodRes foodRes=new FoodRes();
                    foodRes.setRestaurantName(orderDetail.getRestaurantName());
                    foodRes.setQuantity(orderDetail.getQuantity());
                    foodRes.setFoodPrice(orderDetail.getFoodPrice());
                    foodRes.setFoodName(orderDetail.getFoodName());
                    foodResList.add(foodRes);
                    userListDto.setFoodResList(foodResList);
                }
                userListDtoList.add(userListDto);
            }
            return userListDtoList;
        }catch(Exception e){
            throw new RuntimeException("cannot find user list");
        }
    }
}
