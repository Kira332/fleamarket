package com.market.controller;

import com.market.result.Result;
import com.market.result.ResultFactory;
import com.market.service.GoodsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    GoodsServices goodsServices;

    @PostMapping("/admin/pass")
    public Result passGoods(int goodsId){
        goodsServices.pass(goodsId);
        return ResultFactory.buildSuccessResult("商品通过审核", "");
        //填些甚么呢.....
    }

    @PostMapping("/admin/fail")
    public Result failGoods(int goodsId){
        Result result =goodsServices.fail(goodsId);
        return result;
    }

}
