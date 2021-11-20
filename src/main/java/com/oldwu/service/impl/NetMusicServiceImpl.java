package com.oldwu.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oldwu.dao.SystemConfigDao;
import com.oldwu.entity.SystemConfig;
import com.oldwu.service.NetMusicService;
import com.oldwu.util.NeteaseMusicUtil;
import com.oldwu.word.Words;
import com.xiaomi.model.send.SendInfo;
import com.xiaomi.util.XiaomiResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetMusicServiceImpl implements NetMusicService {

    @Autowired
    private SystemConfigDao configDao;

    @Override
    public SendInfo searchNetmusicMusic(String song) {
        JSONArray array = NeteaseMusicUtil.searchMusicReturnResultIds(song);
        if (array.size() == 0) {
            return XiaomiResult.sendMsg(String.format(Words.NETMUSIC_NULL_MUSIC, song));
        }
        //获取cookie
        SystemConfig netmusic_cookie = configDao.selectByKey("netmusic_cookie");
        if (netmusic_cookie == null || netmusic_cookie.getValue() == null){
            return XiaomiResult.sendMsg(Words.NETMUSIC_NO_COOKIE);
        }
        JSONObject jsonObject = array.getJSONObject(0);
        //title,id
        String url = NeteaseMusicUtil.getPlayUrl(jsonObject.getString("id"), netmusic_cookie.getValue());
        if (StringUtils.isEmpty(url)){
            return XiaomiResult.sendMsg(Words.NETMUSIC_SEARCH_ERROR);
        }
        return XiaomiResult.sendVoice(url,String.format(Words.NETMUSIC_SUCCESS,jsonObject.getString("title")));
    }

    @Override
    public SendInfo getSongsInMyLove() {
        JSONArray mList = new JSONArray();
        mList.add("2208571686");
        String netmusic_cookie = configDao.selectByKey("netmusic_cookie").getValue();
        String netmusic_csrf = configDao.selectByKey("netmusic_csrf").getValue();
        List<String> playUrls = NeteaseMusicUtil.getPlayUrls(mList, netmusic_cookie, netmusic_csrf);
        return XiaomiResult.sendVoices(playUrls,Words.NETMUSIC_PLAY_LIST);
    }
}
