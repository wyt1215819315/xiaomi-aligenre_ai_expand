package com.xiaomi.model.send;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class ActionProperty {

    /**
     * 	要播放的文件id，用于play_msg动作
     */
    private JSONArray file_id_list;
}
