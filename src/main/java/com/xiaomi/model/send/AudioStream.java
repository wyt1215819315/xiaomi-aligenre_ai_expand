package com.xiaomi.model.send;

import lombok.Data;

@Data
public class AudioStream {

    /**
     * 	播放资源鉴权用的token
     */
    private String token;

    /**
     * 	播放地址
     */
    private String url;

    /**
     * 	偏移地址
     */
    private Long offset_in_milliseconds;

}
