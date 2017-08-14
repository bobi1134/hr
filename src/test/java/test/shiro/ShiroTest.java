package test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

/**
 * Author: xialiangbo
 * Date: 2017/8/2 10:37
 * Description:
 */
public class ShiroTest {

    /**##########################Authentication认证测试##########################*/
    /**
     * 从shiro-first.ini文件中的账号密码进行认证
     */
    @Test
    public void testLoginAndLogout(){
        //通过ini配置文件创建SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro-first.ini");
        //创建SecurityManager
        SecurityManager securityManager = factory.getInstance();
        //将SecurityManager设置在当前的运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        //从SecurityUtils中获取一个subject
        Subject subject = SecurityUtils.getSubject();
        //创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "111111");
        subject.login(token);
        System.out.println("认证状态："+subject.isAuthenticated());
        subject.logout();
        System.out.println("认证状态："+subject.isAuthenticated());
    }

    /**
     * 通过自定义Realm对数据库中的账号密码进行认证
     */
    @Test
    public void testMyRealm(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "111111");
        subject.login(token);
        System.out.println("认证状态："+subject.isAuthenticated());
        subject.logout();
        System.out.println("认证状态："+subject.isAuthenticated());
    }

    /**
     * md5散列算法测试
     */
    @Test
    public void testMD5(){
        String pwd = "111111";
        String salt = "qwerty";
        /**
         *  Md5Hash(Object source, Object salt, int hashIterations)
         *  source：明文，原始密码
         *  salt：盐，通过使用随机数
         *  hashIterations：散列次数
         */
        Md5Hash md5Hash = new Md5Hash(pwd, salt, 2);
        String md5Pwd = md5Hash.toString();
        System.out.println(md5Pwd);

        /**
         *  SimpleHash(String algorithmName, Object source, Object salt, int hashIterations)
         *  algorithmName：算法名称
         *  source：明文，原始密码
         *  salt：盐
         *  hashIterations：散列次数
         */
        SimpleHash simpleHash = new SimpleHash("md5", pwd, salt, 2);
        System.out.println(simpleHash);
    }

    /**
     * 通过自定义Realm+md5+salt加密对数据库中的账号密码进行认证
     */
    @Test
    public void testMyRealmMD5(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro-realm-md5.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "111111");
        subject.login(token);
        System.out.println("认证状态："+subject.isAuthenticated());
        subject.logout();
        System.out.println("认证状态："+subject.isAuthenticated());
    }








    /**##########################Authorization授权测试##########################*/
    /**
     * 从shiro-permission.ini文件中读取权限
     */
    @Test
    public void testPermission(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro-permission.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "111111");
        subject.login(token);
        System.out.println("认证状态："+subject.isAuthenticated());

        //认证成功后授权
        //基于角色的授权
        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.hasAllRoles(Arrays.asList("role1", "role2")));

        //基于资源的授权
        System.out.println(subject.isPermitted("user:create"));
        System.out.println(subject.isPermittedAll("user:create", "user:update", "user:delete"));
    }

    /**
     * 通过自定义Realm查询数据库读取权限
     */
    @Test
    public void testMyRealm2(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "111111");
        subject.login(token);
        System.out.println("认证状态："+subject.isAuthenticated());
        System.out.println(subject.isPermitted("user:create"));
        System.out.println(subject.isPermittedAll("user:create", "items:add"));
    }
}
