package com.oldwu.service.impl;

import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import com.oldwu.dao.SystemRecordDao;
import com.oldwu.entity.SystemRecord;
import com.oldwu.genshin.service.GenshinService;
import com.oldwu.service.AsyncService;
import com.oldwu.service.BaseService;
import com.oldwu.service.NetMusicService;
import com.oldwu.service.UService;
import com.oldwu.word.Words;
import com.xiaomi.model.receive.ReceiveInfo;
import com.xiaomi.model.receive.User;
import com.xiaomi.model.send.SendInfo;
import com.xiaomi.util.HelpUtils;
import com.xiaomi.util.WordsUtil;
import com.xiaomi.util.XiaomiResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BaseServiceImpl implements BaseService {
    private final Log logger = LogFactory.getLog(BaseServiceImpl.class);
    @Autowired
    private GenshinService genshinService;

    @Autowired
    private NetMusicService netMusicService;

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private UService uService;

    @Override
    public SendInfo xiaomiReply(ReceiveInfo receiveInfo) {
        //首先向数据库插入唯一标记值，如果已经有了就不插入
        String user_id = receiveInfo.getSession().getUser().getUser_id();
        asyncService.insertOnlyId(user_id,"xiaomi");
        String queryMessage = HelpUtils.getQueryMessage(receiveInfo);
        String replyStr = "";
        if (WordsUtil.checkWords(queryMessage, Words.ENTER_ROBOT)) {
            return XiaomiResult.answerQuestion("请吩咐");
        }
        //首先匹配正则
        Map<String, Object> map;
        if ((boolean)((map = WordsUtil.checkWordsRegx(queryMessage, Words.REGX_NETMUSIC_MY_LOVE)).get("flag"))) {
            //网易云播放歌单
            return netMusicService.getSongsInMyLove();
        }
        if ((boolean)((map = WordsUtil.checkWordsRegx(queryMessage, Words.REGX_NETMUSIC)).get("flag"))) {
            //网易云搜索歌曲
            List<String> list = (List<String>) map.get("list");
            if (list.size() == 0){
                return XiaomiResult.sendMsg(Words.NETMUSIC_CANT_UNDERSTAND);
            }
            return netMusicService.searchNetmusicMusic(list.get(0));
        }
        //然后进行模糊匹配
        if (WordsUtil.checkWords(queryMessage, Words.GENSHIN_POWER)) {
            //原神体力查询
            replyStr = genshinService.getDailyNote(user_id, "xiaomi", "power");
        } else if (WordsUtil.checkWords(queryMessage, Words.GENSHIN)) {
            //原神体力等信息查询
            replyStr = genshinService.getDailyNote(user_id, "xiaomi", "all");
        }else if (WordsUtil.checkWords(queryMessage,Words.CLOSE_COMPUTER)){
            replyStr = uService.sendTurnOffComputer(user_id,"xiaomi");
        }
        else {
            return XiaomiResult.cantUnderstand();
        }
        return XiaomiResult.sendMsg(replyStr);
    }

    @Override
    public TaskResult aligenreReply(TaskQuery taskQuery) {
        return null;
    }
}
