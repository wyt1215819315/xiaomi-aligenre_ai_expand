package com.oldwu.service.impl;

import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import com.oldwu.dao.SystemLogDao;
import com.oldwu.entity.SystemLog;
import com.oldwu.service.LogService;
import com.oldwu.util.LogUtil;
import com.xiaomi.model.receive.ReceiveInfo;
import com.xiaomi.model.send.Response;
import com.xiaomi.model.send.SendInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {

    private static final long XIAOMI_MAX_REQUEST_TIME = 2200L;
    private static final long ALIGENIE_MAX_REQUEST_TIME = 2200L;
    @Autowired
    private SystemLogDao logDao;


    @Override
    @Async
    public void aligenieSendMsgLog(TaskResult taskResult, TaskQuery taskQuery, Date startTime) {
        Date endDate = new Date();
        long totalTime = endDate.getTime() - startTime.getTime();
        String logStr = "天猫精灵发送消息：";
        logStr = "[" + totalTime + "ms] " + logStr;
        //收到的消息
        String queryMessage = taskQuery.getUtterance();
        //消息回复
        String reply = taskResult.getReply();
        //用户信息
        Map<String, String> requestData = taskQuery.getRequestData();
        logStr = logStr + reply;
        //判断时间是否超时
        if (totalTime > ALIGENIE_MAX_REQUEST_TIME) {
            LogUtil.logError("【请求过慢】" + logStr);
        } else {
            LogUtil.logInfo(logStr);
        }
        //将结果写入数据库
        SystemLog systemLog = new SystemLog();
        systemLog.setSendText(logStr);
        systemLog.setReceiveText(queryMessage);
        if (requestData.containsKey("userOpenId")){
            systemLog.setUserid(requestData.get("userOpenId"));
        }
        systemLog.setType("aligenie");
        logDao.insert(systemLog);
    }

    @Override
    @Async
    public void xiaomiSendMsgLog(SendInfo sendInfo, ReceiveInfo receiveInfo, Date startTime) {
        Date endDate = new Date();
        long totalTime = endDate.getTime() - startTime.getTime();
        String logStr = "小爱发送%s消息：";
        logStr = "[" + totalTime + "ms] " + logStr;
        Response response = sendInfo.getResponse();
        try {
            if (response.getTo_speak() != null) {
                //普通TTS
                logStr = String.format(logStr, "[TTS文本]");
                logStr = logStr + response.getTo_speak().getText();
            } else if (response.getDirectives().get(1).getAudio_item() != null && response.getDirectives().get(0).getTts_item() != null) {
                //TTS+语音字段
                logStr = String.format(logStr, "[TTS+AUDIO]");
                logStr = logStr + response.getDirectives().get(0).getTts_item().getText() + ";AUDIO URL=" + response.getDirectives().get(1).getAudio_item().getStream().getUrl();
            } else if (response.getDirectives().get(0).getAudio_item() != null) {
                logStr = String.format(logStr, "[AUDIO语音]");
                logStr = logStr + "AUDIO URL=" + response.getDirectives().get(0).getAudio_item().getStream().getUrl();
            } else if (response.getDirectives().get(0).getTts_item() != null) {
                logStr = String.format(logStr, "[TTS文本]");
                logStr = logStr + response.getDirectives().get(0).getTts_item().getText();
            } else {
                logStr = String.format(logStr, "[未识别的消息类型]");
                logStr = logStr + response;
            }
        } catch (Exception e) {
            logStr = String.format(logStr, "[未识别的消息类型]");
            logStr = logStr + response.toString();
        }
        //判断时间是否超时
        if (totalTime > XIAOMI_MAX_REQUEST_TIME) {
            LogUtil.logError("【请求过慢】" + logStr);
        } else {
            LogUtil.logInfo(logStr);
        }
        //将结果写入数据库
        SystemLog systemLog = new SystemLog();
        systemLog.setSendText(logStr);
        systemLog.setReceiveText(receiveInfo.getQuery());
        systemLog.setReceiveWhole(receiveInfo.toString());
        systemLog.setSendWhole(sendInfo.toString());
        systemLog.setUserid(receiveInfo.getSession().getUser().getUser_id());
        logDao.insertSelective(systemLog);
    }
}
