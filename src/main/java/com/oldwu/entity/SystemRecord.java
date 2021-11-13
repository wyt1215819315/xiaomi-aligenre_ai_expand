package com.oldwu.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * system_record
 * @author 
 */
@Data
public class SystemRecord implements Serializable {
    private Integer id;

    /**
     * 小爱的唯一标识
     */
    private String xiaomiId;

    /**
     * 天猫精灵的唯一标识
     */
    private String aligenreId;

    /**
     * 字段创建时间
     */
    private Date addDate;

    private String type;

    private String other;

    private static final long serialVersionUID = 1L;
}