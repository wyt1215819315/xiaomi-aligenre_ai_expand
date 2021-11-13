package com.oldwu.dao;

import com.oldwu.entity.SystemRecord;

public interface SystemRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemRecord record);

    int insertSelective(SystemRecord record);

    SystemRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemRecord record);

    int updateByPrimaryKey(SystemRecord record);
}