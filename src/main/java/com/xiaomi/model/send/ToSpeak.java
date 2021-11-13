package com.xiaomi.model.send;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xiaomi.model.enumm.ToSpeakType;
import lombok.Data;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class ToSpeak {

    /**
     * tts的类型，目前仅支持0：TTS
     */
    private Integer type;

    /**
     * tts要说的文本
     */
    private String text;

    public Integer getType() {
        if (type == null) {
            return ToSpeakType.TTS.getType();
        }
        return type;
    }
}
