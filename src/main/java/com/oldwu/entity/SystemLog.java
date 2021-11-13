package com.oldwu.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * system_log
 * @author 
 */
@Data
public class SystemLog implements Serializable {
    private Long id;

    private String receiveText;

    private String receiveWhole;

    private String sendText;

    private String sendWhole;

    private String userid;

    private String type;

    private Date date;

    private String other;

    private static final long serialVersionUID = 1L;
}