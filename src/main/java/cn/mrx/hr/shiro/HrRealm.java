package cn.mrx.hr.shiro;

import cn.mrx.hr.model.Resource;
import cn.mrx.hr.model.Role;
import cn.mrx.hr.model.User;
import cn.mrx.hr.service.IResourceService;
import cn.mrx.hr.service.IRoleService;
import cn.mrx.hr.service.IUserService;
import cn.mrx.hr.utils.Log4jPrint;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Author: xialiangbo
 * Date: 2017/7/31 16:31
 * Description: 自定义Realm
 */
public class HrRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(AuthorizingRealm.class);

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IRoleService iRoleService;

    @Autowired
    private IResourceService iResourceService;

    /**
     * 登录认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Log4jPrint.println(HrRealm.class, "Shiro开始认证", null);
        String account = (String) token.getPrincipal();
        User user = iUserService.selectUser(account);
        if(user == null)    return null;
        //返回认证信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getId(), user.getPwd(),  ByteSource.Util.bytes(user.getSalt()),  this.getName());
        return info;
    }

    /**
     * 用户授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Log4jPrint.println(HrRealm.class, "Shiro开始授权", null);
        Integer userId = (Integer) principalCollection.getPrimaryPrincipal();
        Set<String> roleNameSet = new HashSet<>();
        Set<String> resourceUrlSet = new HashSet<>();
        //1、根据userId查询该用户拥有哪些角色
        Set<Role> roleSet = iRoleService.selectRoles(userId);
        //2、遍历角色，根据roleId查询该角色拥有哪些资源
        for(Role role : roleSet){
            roleNameSet.add(role.getName());
            Set<Resource> resourceSet = iResourceService.selectResourcesByRoleId(role.getId());
            for(Resource resource : resourceSet){
                resourceUrlSet.add(resource.getUrl());
            }
        }
        //返回授权信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleNameSet);
        info.addStringPermissions(resourceUrlSet);
        return info;
    }
}
