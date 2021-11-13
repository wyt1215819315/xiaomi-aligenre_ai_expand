package com.xiaomi.model.receive;

import lombok.Data;

@Data
public class EventProperty {

    /**
     * 录音的语音识别结果文本
     */
    private String asr_text;

    /**
     * 录音所得的文件id
     */
    private String msg_file_id;

}
