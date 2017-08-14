package cn.mrx.hr.service.impl;

import cn.mrx.hr.mapper.ResourceMapper;
import cn.mrx.hr.model.Resource;
import cn.mrx.hr.model.Role;
import cn.mrx.hr.service.IResourceService;
import cn.mrx.hr.service.IRoleService;
import cn.mrx.hr.shiro.ShiroUser;
import cn.mrx.hr.utils.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Author: xialiangbo
 * Date: 2017/8/1 8:44
 * Description:
 */
@Service
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private IRoleService iRoleService;

    /**
     * HrRealm.java授权时调用此方法，根据roleId获取对应的resources
     * @param roleId
     * @return
     */
    @Override
    public Set<Resource> selectResourcesByRoleId(Integer roleId) {
        //传null查询所有菜单
        return resourceMapper.selectResourcesByRoleId(roleId, null);
    }

    /**
     * 首页左边权限树调用此方法，查询所有的菜单(不包括功能菜单)
     * @return
     */
    @Override
    public Object selectMenuTree() {
        Integer userId = (Integer) SecurityUtils.getSubject().getPrincipal();
        //1、根据userId查询该用户拥有哪些角色
        Set<Role> roleSet = iRoleService.selectRoles(userId);
        //2、遍历角色，根据roleId查询该角色拥有哪些资源
        Set<Resource> allResourceSet = new HashSet<>();
        for(Role role : roleSet){
            //传0只查询所有的菜单(不包括功能菜单)
            Set<Resource> resourceSet = resourceMapper.selectResourcesByRoleId(role.getId(), 0);
            for(Resource resource : resourceSet){
                allResourceSet.add(resource);
            }
        }
        return allResourceSet;
    }

    /**
     * 授权页面打开权限树调用此方法，查询所有的资源
     * @return
     */
    @Override
    public AjaxResult selectGrantTree(Integer roleId) {
        Set<Resource> resourceSet = resourceMapper.selectResourcesByRoleId(roleId, null);
        Set<Integer> hasResourceIds = new HashSet<>();
        for(Resource resource : resourceSet){
            hasResourceIds.add(resource.getId());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("allResources", resourceMapper.selectResources());
        map.put("hasResourceIds", hasResourceIds);

        return AjaxResult.R(true, "查询成功！", map);
    }
}
