package com.xiaomi.model.send;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class Response {

    /**
     * 	tts要说的话，通常简单的回复可以使用这个字段
     */
    private ToSpeak to_speak;

    /**
     * 有屏设备显示的数据
     */
    private ToDisplay to_display;

    /**
     * 设备的复杂操作通常放在这里，例如多句tts，url音频播放等
     */
    private List<Directive> directives;

    /**
     * 是否需要开麦（相关指导建议详见最佳实践），默认不开麦
     */
    private Boolean open_mic;

    /**
     * 是否理解用户的请求，具体作用详见最佳实践
     */
    private Boolean not_understand;

    /**
     * 动作类型, 常见的动作有：leave_msg, play_msg等
     */
    private String action;

    /**
     * 动作属性
     */
    private ActionProperty action_property;

    /**
     * 需要注册的事件类型
     */
    private List<RegisterEvent> register_events;
}
