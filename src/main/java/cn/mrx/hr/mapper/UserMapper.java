package cn.mrx.hr.mapper;

import cn.mrx.hr.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**#####################################自定义#############################################*/

    /**
     * 根据账号查询用户
     * @param account
     * @return
     */
    User selectUserByAccount(String account);

    /**
     * 查询所有用户
     * @return
     */
    List<User> selectUser();
}