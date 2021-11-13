package com.xiaomi.model.enumm;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UITemplateType {

    /**
     * 使用的模板的类型，0：list view，1：single image view，2：double image view
     */
    LIST_VIEW(0),SINGLE_IMAGE_VIEW(1),DOUBLE_IMAGE_VIEW(2);

    public Integer type;

    public Integer getType() {
        return type;
    }
}
