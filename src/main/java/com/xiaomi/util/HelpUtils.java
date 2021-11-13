package com.xiaomi.util;

import com.xiaomi.model.receive.ReceiveInfo;

public class HelpUtils {

    public static String getQueryMessage(ReceiveInfo receiveInfo){
        return receiveInfo.getQuery();
    }

}
