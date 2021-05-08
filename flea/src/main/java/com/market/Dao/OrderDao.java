package com.market.Dao;

import com.market.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order,Integer> {
    Order findById(int id);
    List<Order> findAllByBuyerId(String  buyerId);
}
