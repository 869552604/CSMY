package com.ks.centerdata.network;

import android.content.Context;
import android.widget.Toast;

import io.reactivex.Observer;


/**
 * Created by Administrator on 2022/4/26.
 * xfs
 * 控制整个应用网络的请求中心
 */
public class CenterAPI implements ModeNetwork{

    static private ModeNetwork proxy = null;
    static private CenterAPI mInstance = null;
    static public CenterAPI getInstance() {
        if (mInstance == null) {
            mInstance = new CenterAPI();
            try {
                Class cls = Class.forName("com.ks.model.RetrofitFactory");
                proxy = (ModeNetwork) cls.newInstance();
            } catch (ClassNotFoundException e) {
            } catch (IllegalAccessException e) {
            } catch (InstantiationException e) {
            }
        }
        return mInstance;
    }

    private boolean checkProxy(Context context) {
        if (proxy == null) {
            Toast.makeText(context.getApplicationContext(),"centerData..CenterAPI ->网络请求初始化失败",Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;

    }

    public void login(Context context, String app_id, String version, String channel_id, Observer observer) {
        if(!checkProxy(context)){
            return ;
        }
        proxy.login(context,app_id,version,channel_id,observer);
    }

    @Override
    public void getBanner(Context context, String home_channel_id, String app_id, String version, String channel_id, String band, String platform_id, String time, Observer observer) {
        if(!checkProxy(context)){
            return;
        }
        proxy.getBanner(context,home_channel_id,app_id,version,channel_id,band,platform_id,time,observer);
    }

    @Override
    public void getRankings(Context context, String type, String app_id, String version, String channel_id, String band, String platform_id, String time, String gender, String scope, String page, String pages, String category, String state, String today, String words, Observer observer) {
        if(!checkProxy(context)){
            return;
        }
        proxy.getRankings(context,type,app_id,version,channel_id,band,platform_id,time,gender,scope,page,pages,category,state,today,words,observer);
    }

    @Override
    public void getCategorys(Context context, String app_id, String version, String channel_id, String band, String platform_id, String time, String gender, Observer observer) {
        if(!checkProxy(context)){
            return;
        }
        proxy.getCategorys(context,app_id,version,channel_id,band,platform_id,time,gender,observer);
    }

    @Override
    public void getBookRecommendList(Context context, String tag, String app_id, String version, String channel_id, String band, String platform_id, String time, Observer observer) {
        if(!checkProxy(context)){
            return;
        }
        proxy.getBookRecommendList(context,tag,app_id,version,channel_id,band,platform_id,time,observer);
    }

    @Override
    public void getRackBookSelfBean(Context context,String token, String user_id, String app_id, String version, String channel_id, String band, String platform_id, String time, String last_sync_time, String sort, String sort_by, String page, String pages, Observer observer) {
        if(!checkProxy(context)){
            return;
        }
        proxy.getRackBookSelfBean(context,token,user_id, app_id, version, channel_id, band, platform_id, time, last_sync_time, sort, sort_by, page, pages,observer);
    }

    @Override
    public void loginByPhone(Context context,String sign, String mobile, String tourist, String tourist_id, String code, String app_id, String version, String channel_id, String platform_id, String band, String os, String imei, String oaid, String uuid, String time, String phone_model, Observer observer) {
        if(!checkProxy(context)){
            return;
        }
        proxy.loginByPhone(context,sign,mobile,tourist, tourist_id, code, app_id, version, channel_id, platform_id, band, os, imei, oaid, uuid, time, phone_model, observer);
    }

    @Override
    public void register(Context context, String sign, String mobile, String tourist_id, String code, String app_id, String version, String channel_id, String platform_id, String band, String time, String uuid, String phone_model, String password, Observer observer) {
        if(!checkProxy(context)){
            return;
        }
        proxy.register(context, sign,  mobile,  tourist_id,  code,  app_id,  version,  channel_id,  platform_id,  band,  time,  uuid,  phone_model,  password,  observer);
    }

    @Override
    public void upDataUser(Context context, String user_id, String nickname, String avatar, String gender, String birthday, String intro, String personalise_ad, String personalise_recommend, String local_avatar, Observer observer) {
        if(!checkProxy(context)){
            return;
        }
        proxy.upDataUser(context,user_id, nickname,  avatar,  gender,  birthday,  intro,  personalise_ad,  personalise_recommend,  local_avatar,  observer);
    }


}
