package com.xiaomi.model.receive;

import lombok.Data;

@Data
public class ReceiveInfo {

    /**
     * 版本号，当前为1.0
     */
    private String version;

    /**
     * 用户语音输入的识别结果
     */
    private String query;

    /**
     * 一轮对话相关的信息
     */
    private Session session;

    /**
     * 上下文相关的信息
     */
    private Context context;

    /**
     * 请求相关的信息
     */
    private Request request;

}
