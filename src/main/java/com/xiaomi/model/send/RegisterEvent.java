package com.xiaomi.model.send;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class RegisterEvent {

    /**
     * 事件名称，目前仅支持：mediaplayer.playbacknearlyfinished
     */
    private String event_name;

}
