package com.market.Dao;

import com.market.pojo.WxUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WxUserDao extends JpaRepository<WxUser,String> {
    WxUser findByOpenid(String openid);
}
