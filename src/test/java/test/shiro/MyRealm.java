package test.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: xialiangbo
 * Date: 2017/8/2 11:51
 * Description:
 */
public class MyRealm extends AuthorizingRealm {

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //tiken是用户输入的
        //1、从token中取出身份信息
        String account = (String) token.getPrincipal();

        //2、根据取到的account到数据库中查询是否存在
        System.out.println("认证：查询数据库中，账号："+account+"存在！密码为：111111");
        //模拟从数据库查询到密码
        String pwd = "111111";

        //3、如果查询得到数据就返回认证信息，查不到就返回null
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(account, pwd, this.getName());
        return info;
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String account =  (String) principals.getPrimaryPrincipal();
        System.out.println("授权：查询数据库权限中...");
        List<String> permissions = new ArrayList<String>();
        permissions.add("user:create");
        permissions.add("items:add");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        return info;
    }
}
