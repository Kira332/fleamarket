package com.market.Dao;

import com.market.pojo.Goods;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsDao extends JpaRepository<Goods, Integer> {
    //给用户展示
    List<Goods> findAllByIsPassAndIsSold(int isPass,int isSold);
    //展示一个用户所有审核成功商品
    List<Goods> findAllBySellerIdAndAndIsPass(String sellerId,int isPass);
    //给管理员展示
    List<Goods> findAllByIsPass(int isPass);
    //商品详情页
    Goods findById(int id);
    //标签页查询
    List<Goods> findAllByType(String type);
    //关键字查询
    @Query(value = "SELECT * FROM goods g WHERE g.name LIKE CONCAT('%',:keywords,'%');",nativeQuery = true)
    List<Goods> findGoodsWithPartOfName(String keywords);
    //自己所有上传的商品
    List<Goods> findAllBySellerId(String sellerId);
    //找到一个用户里审核失败的所有商品
    List<Goods> findAllBySellerIdAndIsPass(String sellerId,int isPass);
    //找到在售商品
    List<Goods> findAllBySellerIdAndIsSoldAndIsPass(String sellerId,int isSold,int isPass);
}
