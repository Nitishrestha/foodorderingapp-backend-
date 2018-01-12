package com.foodorderingapp.daoImpl;

import com.foodorderingapp.config.DBConnection;
import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dto.FoodRes;
import com.foodorderingapp.dto.OrderListDto;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.Orders;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("orderDAO")
@Transactional
public class OrderDaoImpl implements OrderDAO{

    private final SessionFactory sessionFactory;
    private final OrderDetailDAO orderDetailDAO;

    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory ,OrderDetailDAO orderDetailDAO){
        this.sessionFactory=sessionFactory;
        this.orderDetailDAO=orderDetailDAO;
    }

    public Boolean add(Orders orders) {

        try {
            sessionFactory.getCurrentSession().save(orders);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<OrderListDto> getOrders() {

        try(Connection con = DBConnection.getConnection()) {

            String sql ="select tbl_orders.order_id , tbl_orders.user_id from tbl_orders where tbl_orders.confirm=false";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            List<OrderListDto> orderListDtoList=new ArrayList<OrderListDto>();
            while(rs.next()) {

                OrderListDto orderListDto=new OrderListDto();
                List<FoodRes> foodResList=new ArrayList<FoodRes>();
                List<OrderDetail> orderDetailList=orderDetailDAO.getOrderDetailByOrderId(rs.getInt("order_id"));
                orderListDto.setOrderId(rs.getInt("order_id"));
                orderListDto.setUserId(rs.getInt("user_id"));

                for(OrderDetail orderDetail:orderDetailList){
                    FoodRes foodRes = new FoodRes();
                    foodRes.setFoodName(orderDetail.getFoodName());
                    foodRes.setFoodPrice(orderDetail.getFoodPrice());
                    foodRes.setQuantity(orderDetail.getQuantity());
                    foodRes.setRestaurantName(orderDetail.getRestaurantName());
                    foodResList.add(foodRes);
                    orderListDto.setFoodResList(foodResList);
                }
                orderListDtoList.add(orderListDto);
            }
            return orderListDtoList;
        } catch (Exception e) {

            throw new RuntimeException("Cannot find Order List");
        }
    }

    public void update(Orders orders) {
        sessionFactory.getCurrentSession().update(orders);
    }

    public Orders getOrder(int orderId) {
        return  sessionFactory.getCurrentSession().get(Orders.class,orderId);
    }

    /*public List<Orders> getOrderByUser(int userId) {
        return sessionFactory.getCurrentSession().
                createQuery("FROM Orders where userId=:userId",Orders.class).
                setParameter("userId",userId).getResultList();
    }*/
}
