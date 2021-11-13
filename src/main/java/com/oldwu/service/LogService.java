package com.oldwu.service;

import com.xiaomi.model.receive.ReceiveInfo;
import com.xiaomi.model.send.SendInfo;

import java.util.Date;

public interface LogService {

    void xiaomiSendMsgLog(SendInfo sendInfo, ReceiveInfo receiveInfo, Date startTime);

}
