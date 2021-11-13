package com.xiaomi.model.receive;

import lombok.Data;

@Data
public class Request {

    /**
     *	请求的类型，分别进行标识。
     * 0：技能进入请求；
     * 1：技能进行中请求；
     * 2：请求结束请求。
     */
    private Integer type;

    /**
     *请求的唯一标识
     */
    private String request_id;

    /**
     *时间戳
     */
    private Long timestamp;

    /**
     *用户是否没响应小爱
     * （例如用户没有在小爱音箱旁边）
     */
    private Boolean no_response;

    /**
     *事件类型
     */
    private String event_type;

    /**
     *事件的相关信息
     */
    private EventProperty event_property;

    /**
     *本地化设置
     */
    private String Locale;

    /**
     *意图信息
     */
    private SlotInfo slot_info;

}
