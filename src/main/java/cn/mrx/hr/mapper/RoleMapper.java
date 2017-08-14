package cn.mrx.hr.mapper;

import cn.mrx.hr.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 根据userId获取拥有的角色
     * @param userId
     * @return
     */
    Set<Role> selectRolesByUserId(Integer userId);

    /**
     * 查询所有角色
     * @return
     */
    List<Role> selectRole();
}