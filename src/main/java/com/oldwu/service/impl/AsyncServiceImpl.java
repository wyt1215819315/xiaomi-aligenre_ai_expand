package com.oldwu.service.impl;

import com.oldwu.dao.SystemRecordDao;
import com.oldwu.entity.SystemRecord;
import com.oldwu.service.AsyncService;
import com.oldwu.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {



    @Autowired
    private SystemRecordDao systemRecordDao;

    @Override
    @Async
    public void insertOnlyId(String id, String type) {
        try {
            SystemRecord systemRecord = new SystemRecord();
            if (type.equals("xiaomi")){
                systemRecord.setXiaomiId(id);
            }else if (type.equals("aligenre")){
                systemRecord.setAligenreId(id);
            }
            systemRecordDao.insertSelective(systemRecord);
        } catch (Exception e) {
            LogUtil.logWarn("插入唯一标记失败，该键可能已经存在");
        }
    }
}
