package cn.mrx.hr.service.impl;

import cn.mrx.hr.mapper.ResourceMapper;
import cn.mrx.hr.mapper.RoleMapper;
import cn.mrx.hr.mapper.RoleResourceMapper;
import cn.mrx.hr.model.Resource;
import cn.mrx.hr.model.Role;
import cn.mrx.hr.model.RoleResource;
import cn.mrx.hr.model.User;
import cn.mrx.hr.service.IRoleService;
import cn.mrx.hr.utils.AjaxResult;
import cn.mrx.hr.utils.DataGridPage;
import cn.mrx.hr.utils.Log4jPrint;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Author: xialiangbo
 * Date: 2017/8/1 8:44
 * Description:
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    /**
     * 根据userId获取用户对应的roles
     * @param userId
     * @return
     */
    @Override
    public Set<Role> selectRoles(Integer userId) {
        return roleMapper.selectRolesByUserId(userId);
    }

    /**
     * 分页查询角色
     * @param page
     * @param rows
     * @return
     */
    @Override
    public DataGridPage selectRole(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<Role> roleList = roleMapper.selectRole();
        DataGridPage dataGridPage = new DataGridPage();
        dataGridPage.setRows(roleList);
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        dataGridPage.setTotal(pageInfo.getTotal());
        return dataGridPage;
    }

    /**
     * 根据角色id进行授权
     * @param roleId
     * @param resourceIds
     * @return
     */
    @Override
    public AjaxResult grantRole(Integer roleId, String resourceIds) {
        //操作t_role_resource表
        //1、线把该角色拥有的资源全部删除
        roleResourceMapper.deleteByRoleId(roleId);

        //2、重新插入
        String[] idArray = resourceIds.split(",");
        //把pid包括进去
        Set<Integer> newIds = new HashSet<>();
        for (String resourceId : idArray){
            //传过来的菜单id
            newIds.add(Integer.valueOf(resourceId));
            Resource resource = resourceMapper.selectByPrimaryKey(Integer.valueOf(resourceId));
            //父菜单
            if(resource.getPid() != null){
                Resource p_resource = resourceMapper.selectByPrimaryKey(resource.getPid());
                newIds.add(p_resource.getId());
                //再上一级父菜单
                if(p_resource.getPid() != null){
                    Resource g_resource = resourceMapper.selectByPrimaryKey(p_resource.getPid());
                    newIds.add(g_resource.getId());
                }
            }
        }

        RoleResource roleResource = new RoleResource();
        roleResource.setRoleId(roleId);
        for(Integer id : newIds){
            roleResource.setResourceId(id);
            roleResourceMapper.insert(roleResource);
        }
        return AjaxResult.R(true, "修改成功！");
    }
}
