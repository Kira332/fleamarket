package com.market.service;

import com.market.Dao.GoodsDao;
import com.market.pojo.Goods;
import com.market.pojo.WxUser;
import com.market.result.Result;
import com.market.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServices {
    @Autowired
    GoodsDao goodsDao;


    public void save(Goods goods){
        goodsDao.save(goods);
    }

    public void adminDelete(int goodsId){goodsDao.delete(findGoodsById(goodsId));}

    public void userDelete(int goodsId){
        Goods goods=findGoodsById(goodsId);
        goods.setIsSold(1);
        goodsDao.save(goods);
    }

    public List<Goods> findAllGoodsByType(String type){
        List<Goods> goodsList=goodsDao.findAllByType(type);
        return goodsList;
    }

    public List<Goods> findAllByKeyword(String keyword){
        return goodsDao.findGoodsWithPartOfName(keyword);
    }

    public Goods findGoodsById(int id){
        return goodsDao.findById(id);
    }

    public List<Goods> findAllGoodsOnSale(String openId){
        return goodsDao.findAllBySellerIdAndIsSoldAndIsPass(openId,0,1);
    }

    public List<Goods> findAllByIsPass(){
        return goodsDao.findAllByIsPass(0);
    }

    public List<Goods> findAllByIsPassAndSellerId(String sellerId){
        return goodsDao.findAllBySellerIdAndIsPass(sellerId,1);
    }

    public List<Goods> findAllBySellerId(String sellerId){
        return goodsDao.findAllBySellerId(sellerId);
    }

    public void pass(int goodsId){
        Goods goods=goodsDao.findById(goodsId);
        goods.setIsPass(1);
        goodsDao.save(goods);
    }

    public Result fail(int goodsId){
        Goods goods=goodsDao.findById(goodsId);
        goods.setIsPass(2);
        goodsDao.save(goods);
        String sellerId=goods.getSellerId();
        List<Goods> goodsList=goodsDao.findAllBySellerIdAndIsPass(sellerId, 2);
        String message1="您的商品["+goods.getName()+"]违规，审核失败.";
        String message2="且您发布违规商品次数过多，将禁用此账号";
        if(goodsList.size()>=5){
            return ResultFactory.buildSuccessResult(message1+message2,true);
        }else{
            return ResultFactory.buildSuccessResult(message1,false);
        }
    }

    public void uploadPicture(String pictureUrl,int goodsId){
        Goods goods=goodsDao.findById(goodsId);
        goods.setPictureUrl(pictureUrl);
    }

    public void changePicture(String pictureUrl,int goodsId){
        Goods goods=goodsDao.findById(goodsId);
        goods.setPictureUrl(pictureUrl);
        goodsDao.save(goods);
    }
}
