package com.market.controller;

import com.market.pojo.Goods;
import com.market.result.Result;
import com.market.result.ResultFactory;
import com.market.service.CollectionService;
import com.market.service.GoodsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoodsController {

    @Autowired
    GoodsServices goodsServices;

    //展示未审核商品
    @GetMapping("/admin/list")
    public Result listForAdmin (){
        List<Goods> goodsList=goodsServices.findAllByIsPass();
        return ResultFactory.buildSuccessResult("展示未审核商品", goodsList);
    }

    //上传商品
    @PostMapping("/user/upload")
    public Result uploadGoods (@RequestBody Goods goods){
        goodsServices.save(goods);
        return ResultFactory.buildSuccessResult("上传商品成功", goods.getId());
    }

    //修改商品(可以只给商品id跟要改的部分
    @PostMapping("/user/modify")
    public Result modifyGoods (@RequestBody Goods goods){
        goodsServices.save(goods);
        return ResultFactory.buildSuccessResult("修改商品成功", goods.getId());
    }

    //用户删除商品
    @DeleteMapping("/user/goodsdelete")
    public Result deleteGoods (int goodsId){
        //状态变为已出售，假删除
        goodsServices.userDelete(goodsId);
        return ResultFactory.buildSuccessResult("删除商品成功", "");
    }

    //商品分类
    @GetMapping("/type")
    public Result findGoodsByType(String type){
        List<Goods> goodsList=goodsServices.findAllGoodsByType(type);
        return ResultFactory.buildSuccessResult("分类查找成功",goodsList);
    }

    //模糊查询
    @GetMapping("/search")
    public Result findGoodsByKeyword(String keyword){
        List<Goods> goodsList=goodsServices.findAllByKeyword(keyword);
        return ResultFactory.buildSuccessResult("模糊查询成功", goodsList);
    }

    //打开商品详情页
    @GetMapping("/goods/detail")
    public Result detail(int goodsId){
        Goods goods=goodsServices.findGoodsById(goodsId);
        return ResultFactory.buildSuccessResult("成功打开详情页", goods);
    }

    //找到所有在售商品
    @PostMapping("/user/onsale")
    public Result goodsOnSale(String userId){
        List<Goods> goods=goodsServices.findAllGoodsOnSale(userId);
        return ResultFactory.buildSuccessResult("成功打开收藏列表", goods);
    }

    //上传主图
    @PostMapping("/user/goods/picture")
    public Result uploadGoodsPicture(String pictureUrl,int goodsId){
        goodsServices.uploadPicture(pictureUrl, goodsId);
        return ResultFactory.buildSuccessResult("成功上传商品主图", pictureUrl);
    }

    //修改主图
    @PostMapping("/user/goods/changepicture")
    public Result changeGoodsPicture(String pictureUrl,int goodsId){
        goodsServices.changePicture(pictureUrl, goodsId);
        return ResultFactory.buildSuccessResult("成功修改商品主图", pictureUrl);
    }

    //打开该卖家所有已审核商品
    @GetMapping("/user/sellerGoods")
    public Result openSellerGoods(String sellerId){
        List<Goods> goodsList=goodsServices.findAllByIsPassAndSellerId(sellerId);
        return ResultFactory.buildSuccessResult("成功打开该卖家所有已审核商品",goodsList);
    }

    //打开该用户所有发布商品
    @GetMapping("/user/myGoods")
    public Result openMyGoods(String sellerId){
        List<Goods> goodsList=goodsServices.findAllBySellerId(sellerId);
        return ResultFactory.buildSuccessResult("成功打开该用户所有发布商品",goodsList);
    }

}
