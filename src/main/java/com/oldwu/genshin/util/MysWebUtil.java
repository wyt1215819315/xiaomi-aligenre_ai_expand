package com.oldwu.genshin.util;

import com.oldwu.util.HttpUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MysWebUtil {

    private static final String API_SALT = "xV8v4Qu54lUKrEYFZkJhB8cuOh9Asafs";
    private static final String API_APP_VERSION = "2.11.1";
    private static final String API_CLIENT_TYPE = "5";


    public static String GetDS() {
//        String md5str = "h8w582wxwgqvahcdkpvdhbh2w9casgfl";
        String md5str = "14bmu1mz0yuljprsfgpvjh3ju2ni468r";
//        String md5str = "dmq2p7ka6nsu0d3ev6nex4k1ndzrnfiy";
        String i = "" + System.currentTimeMillis() / 1000;
        String r = MyRandom(6);
        String c = DigestUtils.md5Hex("salt=" + md5str + "&t=" + i + "&r=" + r);
        return i + "," + r + "," + c;
    }

    /**
     * 米游社加密DS算法
     *
     * @return
     */
    public static String GetDS(Map<String, String> query, String body) {
        String i = "" + System.currentTimeMillis() / 1000;
        String r = MyRandom(6);
        String q = "";
        if (query != null) {
            Set<String> queryKeys = query.keySet();
            List<String> que = new ArrayList<>();
            for (String queryKey : queryKeys) {
                que.add(queryKey + "=" + query.get(queryKey));
            }
            q = StringUtils.join(que, "&");
        }
        String c = DigestUtils.md5Hex("salt=" + API_SALT + "&t=" + i + "&r=" + r + "&b=" + body + "&q=" + q);
        return i + "," + r + "," + c;
    }

    /**
     * mhy转用随机数算法
     *
     * @param len
     * @return
     */
    static String MyRandom(int len) {
        Random rd = new Random();
        char[] x = "1234567890abcdefghijklmnopqrstuvwxyz".toCharArray();//ABCDEFGHIJKLMNOPQRSTUVWXYZ
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = x[rd.nextInt(x.length)];
        }
        return new String(str);
    }

    public static Map<String, String> getPcHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.put("Accept-Language", "zh-cn");
        headers.put("Connection", "keep-alive");
        headers.put("Host", "api-takumi.mihoyo.com");
        headers.put("x-rpc-app_version", "2.11.0");
        headers.put("x-rpc-client_type", "4");
        headers.put("Referer", "https://bbs.mihoyo.com/");
//        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36");
        headers.put("DS", GetDS());
        return headers;
    }

    public static HttpResponse mysGet(String url, Map<String, String> querys, String cookie) throws Exception {
        Map<String, String> headers = getHeaders(querys, cookie, "");
        return HttpUtils.doGet(url, null, headers, querys);
    }

    public static HttpResponse mysPost(String url, String cookie, String query) throws Exception {
        Map<String, String> headers = getHeaders(null, cookie, query);
        return HttpUtils.doPost(url, null, headers, null, query);
    }

    /**
     * 设置header头来模拟验证
     *
     * @return
     */
    public static Map<String, String> getHeaders(Map<String, String> querys, String cookie, String body) {
        Map<String, String> headers = new HashMap<>();
//        headers.put("Accept-Encoding", "gzip, deflate, br");
//        headers.put("Accept-Language", "zh-cn");
//        headers.put("Connection", "keep-alive");
//        headers.put("Host", "api-takumi.mihoyo.com");
        headers.put("x-rpc-app_version", API_APP_VERSION);
        headers.put("Referer", "https://webstatic.mihoyo.com/");
        headers.put("x-rpc-client_type", API_CLIENT_TYPE);
        headers.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) miHoYoBBS/2.11.1");
        headers.put("Cookie", cookie);
        headers.put("DS", GetDS(querys, body));
        return headers;
    }

    /**
     * 从输入流获取字符串
     *
     * @param content
     * @return
     */
    public static String getContent(InputStream content) {
        StringBuilder out = new StringBuilder();
        int bufferSize = 1024;
        char[] buffer = new char[bufferSize];
        Reader in = null;
        try {
            in = new InputStreamReader(content, StandardCharsets.UTF_8);
            for (; ; ) {
                int rsz = in.read(buffer, 0, buffer.length);
                if (rsz < 0)
                    break;
                out.append(buffer, 0, rsz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    /**
     * 从输入流获取字符串
     *
     * @param content
     * @return
     */
    public static String getContentGBK(InputStream content) {
        StringBuilder out = new StringBuilder();
        int bufferSize = 1024;
        char[] buffer = new char[bufferSize];
        Reader in = null;
        try {
            in = new InputStreamReader(content, "GB2312");
            for (; ; ) {
                int rsz = in.read(buffer, 0, buffer.length);
                if (rsz < 0)
                    break;
                out.append(buffer, 0, rsz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    /**
     * 获取原神个人信息，返回json字符串
     *
     * @param uid    米游社的uid！！
     * @param cookie
     * @return
     */
    public static String getPrimaryInfo(String uid, String cookie) {
        String url = "https://api-takumi.mihoyo.com/game_record/app/card/wapi/getGameRecordCard";
        Map<String, String> query = new HashMap<>();
        query.put("uid", uid);
        try {
            HttpResponse httpResponse = mysGet(url, query, cookie);
            HttpEntity entity = httpResponse.getEntity();
            InputStream content = entity.getContent();
            //System.out.println(content1);
            return getContent(content);
        } catch (Exception e) {
            return "error" + e.getMessage();
        }
    }

    /**
     * 获取原神深渊信息
     *
     * @param uid
     * @param server
     * @param cookie
     * @return
     */
    public static String getShenYuanInfo(String uid, String server, String cookie, String schedule_type) {
        if (server == null || server.equals("")) {
            server = "cn_gf01";
        }
        String url = "https://api-takumi.mihoyo.com/game_record/app/genshin/api/spiralAbyss";
        Map<String, String> query = new TreeMap<>();
        query.put("role_id", uid);
        query.put("schedule_type", schedule_type);   //1or2
        query.put("server", server);
        try {
            HttpResponse httpResponse = mysGet(url, query, cookie);
            HttpEntity entity = httpResponse.getEntity();
            InputStream content = entity.getContent();
            //System.out.println(content1);
            return getContent(content);
        } catch (Exception e) {
            return "error" + e.getMessage();
        }
    }

    /**
     * 获取原神概览信息，返回json字符串
     *
     * @param uid
     * @param server
     * @param cookie
     * @return
     */
    public static String getMainInfo(String uid, String server, String cookie) {
        if (server == null || server.equals("")) {
            server = "cn_gf01";
        }
        String url = "https://api-takumi.mihoyo.com/game_record/app/genshin/api/index";
        Map<String, String> query = new TreeMap<>();
        query.put("role_id", uid);
        query.put("server", server);
        try {
            HttpResponse httpResponse = mysGet(url, query, cookie);
            HttpEntity entity = httpResponse.getEntity();
            InputStream content = entity.getContent();
            //System.out.println(content1);
            return getContent(content);
        } catch (Exception e) {
            return "error" + e.getMessage();
        }
    }

    /**
     * 获取原神角色信息，返回json字符串
     *
     * @param uid
     * @param server
     * @param cookie
     * @param selectid
     * @return
     */
    public static String getjueseInfo(String uid, String server, String cookie, String selectid) {
        if (server == null || server.equals("")) {
            server = "cn_gf01";
        }
        String url = "https://api-takumi.mihoyo.com/game_record/app/genshin/api/character";
        String postdata = "{\"character_ids\":[" + selectid + "],\"role_id\":\"" + uid + "\",\"server\":\"" + server + "\"}";
        try {
            HttpResponse httpResponse = mysPost(url, cookie, postdata);
            HttpEntity entity = httpResponse.getEntity();
            InputStream content = entity.getContent();
            //System.out.println(content1);
            return getContent(content);
        } catch (Exception e) {
            return "error" + e.getMessage();
        }
    }

    /**
     * 获取实时体力等信息，需要cookie拥有者
     * @param uid
     * @param server
     * @param cookie
     * @return
     */
    public static String getDailyNoteInfo(String uid, String server, String cookie) {
        if (server == null || server.equals("")) {
            server = "cn_gf01";
        }
        String url = "https://api-takumi.mihoyo.com/game_record/app/genshin/api/dailyNote";
        Map<String, String> query = new TreeMap<>();
        query.put("role_id", uid);
        query.put("server", server);
        try {
            HttpResponse httpResponse = mysGet(url, query, cookie);
            HttpEntity entity = httpResponse.getEntity();
            InputStream content = entity.getContent();
            //System.out.println(content1);
            return getContent(content);
        } catch (Exception e) {
            return "error" + e.getMessage();
        }
    }

}
