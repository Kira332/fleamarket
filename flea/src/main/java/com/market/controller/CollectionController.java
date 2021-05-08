package com.market.controller;

import com.market.pojo.Goods;
import com.market.result.Result;
import com.market.result.ResultFactory;
import com.market.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CollectionController {
    @Autowired
    CollectionService collectionService;

    //收藏商品
    @GetMapping("/user/collect")
    public Result collect(int goodsId, String userId){
        collectionService.save(goodsId, userId);
        return ResultFactory.buildSuccessResult("收藏成功", "");
    }

    //一个用户的收藏列表
    @PostMapping("/user/mycollection")
    public Result collection(String userId){
        List<Goods> goods=collectionService.findAllGoodsByUserId(userId);
        return ResultFactory.buildSuccessResult("成功打开收藏列表", goods);
    }
}
