package com.oldwu.genshin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oldwu.genshin.dao.GenshinAccountDao;
import com.oldwu.genshin.enitty.GenshinAccount;
import com.oldwu.genshin.service.GenshinService;
import com.oldwu.genshin.util.MysWebUtil;
import com.oldwu.util.NumberUtil;
import com.oldwu.word.Words;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenshinServiceImpl implements GenshinService {
    private final Log logger = LogFactory.getLog(GenshinServiceImpl.class);

    @Autowired
    private GenshinAccountDao genshinAccountDao;


    /**
     * 查询该唯一id的绑定信息
     *
     * @param id   唯一字段id
     * @param type 小爱/天猫精灵
     * @return
     */
    @Override
    public GenshinAccount queryBandGenshinAccount(String id, String type) {
        if (type.equals("xiaomi")) {
            return genshinAccountDao.queryGenshinAccount(id, null);
        } else if (type.equals("aligenie")) {
            return genshinAccountDao.queryGenshinAccount(null, id);
        } else {
            logger.error("传入类型错误！请检查代码！");
            return null;
        }
    }

    /**
     * @param id        唯一用户标识id
     * @param type      小米/天猫
     * @param queryType 查询类型(power/all)
     * @return
     */
    @Override
    public String getDailyNote(String id, String type, String queryType) {
        GenshinAccount genshinAccount = queryBandGenshinAccount(id, type);
        if (genshinAccount == null || StringUtils.isEmpty(genshinAccount.getCookie()) || StringUtils.isEmpty(genshinAccount.getUid())) {
            return Words.GENSHIN_REPLY_POWER_NOBAND;
        }
        String reply = "";
        String json = MysWebUtil.getDailyNoteInfo(genshinAccount.getUid(), genshinAccount.getServer(), genshinAccount.getCookie());
        JSONObject jsonObject = JSON.parseObject(json);
        JSONObject data = jsonObject.getJSONObject("data");
        //当前体力/最大体力
        Integer now_power = data.getInteger("current_resin");
        Integer max_power = data.getInteger("max_resin");
        String power_time = data.getString("resin_recovery_time");
        if (now_power.equals(max_power)) {
            //溢出了
            reply = Words.GENSHIN_REPLY_POWER_MAX;
        } else if (now_power >= max_power * 0.8) {
            //快溢出了
            String d = NumberUtil.autoFormatTimeWithOutSecond(Integer.parseInt(power_time));
            reply = String.format(Words.GENSHIN_REPLY_POWER_BIG, now_power, d);
        } else {
            //正常情况
            String d = NumberUtil.autoFormatTimeWithOutSecond(Integer.parseInt(power_time));
            reply = String.format(Words.GENSHIN_REPLY_POWER, now_power, d);
        }
        if (queryType.equals("all")) {
            //回复每日委托情况
            Integer finished_task_num = data.getInteger("finished_task_num");
            Integer total_task_num = data.getInteger("total_task_num");
            if (finished_task_num.equals(total_task_num)) {
                reply = reply + Words.GENSHIN_REPLY_DAILY_COMPLETE;
                Boolean is_extra_task_reward_received = data.getBoolean("is_extra_task_reward_received");
                if (!is_extra_task_reward_received){
                    reply = reply + Words.GENSHIN_REPLY_DAILY_COMPLETE_WITHOUT_PRIZE;
                }
            } else if (finished_task_num == 0) {
                reply = reply + Words.GENSHIN_REPLY_DAILY_UNCOMPLETE;
            } else {
                reply = reply + String.format(Words.GENSHIN_REPLY_DAILY, finished_task_num);
            }
            //回复探索派遣信息
            Integer current_expedition_num = data.getInteger("current_expedition_num");
            Integer max_expedition_num = data.getInteger("max_expedition_num");
            if (current_expedition_num.equals(max_expedition_num)){
                reply = reply + Words.GENSHIN_REPLY_SEARCH_COMPLETE;
            }else if (current_expedition_num != 0){
                reply = reply + String.format(Words.GENSHIN_REPLY_SEARCH,current_expedition_num);
            }
        }
        return reply;
    }
}
