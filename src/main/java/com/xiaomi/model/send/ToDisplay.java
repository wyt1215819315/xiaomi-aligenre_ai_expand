package com.xiaomi.model.send;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class ToDisplay {

    /**
     *显示类型，目前支持0：文字，1：html，2：native ui，3：widgets，目前主要用在电视和手机等有屏设备
     */
    private Integer type;

    /**
     *	假如显示html则通过这个链接指定
     */
    private String url;

    /**
     *	显示的文字
     */
    private String text;

    /**
     *显示的模板，有屏设备支持一些显示模板，可以通过这个配置
     */
    private UITemplate ui_template;

}
