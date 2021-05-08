package com.market.controller;

import com.market.pojo.Admin;
import com.market.pojo.User;
import com.market.service.AdminService;
import com.market.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    //管理员登录（暂时仅限网页
    @PostMapping("/adminlogin")
    public String adminLogin(@RequestBody Admin admin){
        return "/admin/work";
    }

    //网页版用户注册
    @PostMapping("/regist")
    public String userRegist(@RequestBody User user){
        String message=userService.regist(user);
        return message;
    }

    //网页用户登录
    @PostMapping("/login")
    public String userLogin(@RequestBody User user){
        String username=user.getUsername();
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username, user.getPassword());
        try {
            subject.login(token);
            return "登录成功";
        } catch (AuthenticationException e) {
            String message = "账号密码错误";
            return message;
        }
    }

    //管理员登出
    @GetMapping("/admin/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "";
    }

}
