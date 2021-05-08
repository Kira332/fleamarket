package com.market.service;

import com.market.Dao.GoodsDao;
import com.market.Dao.OrderDao;
import com.market.Dao.WxUserDao;
import com.market.pojo.Goods;
import com.market.pojo.Order;
import com.market.pojo.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService {

    @Autowired
    GoodsDao goodsDao;

    @Autowired
    OrderDao orderDao;

    public int order(int goodsId, String userId,String address,String phone){
        Order order=new Order();
        order.setAddress(address);
        order.setBuyerId(userId);
        order.setPhone(phone);
        System.out.println(phone);
        Goods goods=goodsDao.findById(goodsId);
        order.setGoods(goods);
        order.setState(0);
        orderDao.save(order);
        return order.getId();
    }

    public Order findById(int id){
        Order order=orderDao.findById(id);
        Goods goods=order.getGoods();
        Goods goods1=goodsDao.findById(goods.getId());
        order.setGoods(goods1);
        return order;
    }

    public List<Goods> findAllGoodsPurchase(String userId){
        List<Order> orderList=orderDao.findAllByBuyerId(userId);
        List<Goods> goodsList=new ArrayList<Goods>();
        for(Order order:orderList){
            Goods goods=order.getGoods();
            Goods goods1=goodsDao.findById(goods.getId());
            order.setGoods(goods1);
            goodsList.add(goods1);
        }
        return goodsList;
    }
}
