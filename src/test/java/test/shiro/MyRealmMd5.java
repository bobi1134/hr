package test.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Author: xialiangbo
 * Date: 2017/8/2 11:51
 * Description: 修改自定义Realm支持散列算法
 */
public class MyRealmMd5 extends AuthorizingRealm {

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String account = (String) token.getPrincipal();
        System.out.println("认证：查询数据库中，账号："+account+"存在！密码为：36f2dfa24d0a9fa97276abbe13e596fc");

        //模拟从数据库查询到密码
        String pwd = "36f2dfa24d0a9fa97276abbe13e596fc";
        //模拟从数据库中取出的salt
        String salt = "qwerty";

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(account, pwd, ByteSource.Util.bytes(salt), this.getName());
        return info;
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }


}
