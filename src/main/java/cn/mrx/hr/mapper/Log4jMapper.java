package cn.mrx.hr.mapper;

import cn.mrx.hr.model.Log4j;

public interface Log4jMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Log4j record);

    int insertSelective(Log4j record);

    Log4j selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Log4j record);

    int updateByPrimaryKeyWithBLOBs(Log4j record);

    int updateByPrimaryKey(Log4j record);
}