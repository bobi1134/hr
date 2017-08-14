package cn.mrx.hr.controller;

import cn.mrx.hr.service.IRoleService;
import cn.mrx.hr.utils.AjaxResult;
import cn.mrx.hr.utils.DataGridPage;
import cn.mrx.hr.utils.Log4jPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: xialiangbo
 * Date: 2017/8/6 16:49
 * Description:
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;

    /**
     * 角色管理页面
     * @return
     */
    @GetMapping("/manager")
    public String roleManager(){
        return "role/role-manager";
    }

    /**
     * 分页查询角色
     * @param page
     * @param rows
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public DataGridPage selectRole(Integer page, Integer rows){
        return iRoleService.selectRole(page, rows);
    }

    /**
     * 授权页面
     * @return
     */
    @GetMapping("/grant")
    public String grantPage(){
        return "role/role-grant";
    }

    /**
     * 根据角色id进行授权
     * @param roleId
     * @param resourceIds
     * @return
     */
    @PostMapping("/grant")
    @ResponseBody
    public AjaxResult grantRole(Integer roleId, String resourceIds){
        return iRoleService.grantRole(roleId, resourceIds);
    }
}
