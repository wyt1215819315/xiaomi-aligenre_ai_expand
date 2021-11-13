package com.oldwu.genshin.service;

import com.oldwu.genshin.enitty.GenshinAccount;

public interface GenshinService {

    GenshinAccount queryBandGenshinAccount(String id, String type);

    String getDailyNote(String id, String type, String queryType);

}
