package com.market.service;
import com.market.Dao.AdminDao;
import com.market.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminDao adminDao;
    public Admin findByUsername(String username){
        return adminDao.findByUsername(username);
    }
}