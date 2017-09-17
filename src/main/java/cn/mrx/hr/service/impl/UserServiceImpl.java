package cn.mrx.hr.service.impl;

import cn.mrx.hr.mapper.UserMapper;
import cn.mrx.hr.model.User;
import cn.mrx.hr.service.IUserService;
import cn.mrx.hr.utils.AjaxResult;
import cn.mrx.hr.utils.DataGridPage;
import cn.mrx.hr.utils.Log4jPrint;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: xialiangbo
 * Date: 2017/7/27 17:47
 * Description: User业务层接口实现类
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录逻辑
     * @param account
     * @param pwd
     * @return
     */
    @Override
    public AjaxResult userLogin(String account, String pwd, Integer rememberMe) {
        //非空判断
        if(!StringUtils.isNotBlank(account))
            return AjaxResult.R(false, "用户名不能为空！");
        if(!StringUtils.isNotBlank(pwd))
            return AjaxResult.R(false, "密码不能为空！");

        //用户登录认证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account, pwd);
        token.setRememberMe(1==rememberMe);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return AjaxResult.R(false, "账号不存在！");
        }catch (IncorrectCredentialsException e) {
            return AjaxResult.R(false, "账号或密码错误！");
        }
        return AjaxResult.R(true, "登录成功！");
    }

    /**
     * 根据账号查询用户
     * @param account
     * @return
     */
    @Override
    public User selectUser(String account) {
        return userMapper.selectUserByAccount(account);
    }

    /**
     * 分页查询用户
     * @param page
     * @param rows
     * @return  EasyUI DataGrid
     */
    @Override
    public DataGridPage selectUser(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<User> userList = userMapper.selectUser();
        DataGridPage dataGridPage = new DataGridPage();
        dataGridPage.setRows(userList);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        dataGridPage.setTotal(pageInfo.getTotal());
        return dataGridPage;
    }

    @Override
    public AjaxResult selectUserById(Integer userId) {
        return AjaxResult.R(true, "查询成功！", userMapper.selectByPrimaryKey(userId));
    }
}
