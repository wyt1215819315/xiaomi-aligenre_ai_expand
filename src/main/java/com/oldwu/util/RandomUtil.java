package com.oldwu.util;


import java.util.Random;

public class RandomUtil {

    /**
     * 生成[min,max]之间的随机数
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }


}
