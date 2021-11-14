package com.oldwu.word;

public class Words {

    /**
     * 一些关键字
     */
    public static final String[] GENSHIN_POWER = {"体力","树脂"};
    public static final String[] GENSHIN = {"原神","原声","元神"};
    public static final String[] ENTER_ROBOT = {"无敌机器人"};
    public static final String[] CLOSE_COMPUTER = {"关闭电脑","电脑关机","关掉电脑","关闭我的电脑"};

    /**
     * 网易云
     */
    public static final String[] REGX_NETMUSIC = {
            "播放([\\s\\S]+)[的歌]*",
            "解析([\\s\\S]+)",
            "我要听([\\s\\S]+[的歌]*)",
            "我想听([\\s\\S]+)[的歌]*",
            "来一首([\\s\\S]+)[的歌]*",
    };
    public static final String NETMUSIC_CANT_UNDERSTAND = "小爱没有识别到你想搜索的歌曲哦";
    public static final String NETMUSIC_NO_COOKIE = "服务端没有配置cookie，无法获取歌曲链接";
    public static final String NETMUSIC_SEARCH_ERROR = "歌曲链接获取失败！";
    public static final String NETMUSIC_SUCCESS = "让小爱和你一起听%s吧！";
    public static final String NETMUSIC_NULL_MUSIC = "小爱没有搜索到有关%s的歌曲哦";

    /**
     * 关闭电脑
     */
    public static final String SEND_TURNOFF_COMPUTER = "好的，关机指令已经发送";
    public static final String SEND_TURNOFF_COMPUTER_NOT_BAND = "系统错误，没有找到绑定的信息";


    /**
     * 原神回复字段
     */
    public static final String GENSHIN_REPLY_POWER = "你当前绑定原神账号的体力为%s点，预计%s后回满；";
    public static final String GENSHIN_REPLY_POWER_BIG = "你当前绑定原神账号的体力为%s点，预计%s后回满，记得及时上号清体力哦；";
    public static final String GENSHIN_REPLY_POWER_MAX = "你当前绑定原神账号的体力已经溢出啦，请火速上号清理；";
    public static final String GENSHIN_REPLY_POWER_NOBAND = "查询当前绑定信息失败,你可能没有绑定原神账号信息";
    //每日委托
    public static final String GENSHIN_REPLY_DAILY = "每日委托已经完成%s个；";
    public static final String GENSHIN_REPLY_DAILY_COMPLETE = "每日委托已经完成；";
    public static final String GENSHIN_REPLY_DAILY_COMPLETE_WITHOUT_PRIZE = "但是你还没有去冒险家协会领取额外奖励哦；";
    public static final String GENSHIN_REPLY_DAILY_UNCOMPLETE = "未做每日委托；";
    //探索派遣
    public static final String GENSHIN_REPLY_SEARCH_COMPLETE = "探索派遣已经全部完成；";
    public static final String GENSHIN_REPLY_SEARCH = "探索派遣已经完成%s个；";


    /**
     * 一些回复字段
     */
    //不能理解语句
    public static final String[] XIAOAI_CANT_UNDERSTAND = {"小爱还不能理解你的意思呢","小爱没有听懂","对不起，小爱暂时不懂呢","你问的问题太深奥了"};
    public static final String[] ALIGENRE_CANT_UNDERSTAND = {"我还不能理解你的意思呢","我还是个小精灵，不能理解哦","对不起，这个问题我暂时不懂呢","你问的问题太深奥了"};
    public static final String[] ALIGENRE_KEQING_CANT_UNDERSTAND = {"刻晴还不能理解你的意思呢","吾乃璃月七星，这样的问题我不屑回答","你这样子问，也太难为刻晴了","你问的问题太深奥了"};

}
