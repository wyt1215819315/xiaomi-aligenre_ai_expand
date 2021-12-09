package com.oldwu.controller;

import com.alibaba.da.coin.ide.spi.standard.ResultModel;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import com.alibaba.da.coin.ide.spi.trans.MetaFormat;
import com.aligenie.util.AligenreResult;
import com.oldwu.service.BaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/aligenre")
public class AligenreController {
    private final Log logger = LogFactory.getLog(AligenreController.class);
    @Autowired
    private BaseService baseService;

    @RequestMapping("/")
    public ResultModel<TaskResult> getJson(@RequestBody String json) {
        Date startTime = new Date();
        //将收到的json转换为实体
        TaskQuery query = MetaFormat.parseToQuery(json);
        String queryMessage = query.getUtterance();
        logger.info("天猫精灵收到消息：" + queryMessage);
        TaskResult taskResult = baseService.aligenreReply(query);
        String sendText = taskResult.getReply();
        Date endTime = new Date();
        logger.info("天猫精灵发送消息：" + sendText + "  处理用时：" + (endTime.getTime() - startTime.getTime()) + "ms");
        return AligenreResult.buildResultModel(taskResult);
//        return Result.sendMsg(receiveInfo.getQuery());
    }

}
