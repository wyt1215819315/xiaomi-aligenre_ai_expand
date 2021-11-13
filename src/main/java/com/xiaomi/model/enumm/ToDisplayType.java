package com.xiaomi.model.enumm;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ToDisplayType {
    /**
     * 显示类型，目前支持0：文字，1：html，2：native ui，3：widgets，目前主要用在电视和手机等有屏设备
     */
    TEXT(0),HTML(1),NATIVE_UI(2),WIDGETS(3);

    public Integer type;

    public Integer getType() {
        return type;
    }
}
