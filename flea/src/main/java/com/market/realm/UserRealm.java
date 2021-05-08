package com.market.realm;

import com.market.pojo.User;
import com.market.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
//import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String loginName = (String) principalCollection.fromRealm(getName()).iterator().next();
        if (loginName == null || loginName.length() == 0) {
            return null;
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user=userService.findByUsername(loginName);
        if (user != null) {
            info.addRole("user");
        }
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = token.getPrincipal().toString();
        User user = userService.findByUsername(userName);
        if (user==null) {
            throw new UnknownAccountException();
        }
        String salt=user.getSalt();
        return new SimpleAuthenticationInfo(userName, user.getPassword(), ByteSource.Util.bytes(salt), getName());
    }
}
