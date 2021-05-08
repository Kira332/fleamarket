package com.market.service;

import com.market.Dao.CollectionDao;
import com.market.Dao.GoodsDao;
import com.market.Dao.WxUserDao;
import com.market.pojo.Collection;
import com.market.pojo.Goods;
import com.market.pojo.User;
import com.market.pojo.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionService {
    @Autowired
    CollectionDao collectionDao;

    @Autowired
    GoodsDao goodsDao;

    public void save(int goodsId,String userId){
        Collection collection = new Collection();
        collection.setGoodsId(goodsId);
        collection.setUserId(userId);
        collectionDao.save(collection);
    }



    public List<Goods> findAllGoodsByUserId(String userId){
        List<Collection> collectionList=collectionDao.findAllByUserId(userId);
        List<Goods> goodsList=new ArrayList<Goods>();
        for (Collection collection:collectionList){
            int goodId=collection.getGoodsId();
            Goods goods=goodsDao.findById(goodId);
            goodsList.add(goods);
        }
        return goodsList;
    }
}
