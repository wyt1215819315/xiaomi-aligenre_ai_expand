package com.xiaomi.model.send;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class Directive {

    /**
     * 动作的类型，audio播放音频，tts播放文字
     */
    private String type;

    /**
     * 音频内容
     */
    private AudioItem audio_item;

    /**
     * tts内容
     */
    private TTSItem tts_item;

}
