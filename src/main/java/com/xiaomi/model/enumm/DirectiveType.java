package com.xiaomi.model.enumm;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DirectiveType {
    /**
     * 动作的类型，audio播放音频，tts播放文字
     */
    AUDIO("audio"), TTS("tts");

    public String type;

    @Override
    public String toString() {
        return type;
    }
}
