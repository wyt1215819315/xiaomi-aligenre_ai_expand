package com.oldwu.service;

import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import com.xiaomi.model.receive.ReceiveInfo;
import com.xiaomi.model.send.SendInfo;
import org.springframework.scheduling.annotation.Async;

import java.util.Date;

public interface LogService {

    void aligenieSendMsgLog(TaskResult taskResult, TaskQuery taskQuery, Date startTime);

    void xiaomiSendMsgLog(SendInfo sendInfo, ReceiveInfo receiveInfo, Date startTime);

}
