package cn.mrx.hr.controller;

import cn.mrx.hr.service.IResourceService;
import cn.mrx.hr.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: xialiangbo
 * Date: 2017/8/4 15:11
 * Description:
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private IResourceService iResourceService;

    /**
     * 首页左边权限树调用此方法，查询所有的菜单(不包括功能菜单)
     * @return
     */
    @PostMapping("/menuTree")
    @ResponseBody
    public Object menuTree(){
        return iResourceService.selectMenuTree();
    }

    /**
     * 授权页面打开权限树调用此方法，查询所有的资源
     * @return
     */
    @PostMapping("/grantTree/{roleId}")
    @ResponseBody
    public AjaxResult grantTree(@PathVariable("roleId") Integer roleId){
        return iResourceService.selectGrantTree(roleId);
    }
}
