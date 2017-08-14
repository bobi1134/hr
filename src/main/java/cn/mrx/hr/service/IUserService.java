package cn.mrx.hr.service;

import cn.mrx.hr.model.User;
import cn.mrx.hr.utils.AjaxResult;
import cn.mrx.hr.utils.DataGridPage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: xialiangbo
 * Date: 2017/7/27 17:47
 * Description: User业务层接口
 */
public interface IUserService {

    /**
     * 用户登录逻辑
     * @param account
     * @param pwd
     * @return
     */
    AjaxResult userLogin(String account, String pwd);

    /**
     * 根据账号查询用户
     * @param account
     * @return
     */
    User selectUser(String account);

    /**
     * 分页查询用户
     * @param page
     * @param rows
     * @return  EasyUI DataGrid
     */
    DataGridPage selectUser(Integer page, Integer rows);
}
