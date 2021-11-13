package com.oldwu.controller;

import com.oldwu.service.BaseService;
import com.oldwu.service.LogService;
import com.oldwu.util.LogUtil;
import com.xiaomi.model.receive.ReceiveInfo;
import com.xiaomi.model.send.SendInfo;
import com.xiaomi.util.JsonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class XiaomiController {
    private final Log logger = LogFactory.getLog(XiaomiController.class);
    @Autowired
    private BaseService baseService;
    @Autowired
    private LogService logService;

    @RequestMapping("/xiaomi")
    public SendInfo getJson(@RequestBody String json) {
        Date startTime = new Date();
        ReceiveInfo receiveInfo = JsonUtils.getReceiveInfoFromJson(json);
        //记录接收日志
        LogUtil.xiaomiReceiveMsgLog(receiveInfo);
        SendInfo sendInfo = baseService.xiaomiReply(receiveInfo);
        //记录发送日志，异步调用，防止日志写入数据库花费过多时间
        logService.xiaomiSendMsgLog(sendInfo, receiveInfo, startTime);
        return sendInfo;
    }

}
