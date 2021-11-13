package com.oldwu.util;

public class ReplyUtils {

    /**
     * 在string数组中随机挑选一个进行回复
     *
     * @param words
     * @return
     */
    public static String randomReply(String[] words) {
        if (words.length == 0) {
            return "";
        }
        int random = RandomUtil.getRandom(0, words.length - 1);
        return words[random];
    }

    public static void main(String[] args) {

    }

}
