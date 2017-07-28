package cn.mrx.hr.service.impl;

import cn.mrx.hr.mapper.UserMapper;
import cn.mrx.hr.model.User;
import cn.mrx.hr.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: xialiangbo
 * Date: 2017/7/27 17:47
 * Description:
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> selectUser() {
        return userMapper.selectUser();
    }
}
