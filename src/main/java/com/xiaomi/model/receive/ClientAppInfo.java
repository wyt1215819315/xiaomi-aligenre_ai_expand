package com.xiaomi.model.receive;

import lombok.Data;

@Data
public class ClientAppInfo {
    /**
     *包名
     */
    private String pkg_name;
    /**
     *版本号
     */
    private String version_name;
}
