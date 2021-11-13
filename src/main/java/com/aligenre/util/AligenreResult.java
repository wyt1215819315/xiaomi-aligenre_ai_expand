package com.aligenre.util;

import com.alibaba.da.coin.ide.spi.standard.ResultModel;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;

public class AligenreResult {

    public static ResultModel<TaskResult> buildResultModel(TaskResult taskResult) {
        ResultModel<TaskResult> resultModel = new ResultModel<>();
        resultModel.setReturnCode("0");
        resultModel.setReturnValue(taskResult);
        return resultModel;
    }

}
