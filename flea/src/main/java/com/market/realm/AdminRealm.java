package com.market.realm;

import com.market.pojo.Admin;
import com.market.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String loginName = (String) principalCollection.fromRealm(getName()).iterator().next();
        if (loginName == null || loginName.length() == 0) {
            return null;
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Admin admin=adminService.findByUsername(loginName);
        if (admin != null) {
            info.addRole("admin");
        }
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = token.getPrincipal().toString();
        Admin admin = adminService.findByUsername(username);
        if (admin==null) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(username,admin.getPassword(),getName());
    }
}
