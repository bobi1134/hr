package cn.mrx.hr.controller;

import cn.mrx.hr.service.IUserService;
import cn.mrx.hr.utils.Log4jPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: xialiangbo
 * Date: 2017/8/3 11:06
 * Description:
 */
@Controller
public class LoginController {

    @Autowired
    private IUserService iUserService;

    /**
     * 首页跳转页面
     * @return
     */
    @GetMapping("/")
    public String index1(){
        return "index";
    }

    /**
     * 首页跳转页面
     * @return
     */
    @GetMapping("/index")
    public String index2(){
        return "index";
    }

    /**
     * 未登录跳转页面
     * @return
     */
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    /**
     * 用户登录逻辑
     * @param account
     * @param pwd
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public Object userLogin(String account, String pwd, @RequestParam(defaultValue = "0") Integer rememberMe){
        return iUserService.userLogin(account, pwd, rememberMe);
    }
}
