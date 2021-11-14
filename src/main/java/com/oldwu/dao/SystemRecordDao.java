package com.oldwu.dao;

import com.oldwu.entity.SystemRecord;
import org.apache.ibatis.annotations.Param;

public interface SystemRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemRecord record);

    int insertSelective(SystemRecord record);

    SystemRecord selectByPrimaryKey(Integer id);

    SystemRecord selectByOnlyId(@Param("id") String id, @Param("type") String type);

    int updateByPrimaryKeySelective(SystemRecord record);

    int updateByPrimaryKey(SystemRecord record);
}