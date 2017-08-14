package cn.mrx.hr.mapper;

import cn.mrx.hr.model.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    /**
     * 根据roleId获取对应的resources
     * @param roleId
     * @param type
     *      资源类型：0(菜单)、1(功能)
     *      传0则表示只查菜单，传null则表示查询全部菜单
     * @return
     */
    Set<Resource> selectResourcesByRoleId(@Param("roleId") Integer roleId, @Param("type") Integer type);

    Set<Resource> selectResources();
}