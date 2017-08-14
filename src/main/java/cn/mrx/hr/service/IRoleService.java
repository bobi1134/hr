package cn.mrx.hr.service;

import cn.mrx.hr.model.Role;
import cn.mrx.hr.utils.AjaxResult;
import cn.mrx.hr.utils.DataGridPage;

import java.util.Map;
import java.util.Set;

/**
 * Author: xialiangbo
 * Date: 2017/8/1 8:43
 * Description:
 */
public interface IRoleService {

    /**
     * 根据userId获取用户对应的roles
     * @param userId
     * @return
     */
    Set<Role> selectRoles(Integer userId);

    /**
     * 分页查询角色
     * @param page
     * @param rows
     * @return
     */
    DataGridPage selectRole(Integer page, Integer rows);

    /**
     * 根据角色id进行授权
     * @param roleId
     * @param resourceIds
     * @return
     */
    AjaxResult grantRole(Integer roleId, String resourceIds);
}
