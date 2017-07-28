package cn.mrx.hr.service;

import cn.mrx.hr.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: xialiangbo
 * Date: 2017/7/27 17:47
 * Description:
 */
public interface IUserService {

    List<User> selectUser();
}
