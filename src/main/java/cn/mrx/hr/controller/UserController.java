package cn.mrx.hr.controller;

import cn.mrx.hr.service.IUserService;
import cn.mrx.hr.utils.DataGridPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Author: xialiangbo
 * Date: 2017/7/27 22:57
 * Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 用户管理页面
     * @return
     */
    @GetMapping("/manager")
    public String userManager(){
        return "user/user-manager";
    }

    /**
     * 分页查询用户
     * @param page
     * @param rows
     * @return  EasyUI DataGrid
     */
    @PostMapping("/list")
    @ResponseBody
    public DataGridPage selectUser(Integer page, Integer rows){
        return iUserService.selectUser(page, rows);
    }
}
