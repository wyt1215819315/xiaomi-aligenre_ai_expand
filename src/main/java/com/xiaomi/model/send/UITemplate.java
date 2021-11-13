package com.xiaomi.model.send;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class UITemplate {

    /**
     * 使用的模板的类型，0：list view，1：single image view，2：double image view
     */
    private Integer type;

    /**
     * Ui template type为0时必填
     */
    private List<Item> items;

    /**
     * 开发者的logo地址
     */
    private String logo;

    /**
     * 	Ui template的type为1,2时必填
     */
    private Item item;

}
