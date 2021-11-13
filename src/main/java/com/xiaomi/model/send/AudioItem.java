package com.xiaomi.model.send;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class AudioItem {

    /**
     * 	音频流
     */
    private AudioStream stream;

}
