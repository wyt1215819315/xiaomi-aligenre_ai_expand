package com.xiaomi.model.enumm;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TTSItemType {
    /**
     * 	tts的类型，目前仅支持text：普通文本
     */
    TEXT("text");

    public String type;

    @Override
    public String toString() {
        return type;
    }
}
