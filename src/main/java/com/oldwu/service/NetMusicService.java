package com.oldwu.service;

import com.alibaba.fastjson.JSONArray;
import com.xiaomi.model.send.SendInfo;

public interface NetMusicService {

    SendInfo searchNetmusicMusic(String song);

    SendInfo getSongsInMyLove();

}
