## xiaomi & aligenre ai expand
这是一个小爱同学以及天猫精灵的技能拓展

### 关于此拓展
主要控制器在`com.oldwu.controller`中

天猫精灵专有包为`com.aligenie`

小爱同学专有包为`com.xiaomi`

### 配置文件
和springboot+mybatis的配置文件格式一致，自行加上即可
```yaml
server:
    port: 8061
spring:
    #数据库连接配置
    datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://         ?characterEncoding=utf-8&useSSL=false
        username: 
        password: 
#mybatis的相关配置
mybatis:
    #mapper配置文件
    mapper-locations: classpath:mapper/*.xml,classpath:mapper/**/*.xml
    type-aliases-package: com.oldwu.entity
    #开启驼峰命名
    configuration:
        map-underscore-to-camel-case: true
#        log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```


### xiaomi ai
小爱同学门槛比较高，官方没有提供任何java有关依赖以及资料，只提供了实体对应字段信息，如果需要使用小米ai，需要使用域名并且使用https请求
<br>更多官方说明请查看：https://developers.xiaoai.mi.com/documents/Home?type=/api/doc/render_markdown/SkillAccess/skill/CustomSkillsMain#3https%E4%BA%A4%E4%BA%92%E5%BC%80%E5%8F%91

#### 接收实体类：ReceiveInfo


#### 发送实体类：SendInfo

#### 发送消息
##### 普通文字消息TTS
直接调用封装好的类`XiaomiResult.sendMsg(String msg)`即可<br>**此方法只能用于发送普通TTS消息，关于调用的字段请自行查看方法**

##### 语音播放
###### 播放单条音频链接
直接调用封装好的类`Xiaomi Result.sendVoice(String url,String msg)`<br>
此方法可以发送普通TTS信息以及发送url音频链接，如果仅需发送两者之间的一个，只需要另另一个参数为**null**即可

###### 播放单条音频链接
此方法常用于播放一个歌单中的歌曲，传入一组链接之后，小爱会自动下一首，按照次序播放
直接调用封装好的类`Xiaomi Result.sendVoices(List<String> urls,String msg)`<br>
此方法可以发送普通TTS信息以及发送url音频链接，如果仅需发送两者之间的一个，只需要另另一个参数为**null**即可


#### 唯一标识字段
`ReceiveInfo.Session.User.user_id`:这个ID为用户ID的加密信息，虽然不是用户id，但是可以作为每个用户的唯一标识

#### 其他事项
* 小爱必须进入应用或者开启意图唤醒之后才能使用功能，第一次开启时的回复关键字不一定为你设定的，因此需要处理好第一次唤醒的情况
* `to_speak`和`directives`两者必须有一个，否则会造成返回的数据没有任何声音，平台会判定为无效动作，断开技能的session

### aligenre ai
更多信息见官方文档：https://www.aligenie.com/doc/20255408/mxi8t9

#### 接收实体类：TaskQuery


#### 发送实体类：ResultModel<TaskResult>
核心是`TaskResult`,这边包含了所有回复了类型以及字段

使用`AligenreResult.buildResultModel(taskResult)`构建最终返回结果，一般不用动

#### 唯一标识字段

