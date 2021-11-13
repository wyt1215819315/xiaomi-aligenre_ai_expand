package com.oldwu.genshin.enitty;

import java.io.Serializable;
import lombok.Data;

/**
 * genshin_cookie
 * @author 
 */
@Data
public class GenshinAccount implements Serializable {
    private Integer id;

    private Integer recordId;

    private String uid;

    private String cookie;

    private String server;

    private String other;

    private static final long serialVersionUID = 1L;
}