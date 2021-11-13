package com.oldwu.dao;

import com.oldwu.entity.SystemConfig;

public interface SystemConfigDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemConfig record);

    int insertSelective(SystemConfig record);

    SystemConfig selectByPrimaryKey(Integer id);

    SystemConfig selectByKey(String key);

    int updateByPrimaryKeySelective(SystemConfig record);

    int updateByPrimaryKey(SystemConfig record);
}