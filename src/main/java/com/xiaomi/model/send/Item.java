package com.xiaomi.model.send;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class Item {

    /**
     * 	图片的样式
     */
    private String image_style;

    /**
     * 	图片列表
     */
    private JSONArray images;

    /**
     * 响应点击事件
     */
    private String intent;

    /**
     * 	标题
     */
    private String title;

    /**
     * 主题内容
     */
    private JSONObject body;

    /**
     * 背景图片
     */
    private String background_image;
}
