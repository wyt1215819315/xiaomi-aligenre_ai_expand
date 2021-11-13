package com.oldwu.service;

import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import com.xiaomi.model.receive.ReceiveInfo;
import com.xiaomi.model.send.SendInfo;

public interface BaseService {

    SendInfo xiaomiReply(ReceiveInfo receiveInfo);

    TaskResult aligenreReply(TaskQuery taskQuery);
}
