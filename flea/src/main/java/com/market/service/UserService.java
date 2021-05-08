package com.market.service;

import com.market.Dao.UserDao;
import com.market.pojo.Goods;
import com.market.pojo.User;
import com.market.pojo.WxUser;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }
    public String regist(User user){
        String username=user.getUsername();
        String password=user.getPassword();

        User testuser = userDao.findByUsername(username);
        if (testuser!=null){
            return "该用户名已被占用";
        }else if(username.equals("")||password.equals("")){
            return "用户名及密码不能为空";
        }
        String salt=new SecureRandomNumberGenerator().nextBytes().toString();
        int times=2;
        String encodedPassword=new SimpleHash("md5", password, salt, times).toString();
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        userDao.save(user);
        return "注册成功";
    }



}
