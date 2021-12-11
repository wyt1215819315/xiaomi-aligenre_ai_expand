package com.oldwu.controller;

import com.alibaba.da.coin.ide.spi.standard.ResultModel;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import com.alibaba.da.coin.ide.spi.trans.MetaFormat;
import com.aligenie.util.AligenieResult;
import com.oldwu.service.BaseService;
import com.oldwu.service.LogService;
import com.oldwu.util.LogUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/aligenie")
public class AligenieController {
    private final Log logger = LogFactory.getLog(AligenieController.class);
    @Autowired
    private BaseService baseService;

    @Autowired
    private LogService logService;

    @RequestMapping("")
    public ResultModel<TaskResult> getJson(@RequestBody String json) throws Exception {
        Date startTime = new Date();
        //将收到的json转换为实体
        TaskQuery query = MetaFormat.parseToQuery(json);
        //接收日志
        LogUtil.aligenieReceiveMsgLog(query);
        //处理服务
        TaskResult taskResult = baseService.aligenreReply(query);
        //记录日志
        logService.aligenieSendMsgLog(taskResult, query, startTime);
        //构建结果
        return AligenieResult.buildResultModel(taskResult);
//        return Result.sendMsg(receiveInfo.getQuery());
    }

}
