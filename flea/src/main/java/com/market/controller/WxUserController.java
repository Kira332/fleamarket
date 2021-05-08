package com.market.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.market.Dao.WxUserDao;
import com.market.pojo.Goods;
import com.market.pojo.WxUser;
import com.market.result.Result;
import com.market.result.ResultFactory;
import com.market.service.CollectionService;
import com.market.service.GoodsServices;
import com.market.service.WxUserService;
import com.market.util.WechatUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Controller;

@RestController
public class WxUserController {

    @Autowired
    private WxUserService wxUserService;

    //这个没测过，不知道能不能用
    @PostMapping("wx/login")
    @ResponseBody
    public Result user_login(@RequestParam(value = "code", required = false) String code,
                             @RequestParam(value = "rawData", required = false) String rawData,
                             @RequestParam(value = "signature", required = false) String signature) {
        // 用户非敏感信息：rawData
        // 签名：signature
        JSONObject rawDataJson = JSON.parseObject(rawData);
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务 获取返回的参数
        String openid = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");

        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
            return ResultFactory.buildFailResult("签名检验失败");
        }
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
        WxUser user = this.wxUserService.findByOpenId(openid);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
        String skey = UUID.randomUUID().toString();
        if (user == null) {
            // 用户信息入库
            String nickName = rawDataJson.getString("nickName");

            user = new WxUser();
            user.setOpenid(openid);
            user.setSkey(skey);
            user.setSessionKey(sessionKey);
            user.setNickName(nickName);

            wxUserService.save(user);
        } else {
            // 重新设置会话skey
            user.setSkey(skey);
            wxUserService.save(user);
        }
        //encrypteData比rowData多了appid和openid
        //JSONObject userInfo = WechatUtil.getUserInfo(encrypteData, sessionKey, iv);
        //6. 把新的skey返回给小程序
        return ResultFactory.buildSuccessResult("成功", skey);
    }

    //打开用户详情页
    @GetMapping("/user/privatemessage")
    public Result detail(String userId){
        WxUser wxUser=wxUserService.findByOpenId(userId);
        return ResultFactory.buildSuccessResult("成功打开用户详情页", wxUser);
    }

    //上传头像
    @PostMapping("/user/uploadpicture")
    public Result uploadPicture(String pictureUrl,String userId){
        wxUserService.uploadPicture(pictureUrl, userId);
        return ResultFactory.buildSuccessResult("成功上传头像", pictureUrl);
    }

    //修改头像
    @PostMapping("/user/changepicture")
    public Result changePicture(String pictureUrl,String userId){
        wxUserService.changePicture(pictureUrl, userId);
        return ResultFactory.buildSuccessResult("成功修改头像", pictureUrl);
    }

    //上传、修改个性签名
    @PostMapping("/user/signature")
    public Result changeSignature(String signature,String userId){
        wxUserService.changeSignature(signature, userId);
        return ResultFactory.buildSuccessResult("成功上传个性签名", signature);
    }

}
