package com.xiaomi.model.receive;

import lombok.Data;

@Data
public class Slot {

    /**
     * 槽位名称
     */
    private String name;

    /**
     * 提取出的槽位值，例如city槽位可能提取的值是北京
     */
    private String value;

    /**
     * 追问超过开发者设置的次数会通过这个值告诉开发者。
     */
    private Boolean is_inquire_failed;
}
