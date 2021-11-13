package com.xiaomi.model.receive;

import lombok.Data;

@Data
public class User {

    /**
     *用户唯一id，默认会对用户id进行加密，所以这个id不是真实的小米id，但是可以作为唯一标识
     */
    private String user_id;
    /**
     *如果使用了OAuth进行账号绑定，这里存放用户绑定后获取的token
     */
    private String access_token;
    /**
     *使用是否在设备上登录了小米账号
     */
    private Boolean is_user_login;
    /**
     *声纹识别出来的性别, 目前支持三种类型: male, female, child
     */
    private String gender;

}
