package com.xiaomi.model.enumm;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ToSpeakType {
    TTS(0);

    public Integer type;

    public Integer getType() {
        return type;
    }

}
