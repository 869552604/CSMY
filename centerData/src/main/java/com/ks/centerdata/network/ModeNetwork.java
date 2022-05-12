package com.ks.centerdata.network;


import android.content.Context;

import io.reactivex.Observer;

/**
 * Created by Administrator on 2022/4/26.
 * xfs
 * 中间层接口，通知实现层与业务层有新的处理任务
 */
public interface ModeNetwork {

    void login(Context context, String app_id, String version, String channel_id, Observer observer);

    void getBanner(Context context, String home_channel_id, String app_id, String version, String channel_id, String band, String platform_id, String time, Observer observer);

    /**
     * 拿到分类模块 各个榜单的书籍列表
     *
     * @param context
     * @param type        recommend：推荐榜  reading：阅读榜  popularity：人气榜  collect：收藏榜  score：评分榜  over：完结榜  new：新书榜  search：搜索榜  comment：热评榜
     * @param app_id      1
     * @param version     1.0.0
     * @param channel_id  huawei
     * @param band        huawei
     * @param platform_id 1
     * @param time        1600000000
     * @param gender      1   性别：1男，2女
     * @param scope       2   榜单范围：1日榜，2周榜，3月榜，4总榜（90天）
     * @param page        1
     * @param pages       10
     * @param category    科幻空间
     * @param state       完本  状态：连载，完本
     * @param today       1   是否只获取当天有更新章节的书籍：0否，1是
     * @param words       1  	字数： 1  50w以下，2  50w-100w，3  100w以上
     */
    void getRankings(Context context, String type, String app_id, String version, String channel_id, String band, String platform_id, String time, String gender, String scope, String page, String pages
            , String category, String state, String today, String words, Observer observer);

    //获取其他分类的总标题
    void getCategorys(Context context, String app_id, String version, String channel_id, String band, String platform_id, String time, String gender, Observer observer);

    //获取书架推荐书籍列表
    void getBookRecommendList(Context context, String tag, String app_id, String version, String channel_id, String band, String platform_id, String time, Observer observer);

    //获取书架列表
    void getRackBookSelfBean(Context context, String token, String user_id, String app_id, String version, String channel_id, String band, String platform_id, String time, String last_sync_time
            , String sort, String sort_by, String page, String pages, Observer observer);

    //手机号登陆
    void loginByPhone(Context context, String sign, String mobile, String tourist, String tourist_id, String code, String app_id, String version, String channel_id, String platform_id, String band
            , String os, String imei, String oaid, String uuid, String time, String phone_model, Observer observer);

    //注册
    void register(Context context, String sign, String mobile, String tourist_id, String code, String app_id, String version, String channel_id, String platform_id, String band
            , String time, String uuid, String phone_model, String password, Observer observer);

    //修改用户信息
    void upDataUser(Context context,String user_id,
                    String nickname,
                    String avatar,
                    String gender,
                    String birthday,
                    String intro,
                    String personalise_ad,
                    String personalise_recommend,
                    String local_avatar,
                    Observer observer);
}
