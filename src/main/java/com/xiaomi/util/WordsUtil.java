package com.xiaomi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsUtil {

    public static boolean checkWords(String msg, String[] words) {
        for (String word : words) {
            if (msg.contains(word)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查正则是否匹配
     */
    public static Map<String, Object> checkWordsRegx(String msg, String[] regxs) {
        Map<String,Object> map = new HashMap<>();
        List<String> returnList = new ArrayList<>();
        for (String regx : regxs) {
            Pattern r = Pattern.compile(regx);
            // 现在创建 matcher 对象
            Matcher m = r.matcher(msg);
            if (m.find()){
                //找到匹配，获取捕获组个数
                int i = m.groupCount();
                //注意group(0)为获取所有
                for (int j = 1; j <= i; j++) {
                    returnList.add(m.group(j));
                }
                //然后直接返回结果
                map.put("flag",true);
                map.put("list",returnList);
                return map;
            }
        }
        map.put("flag",false);
        return map;
    }

}
