package com.oldwu.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * u_turnoff_computer
 * @author 
 */
@Data
public class UTurnoffComputer implements Serializable {
    private Integer id;

    private Integer recordId;

    private String secertKey;

    private String status;

    private Date updateTime;

    private String other;

    private static final long serialVersionUID = 1L;
}