package com.xiaomi.util;

import com.oldwu.util.ReplyUtils;
import com.oldwu.word.Words;
import com.xiaomi.model.enumm.DirectiveType;
import com.xiaomi.model.enumm.TTSItemType;
import com.xiaomi.model.send.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class XiaomiResult {
    public static SendInfo answerQuestion(String msg) {
        SendInfo sendInfo = new SendInfo();
        Response response = new Response();
        response.setTo_speak(buildToSpeak(msg));
        response.setOpen_mic(true);
        sendInfo.setResponse(response);
        return sendInfo;
    }


    public static SendInfo cantUnderstand() {
        SendInfo sendInfo = new SendInfo();
        Response response = new Response();
        response.setNot_understand(true);
        response.setTo_speak(buildToSpeak(ReplyUtils.randomReply(Words.XIAOAI_CANT_UNDERSTAND)));
        sendInfo.setResponse(response);
        return sendInfo;
    }

    public static SendInfo sendMsg(String msg) {
        SendInfo sendInfo = new SendInfo();
        Response response = new Response();
        response.setTo_speak(buildToSpeak(msg));
        sendInfo.setResponse(response);
        return sendInfo;
    }

    public static SendInfo sendVoice(String url,String msg) {
        SendInfo sendInfo = new SendInfo();
        Response response = new Response();
        if (StringUtils.isEmpty(url) && StringUtils.isEmpty(msg)){
            return sendMsg("系统错误，没有接收到任何信息字段！");
        }
        AudioItem audioItem = null;
        TTSItem ttsItem = null;
        List<Directive> directives = new ArrayList<>();
        if (!StringUtils.isEmpty(msg)){
            ttsItem = buildTTSItem(msg);
            Directive directive = buildDirective(DirectiveType.TTS,null,ttsItem);
            directives.add(directive);
        }
        if (!StringUtils.isEmpty(url)){
            audioItem = buildAudioItem(url);
            Directive directive = buildDirective(DirectiveType.AUDIO,audioItem,null);
            directives.add(directive);
        }
        response.setDirectives(directives);
        sendInfo.setResponse(response);
        return sendInfo;
    }

    public static SendInfo sendVoices(List<String> urls,String msg) {
        SendInfo sendInfo = new SendInfo();
        Response response = new Response();
        if (urls.size() == 0 && StringUtils.isEmpty(msg)){
            return sendMsg("系统错误，没有接收到任何信息字段！");
        }
        AudioItem audioItem = null;
        TTSItem ttsItem = null;
        List<Directive> directives = new ArrayList<>();
        if (!StringUtils.isEmpty(msg)){
            ttsItem = buildTTSItem(msg);
            Directive directive = buildDirective(DirectiveType.TTS,null,ttsItem);
            directives.add(directive);
        }
        for (String url : urls) {
            if (!StringUtils.isEmpty(url)){
                audioItem = buildAudioItem(url);
                Directive directive = buildDirective(DirectiveType.AUDIO,audioItem,null);
                directives.add(directive);
            }
        }
        response.setDirectives(directives);
        sendInfo.setResponse(response);
        return sendInfo;
    }

    public static AudioItem buildAudioItem(String url){
        AudioItem audioItem = new AudioItem();
        AudioStream audioStream = new AudioStream();
        audioStream.setUrl(url);
        audioItem.setStream(audioStream);
        return audioItem;
    }

    public static TTSItem buildTTSItem(String msg){
        TTSItem ttsItem = new TTSItem();
        ttsItem.setType(TTSItemType.TEXT.type);
        ttsItem.setText(msg);
        return ttsItem;
    }

    public static Directive buildDirective(DirectiveType directiveType, AudioItem audioItem, TTSItem ttsItem) {
        Directive directive = new Directive();
        directive.setType(directiveType.type);
        if (audioItem != null) {
            directive.setAudio_item(audioItem);
        }
        if (ttsItem != null) {
            directive.setTts_item(ttsItem);
        }
        return directive;
    }

    public static ToSpeak buildToSpeak(String msg) {
        ToSpeak toSpeak = new ToSpeak();
        toSpeak.setText(msg);
        return toSpeak;
    }

    public static Response buildToResponse(String msg) {
        ToSpeak toSpeak = new ToSpeak();
        Response response = new Response();
        toSpeak.setText(msg);
        response.setTo_speak(toSpeak);
        return response;
    }

}
