package com.xiaomi.model.receive;

import lombok.Data;

import java.util.List;

@Data
public class SlotInfo {

    /**
     * 此次请求命中的意图名称
     */
    private String intent_name;

    /**
     * 	此命中的意图是否确认了，所谓意图确认是指上一轮命中了这个意图，假如开发者配置了意图确认，平台会要求用户确认一次，假如这次用户确认了，会将这个bool值设置为true
     */
    private Boolean is_confirmed;

    /**
     * 该意图提取出的所有槽位值
     */
    private List<Slot> slots;

}
