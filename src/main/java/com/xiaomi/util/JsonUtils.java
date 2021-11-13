package com.xiaomi.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaomi.model.receive.ReceiveInfo;

public class JsonUtils {

    public static ReceiveInfo getReceiveInfoFromJson(String json){
        JSONObject jsonObject = JSON.parseObject(json);
        return JSON.toJavaObject(jsonObject, ReceiveInfo.class);
    }



}
