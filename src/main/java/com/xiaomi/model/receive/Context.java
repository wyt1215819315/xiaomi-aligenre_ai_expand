package com.xiaomi.model.receive;

import lombok.Data;

import java.util.List;

@Data
public class Context {

    /**
     *设备唯一标识
     */
    private String device_id;

    /**
     *OAuth账号绑定的额外信息
     */
    private String passport;

    /**
     *客户端APP相关信息
     */
    private List<ClientAppInfo> app_info;
}
