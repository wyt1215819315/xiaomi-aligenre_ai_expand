package com.xiaomi.model.send;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xiaomi.model.enumm.TTSItemType;
import lombok.Data;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class TTSItem {

    /**
     * 	tts的类型，目前仅支持text：普通文本
     */
    private String type;

    /**
     * 	文本内容
     */
    private String text;

    public String getType() {
        if (type == null){
            return TTSItemType.TEXT.toString();
        }
        return type;
    }
}
