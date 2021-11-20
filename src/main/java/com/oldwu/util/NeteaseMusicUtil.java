package com.oldwu.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 网易云音乐解析,搜索不需要cookie，解析需要账号cookie
 */
public class NeteaseMusicUtil {
    private static final String key = "0CoJUm6Qyw8W8jud";
    private static final String f = "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7";
    private static final String e = "010001";
    private static final String cookie = "";
    private static final String post_url1 = "https://music.163.com/weapi/user/getfolloweds?csrf_token=";
    private static final String post_url2 = "https://music.163.com/weapi/v1/play/record?csrf_token=";
    private static final String url = "https://music.163.com/weapi/song/enhance/player/url/v1?csrf_token=";
    private static final String searchUrl = "http://music.163.com/api/search/get/web";
    private static final String limit = "10";
    private static final String imageUrl = "https://api.vvhan.com/api/music";
    private static final String login_url = "https://music.163.com/weapi/w/login/cellphone?csrf_token=";
    private static final String level_url = "https://music.163.com/weapi/user/level";
    private static final String sign_url = "https://music.163.com/weapi/point/dailyTask?";
    private static final String recommend_url = "https://music.163.com/weapi/v1/discovery/recommend/resource";
    private static final String private_url = "https://music.163.com/weapi/user/playlist?csrf_token=";
    private static final String detail_url = "https://music.163.com/weapi/v6/playlist/detail?csrf_token=";
    private static final String feedback_url = "http://music.163.com/weapi/feedback/weblog";


    public static void main(String[] args) {
//5238992,list 2208571686
        String playUrl = getPlayUrl("86357", cookie);
        System.out.println(playUrl);
//        JSONArray test = searchMusicReturnResultIds("生生世世爱");
//        System.out.println(test);
    }

    /**
     * 根据id匹配曲图
     *
     * @param musicId
     * @return
     */
    public static Map<String, String> getImageAndSoOnById(String musicId) {
        Map<String, String> map = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        querys.put("id", musicId);
        querys.put("type", "song");
        querys.put("media", "netease");
        try {
            HttpResponse httpResponse = HttpUtils.doGet(imageUrl, null, HttpUtils.getHeaders(), querys);
            JSONObject json = HttpUtils.getJson(httpResponse);
            map.put("cover", json.getString("cover"));
            map.put("name", json.getString("name"));
            map.put("mp3url", json.getString("mp3url"));
            map.put("song_id", json.getString("song_id"));
            map.put("author", json.getString("author"));
            return map;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 输出排版好的搜索结果
     *
     * @param musicName
     * @return
     */
    public static String searchMusicReturnResult(String musicName) {
        JSONObject jsonObject = searchMusic(musicName, limit);
        JSONArray songs = jsonObject.getJSONObject("result").getJSONArray("songs");
        StringBuilder stringBuilder = new StringBuilder("-------网易云解析-------\n");
        for (int i = 0; i < songs.size(); i++) {
            JSONObject song = songs.getJSONObject(i);
            String songName = song.getString("name") + "_" + song.getJSONObject("album").getString("name");
            stringBuilder.append(i + 1).append(". ").append(songName);
            JSONArray alias = song.getJSONObject("album").getJSONArray("alia");
            if (alias != null && alias.size() > 0) {
                stringBuilder.append("(").append(alias.get(0)).append(")");
            }
            JSONArray artists = song.getJSONArray("artists");
            stringBuilder.append(" - ");
            for (int i1 = 0; i1 < artists.size(); i1++) {
                stringBuilder.append(artists.getJSONObject(i1).getString("name"));
                if (i1 != artists.size() - 1) {
                    stringBuilder.append("|");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * 输出搜索到的歌曲id数组
     *
     * @param musicName
     * @return
     */
    public static JSONArray searchMusicReturnResultIds(String musicName) {
        JSONObject jsonObject = searchMusic(musicName, limit);
        JSONArray songs = jsonObject.getJSONObject("result").getJSONArray("songs");
        JSONArray infos = new JSONArray();
        for (int i = 0; i < songs.size(); i++) {
            JSONObject song = songs.getJSONObject(i);
            JSONObject info = new JSONObject();
            info.put("id", song.getString("id"));
            String songName = song.getString("name");
//            + "_" + song.getJSONObject("album").getString("name")
            JSONArray alias = song.getJSONObject("album").getJSONArray("alia");
            if (alias != null && alias.size() > 0) {
                info.put("content", alias.get(0));
            }
            JSONArray artists = song.getJSONArray("artists");
            String title = songName + " - ";
            for (int i1 = 0; i1 < artists.size(); i1++) {
                title = title + artists.getJSONObject(i1).getString("name");
                if (i1 != artists.size() - 1) {
                    title = title + "|";
                }
            }
            info.put("title", title);
            infos.set(i, info);
        }
        return infos;
    }

    /**
     * 输出排版好的搜索结果和一个歌曲id数组
     *
     * @param musicName
     * @return
     */
    public static Map<String, String> searchMusicReturnResultBot(String musicName) {
        Map<String, String> map = new HashMap<>();
        JSONObject jsonObject = searchMusic(musicName, limit);
        JSONArray songs = jsonObject.getJSONObject("result").getJSONArray("songs");
        JSONArray infos = new JSONArray();
        StringBuilder stringBuilder = new StringBuilder("-------网易云解析-------\n");
        for (int i = 0; i < songs.size(); i++) {
            JSONObject song = songs.getJSONObject(i);
            JSONObject info = new JSONObject();
            info.put("id", song.getString("id"));
            String songName = song.getString("name") + "_" + song.getJSONObject("album").getString("name");
            stringBuilder.append(i + 1).append(". ").append(songName);
            JSONArray alias = song.getJSONObject("album").getJSONArray("alia");
            if (alias != null && alias.size() > 0) {
                stringBuilder.append("(").append(alias.get(0)).append(")");
                info.put("content", alias.get(0));
            }
            JSONArray artists = song.getJSONArray("artists");
            String title = songName + " - ";
            stringBuilder.append(" - ");
            for (int i1 = 0; i1 < artists.size(); i1++) {
                stringBuilder.append(artists.getJSONObject(i1).getString("name"));
                title = title + artists.getJSONObject(i1).getString("name");
                if (i1 != artists.size() - 1) {
                    stringBuilder.append("|");
                    title = title + "|";
                }
            }
            info.put("title", title);
            infos.set(i, info);
            stringBuilder.append("\n");
        }
        map.put("str", stringBuilder.toString());
        map.put("infos", infos.toJSONString());
        return map;
    }

    /**
     * 搜索音乐
     *
     * @param musicName
     * @param limit
     * @return
     */
    public static JSONObject searchMusic(String musicName, String limit) {
        Map<String, String> querys = new HashMap<>();
        querys.put("s", musicName);
        querys.put("type", "1");
//        querys.put("offset","0");
        querys.put("total", "true");
        querys.put("limit", limit);
        try {
            HttpResponse httpResponse = HttpUtils.doGet(searchUrl, null, HttpUtils.getHeaders(), querys);
            return HttpUtils.getJson(httpResponse);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 根据id获取播放链接
     *
     * @param musicId
     * @return
     */
    public static String getPlayUrl(String musicId, String cookie) {
        JSONObject connect = connect(musicId, cookie);
        if (connect == null) {
            return null;
        }
        JSONArray data = connect.getJSONArray("data");
        JSONObject jsonObject = data.getJSONObject(0);
        return jsonObject.getString("url");
    }

    /**
     * 根据歌单ID获取歌曲链接数组
     * @return
     */
    public static List<String> getPlayUrls(JSONArray mList, String cookie, String csrf){
        int num1 = 15;
        Map<String, String> listMusics = getListMusics(mList, csrf, cookie);
        String musiclistsJson = listMusics.get("musiclists");
        JSONArray musicLists = JSON.parseArray(musiclistsJson);
        List<String> musics = new ArrayList<>();
        if (musicLists.size() > num1) {
            //添加设定数量
            int[] ints = NumberUtil.randomCommon(1, musicLists.size(), num1);
            for (int anInt : ints) {
                musics.add(musicLists.getString(anInt));
            }
        } else {
            //添加全部
            for (int i = 0; i < musicLists.size(); i++) {
                musics.add(musicLists.getString(i));
            }
        }
        List<String> urls = new ArrayList<>();
        for (String music : musics) {
            String playUrl = getPlayUrl(music, cookie);
            urls.add(playUrl);
        }
        return urls;
    }


    public static String aESencrypt(String msg, String key) {
        int padding = 16 - msg.length() % 16;
        char ch = (char) (padding);
        for (int i = 0; i < padding; i++) {
            msg = msg + ch;
        }
        String ivParameter = "0102030405060708";
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            byte[] raw = key.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8));
            String base64 = new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码。
            base64 = base64.replaceAll("\r", "");
            base64 = base64.replaceAll("\n", "");
            return base64;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static String rSAencrypt(String randomStrs, String key, String f) {
//        StringBuilder stringBuilder = new StringBuilder(randomStrs);
//        randomStrs = stringBuilder.reverse().toString();
//        byte[] text = randomStrs.getBytes(StandardCharsets.UTF_8);
//        BigInteger i1 = new BigInteger(byteToHex(text), 16);
//        int i2 = Integer.parseInt(key, 16);
//        BigInteger i3 = new BigInteger(f, 16);
//        BigInteger j = i1.pow(i2);
//        BigInteger seckey = j.mod(i3);
//        String s = seckey.toString(16);
//        return String.format(s, 256);
//    }

    /**
     * rsa加密
     */
    private static String rsaEncrypt(String text, String key, String f) {

        // 反转字符串
        text = new StringBuffer(text).reverse().toString();
        BigInteger biText = new BigInteger(strToHex(text), 16);
        BigInteger biEx = new BigInteger(key, 16);
        BigInteger biMod = new BigInteger(f, 16);
        BigInteger biRet = biText.modPow(biEx, biMod);
        return zFill(biRet.toString(16));

    }
    /**
     * 长度不够前面补充0
     */
    private static String zFill(String str) {
        StringBuilder strBuilder = new StringBuilder(str);
        while (strBuilder.length() < 256) {
            strBuilder.insert(0, "0");
        }
        str = strBuilder.toString();
        return str;
    }
    /**
     * 字符串转成16进制字符串
     */
    public static String strToHex(String s) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str.append(s4);
        }
        return str.toString();
    }


    public static Map<String, String> getParam1(String mingzi1) {
        String msg = "{ids: \"[" + mingzi1 + "]\", level: \"standard\", encodeType: \"mp3\", csrf_token: \"\"}";
        String enctext = aESencrypt(msg, key);
        String i = NumberUtil.generateRandomStrs(16);
        String encText = aESencrypt(enctext, i);
        String encSecKey = rsaEncrypt(i, e, f);
        Map<String, String> map = new HashMap<>();
        map.put("encText", encText);
        map.put("encSecKey", encSecKey);
        return map;
    }

    public static JSONObject connect(String mingzi, String cookie) {
        Map<String, String> param1 = getParam1(mingzi);
        Map<String, String> headers = new HashMap<>();
        Map<String, String> map = new HashMap<>();
        headers.put("Host", "music.163.com");
//        headers.put("Connection", "keep-alive");
//        headers.put("Pragma", "no-cache");
//        headers.put("Cache-Control", "no-cache");
//        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
//        headers.put("Accept", "*/*");
        headers.put("Origin", "https://music.163.com");
//        headers.put("Sec-Fetch-Site", "same-origin");
//        headers.put("Sec-Fetch-Mode", "cors");
//        headers.put("Sec-Fetch-Dest", "empty");
//        headers.put("Referer", "https://music.163.com/user/fans?id=1411492497");
//        headers.put("Accept-Encoding", "gzip, deflate, br");
//        headers.put("Accept-Language", "zh-CN,zh;q=0.9");
        headers.put("cookie", cookie);
        map.put("params", param1.get("encText"));
        map.put("encSecKey", param1.get("encSecKey"));
        try {
            HttpResponse httpResponse = HttpUtils.doPost(url, null, headers, null, map);
            return HttpUtils.getJson(httpResponse);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36");
        headers.put("Referer", "https://music.163.com/");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        return headers;
    }

    /**
     * 获取歌单内所有music的id
     *
     * @param mList
     * @return
     */
    public static Map<String, String> getListMusics(JSONArray mList, String csrf, String cookie) {
        Map<String, String> result = new HashMap<>();
        JSONArray allMusics = new JSONArray();
        String msg = "";
        for (int i = 0; i < mList.size(); i++) {
            Long listId = Long.valueOf(mList.getString(i));
            JSONObject up = new JSONObject();
            up.put("id", listId);
            up.put("n", 1000);
            up.put("csrf_token", csrf);
            Map<String, String> headers = getHeaders();
            headers.put("cookie", cookie);
            try {
                String s = HttpUtils.sendPost(detail_url + csrf, getRequestParamString(up.toJSONString()), cookie);
//                HttpResponse httpResponse = HttpUtils.doPost(detail_url + csrf, null, headers, null, getRequestParam(up.toJSONString()));
//                String s = EntityUtils.toString(httpResponse.getEntity());
                JSONObject json = JSON.parseObject(s);
                if (json == null) {
                    continue;
                }
                if (json.getInteger("code") == 200) {
                    JSONArray trackIds = json.getJSONObject("playlist").getJSONArray("trackIds");
                    for (int i1 = 0; i1 < trackIds.size(); i1++) {
                        JSONObject track = trackIds.getJSONObject(i1);
                        Long id = track.getLong("id");
                        allMusics.add(id);
                    }
                }
//                msg = msg + "\n获取歌单" + listId + "成功！";
            } catch (Exception exception) {
                exception.printStackTrace();
                result.put("flag", "false");
                msg = msg + "\n获取歌单" + listId + "失败！";
                msg = msg + "\n获取歌单" + listId + "失败！";
                result.put("msg", msg);
                return result;
            }
        }
        result.put("flag", "true");
        result.put("msg", "获取歌单音乐完毕，日志：" + msg);
        result.put("musiclists", allMusics.toJSONString());
        return result;
    }

    public static String getRequestParamString(String text) {
        Map<String, String> requestParam = getRequestParam(text);
        try {
            requestParam.put("params", URLEncoder.encode(requestParam.get("params"),"utf-8"));
            requestParam.put("encSecKey", URLEncoder.encode(requestParam.get("encSecKey"),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "params="+requestParam.get("params")+"&encSecKey="+requestParam.get("encSecKey");
    }

    public static String getRequestParamString(Map<String, String> requestParam) {
        try {
            requestParam.put("params", URLEncoder.encode(requestParam.get("params"),"utf-8"));
            requestParam.put("encSecKey", URLEncoder.encode(requestParam.get("encSecKey"),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "params="+requestParam.get("params")+"&encSecKey="+requestParam.get("encSecKey");
    }

    public static Map<String, String> getRequestParam(String text) {
        Map<String, String> encrypt = encrypt(text);
        Map<String, String> data = new HashMap<>();
        String encText = encrypt.get("encText");
        String encSecKey = encrypt.get("encSecKey");
        data.put("params", encText);
        data.put("encSecKey", encSecKey);
        return data;
    }

    public static Map<String, String> encrypt(String text) {
        String enctext = aESencrypt(text, key);
        String i = NumberUtil.generateRandomStrs(16);
        String encText = aESencrypt(enctext, i);
        String encSecKey = rsaEncrypt(i, e, f);
        Map<String, String> map = new HashMap<>();
        map.put("encText", encText);
        map.put("encSecKey", encSecKey);
        return map;
    }

}
