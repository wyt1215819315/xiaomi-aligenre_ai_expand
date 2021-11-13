package com.oldwu.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * system_config
 * @author 
 */
@Data
public class SystemConfig implements Serializable {
    private Integer id;

    private String key;

    private String value;

    private String type;

    private String other;

    private static final long serialVersionUID = 1L;
}