package com.oldwu.util;

import com.oldwu.dao.SystemLogDao;
import com.oldwu.entity.SystemLog;
import com.xiaomi.model.receive.ReceiveInfo;
import com.xiaomi.model.send.Response;
import com.xiaomi.model.send.SendInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LogUtil {
    private static final Log logger = LogFactory.getLog(LogUtil.class);

    public static void xiaomiReceiveMsgLog(ReceiveInfo receiveInfo) {
        String query = receiveInfo.getQuery();
        logger.info("小爱收到消息：" + query);
    }

    public static void logWarn(String msg) {
        logger.warn(msg);
    }

    public static void logInfo(String msg) {
        logger.info(msg);
    }

    public static void logDebug(String msg) {
        logger.debug(msg);
    }

    public static void logError(String msg) {
        logger.error(msg);
    }



}
