package com.oldwu.genshin.dao;

import com.oldwu.genshin.enitty.GenshinAccount;
import org.apache.ibatis.annotations.Param;


public interface GenshinAccountDao {
    int deleteByPrimaryKey(Integer id);

    int insert(GenshinAccount record);

    int insertSelective(GenshinAccount record);

    GenshinAccount selectByPrimaryKey(Integer id);

    GenshinAccount queryGenshinAccount(@Param("xiaomi") String xiaomiId, @Param("aligenre") String aligenreId);

    int updateByPrimaryKeySelective(GenshinAccount record);

    int updateByPrimaryKey(GenshinAccount record);
}