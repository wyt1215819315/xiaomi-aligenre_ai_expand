package com.oldwu.service.impl;

import com.oldwu.dao.SystemRecordDao;
import com.oldwu.dao.UTurnoffComputerDao;
import com.oldwu.entity.SystemRecord;
import com.oldwu.entity.UTurnoffComputer;
import com.oldwu.service.UService;
import com.oldwu.util.LogUtil;
import com.oldwu.word.Words;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UServiceImpl implements UService {
    @Autowired
    private UTurnoffComputerDao turnoffComputerDao;

    @Autowired
    private SystemRecordDao systemRecordDao;

    @Override
    public String turnOffComputer(String secret) {
        if (StringUtils.isEmpty(secret)) {
            return "error";
        }
        UTurnoffComputer uTurnoffComputer = turnoffComputerDao.selectBySecret(secret);
        if (uTurnoffComputer != null && uTurnoffComputer.getStatus().equals("success")) {
            //关机指令已经被接收，去掉标识
            uTurnoffComputer.setStatus("error");
            turnoffComputerDao.updateByPrimaryKeySelective(uTurnoffComputer);
            return "success";
        }
        return "error";
    }

    /**
     * 插入关机指令
     *
     * @param id
     * @return
     */
    @Override
    public String sendTurnOffComputer(String id, String type) {
        //查询唯一标识对应的id
        int record_id = 0;
        SystemRecord systemRecord = systemRecordDao.selectByOnlyId(id, type);
        if (systemRecord == null || systemRecord.getId() == null) {
            return Words.SEND_TURNOFF_COMPUTER_NOT_BAND;
        }
        record_id = systemRecord.getId();
        //首先查询关机记录表中是否有数据
        UTurnoffComputer uTurnoffComputer = turnoffComputerDao.selectByRecordId(record_id);
        if (uTurnoffComputer == null) {
            return Words.SEND_TURNOFF_COMPUTER_NOT_BAND;
        }
        uTurnoffComputer.setStatus("success");
        turnoffComputerDao.updateByPrimaryKeySelective(uTurnoffComputer);
        LogUtil.logInfo("写入关机请求：id=" + record_id);
        return Words.SEND_TURNOFF_COMPUTER;
    }
}
