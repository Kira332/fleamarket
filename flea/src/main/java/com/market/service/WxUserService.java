package com.market.service;

import com.market.Dao.WxUserDao;
import com.market.pojo.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxUserService {
    @Autowired
    WxUserDao wxUserDao;
    public WxUser findByOpenId(String openId){
        return wxUserDao.findByOpenid(openId);
    }
    public void save(WxUser wxUser){
        wxUserDao.save(wxUser);
    }
    public void uploadPicture(String pictureUrl,String openId){
        WxUser wxUser=wxUserDao.findByOpenid(openId);
        wxUser.setPictureUrl(pictureUrl);
    }
    public void changePicture(String pictureUrl,String openId){
        WxUser wxUser=wxUserDao.findByOpenid(openId);
        wxUser.setPictureUrl(pictureUrl);
        wxUserDao.save(wxUser);
    }

    public void changeSignature(String signature,String openId){
        WxUser wxUser=wxUserDao.findByOpenid(openId);
        wxUser.setSignature(signature);
        wxUserDao.save(wxUser);
    }
}
