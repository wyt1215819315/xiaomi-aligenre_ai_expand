package com.aligenie.util;

import com.alibaba.da.coin.ide.spi.meta.ExecuteCode;
import com.alibaba.da.coin.ide.spi.meta.ResultType;
import com.alibaba.da.coin.ide.spi.standard.ResultModel;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;

public class AligenieResult {

    /**
     * 正常发送消息之前需要build一层
     * @param taskResult 构建的result
     * @return 完整返回结果
     */
    public static ResultModel<TaskResult> buildResultModel(TaskResult taskResult) {
        ResultModel<TaskResult> resultModel = new ResultModel<>();
        resultModel.setReturnCode("0");
        resultModel.setReturnValue(taskResult);
        return resultModel;
    }

    /**
     * 正常返回语音消息
     * @param msg 消息
     * @return 结果
     */
    public static TaskResult sendMsg(String msg) {
        TaskResult taskResult = new TaskResult();
        taskResult.setReply(msg);
        taskResult.setResultType(ResultType.RESULT);
        taskResult.setExecuteCode(ExecuteCode.SUCCESS);
        return taskResult;
    }

}
