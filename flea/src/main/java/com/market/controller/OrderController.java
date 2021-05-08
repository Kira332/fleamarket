package com.market.controller;

import com.market.pojo.Goods;
import com.market.pojo.Order;
import com.market.result.Result;
import com.market.result.ResultFactory;
import com.market.service.OrderService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    //下单
    @PostMapping("/order/buy")
    public Result order(int goodsId, String userId,String address,String phone){
        orderService.order(goodsId, userId, address, phone);
        return ResultFactory.buildSuccessResult("下单成功","id");
    }

    //打开订单详情页
    @GetMapping("/order/details")
    public Result detail(int orderId){
        Order order=orderService.findById(orderId);
        return ResultFactory.buildSuccessResult("打开订单详情页成功", order);
    }

    //下单的商品们
    @PostMapping("/order/goods/purchase")
    public Result purchaseList(String userId){
        List<Goods> goodsList=orderService.findAllGoodsPurchase(userId);
        return ResultFactory.buildSuccessResult("我购买的商品", goodsList);
    }

}
