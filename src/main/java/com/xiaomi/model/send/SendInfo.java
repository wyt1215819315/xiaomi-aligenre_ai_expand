package com.xiaomi.model.send;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.JsonObject;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class SendInfo {

    /**
     * 返回的协议版本，默认请用1.0
     */
    private String version;

    /**
     * 期望小爱技能平台帮忙持久化的jsobject
     */
    private JsonObject session_attributes;

    /**
     * 是否结束当前会话
     */
    private Boolean is_session_end;

    /**
     * 返回的具体信息
     */
    private Response response;

    public String getVersion() {
        if (StringUtils.isEmpty(version)){
            return "1.0";
        }
        return version;
    }

    public Boolean getIs_session_end() {
        if (is_session_end == null){
            return false;
        }
        return is_session_end;
    }
}
