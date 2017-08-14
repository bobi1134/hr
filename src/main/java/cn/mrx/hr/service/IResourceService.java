package cn.mrx.hr.service;

import cn.mrx.hr.model.Resource;
import cn.mrx.hr.utils.AjaxResult;

import java.util.Set;

/**
 * Author: xialiangbo
 * Date: 2017/8/1 8:44
 * Description:
 */
public interface IResourceService {

    /**
     * HrRealm.java授权时调用此方法，根据roleId获取对应的resources
     * @param roleId
     * @return
     */
    Set<Resource> selectResourcesByRoleId(Integer roleId);

    /**
     * 首页左边权限树调用此方法，查询所有的菜单(不包括功能菜单)
     * @return
     */
    Object selectMenuTree();

    /**
     * 授权页面打开权限树调用此方法，查询所有的资源
     * @return
     */
    AjaxResult selectGrantTree(Integer roleId);
}
