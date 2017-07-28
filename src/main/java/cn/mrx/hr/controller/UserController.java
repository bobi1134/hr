package cn.mrx.hr.controller;

import cn.mrx.hr.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: xialiangbo
 * Date: 2017/7/27 22:57
 * Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/selectUser")
    public Object selectUser(){
        return iUserService.selectUser();
    }
}
