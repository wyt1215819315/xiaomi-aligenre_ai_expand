package com.oldwu.dao;

import com.oldwu.entity.UTurnoffComputer;

public interface UTurnoffComputerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UTurnoffComputer record);

    int insertSelective(UTurnoffComputer record);

    UTurnoffComputer selectByPrimaryKey(Integer id);

    UTurnoffComputer selectBySecret(String secret);

    UTurnoffComputer selectByRecordId(Integer recordId);

    int updateByPrimaryKeySelective(UTurnoffComputer record);

    int updateByPrimaryKey(UTurnoffComputer record);
}