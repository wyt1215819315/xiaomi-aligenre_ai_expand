package com.xiaomi.model.receive;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class Session {

    /**
     * 唯一标识一轮session
     */
    private String session_id;

    /**
     *当前的技能信息
     */
    private Application application;

    /**
     * 可存储需要持久化的数据，每次请求都会带上
     */
    private JSONObject attributes;

    /**
     * 用户相关信息
     */
    private User user;
}
