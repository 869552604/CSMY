package com.zywz.csmy.base;

import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.util.Log;

import com.zywz.csmy.BuildConfig;
import com.zywz.csmy.ui.me.activity.LoginActivity;
import com.zywz.csmy.utils.AllUtils;

import java.util.HashMap;
import java.util.Map;

public class RecordUtils {
    public static String app_id ="2";  //1
//    public static String version ="1.0.0";
    public static String channel_id="huawei";  //huawei
    public static String band="huawei";
    public static String platform_id="2";
//    public static String time=String.valueOf(System.currentTimeMillis()/1000);
    public static String pages="10";
    public static String os="homeny1.1";
    public static String imei="ads1y23f1g12h31h3gf12g3h1f3h12g";
    public static String oaid="aih213h1kjhj1h23g1jh3g1j3h1g12uy";
    public static String uuid="4cb8681f-84fb-49bf-be3d-d56d613092e1";
    public static String phone_model="iphone12";
    public static String tourist="书友_2"+(int)((Math.random()*9+1)*100000)+getString(3);


    public static String Bearer ="Bearer ";//用来拼接 token
    public static String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJodHRwOi8vOC4xMzQuMTI1LjE0OToxOTUwMS92MS91c2Vycy9sb2dpbiIsImlhdCI6MTY1MjI1ODEyMSwiZXhwIjoxNjU0ODUwMTIxLCJuYmYiOjE2NTIyNTgxMjEsImp0aSI6ImMxZE9xVTJpN1lOcXJPZE0iLCJzdWIiOiIxMDczIiwicHJ2IjoiZjZiNzE1NDlkYjhjMmM0MmI3NTgyN2FhNDRmMDJiN2VlNTI5ZDI0ZCJ9.ypIgtNehwaSZIyVeJzeWZL7IQvnIsiL3ugwnSNWh5qUpc1lqnViDgkKA9mddhwyyzw2Ktm0Xi016Zl4vlZYYHw";
    public static String userId="1072";




    public static String last_sync_time="1600000000";//最后同步线上书架列表到本地时间
    public static String sort="1"; //排序：1根据最后阅读时间，2根据书籍更新时间
    public static String sort_by="1"; //排序规则：1升序，2倒序

    public static String recommend="R_bookself_recommend"; //书架推荐位
    public static String recommend_search="R_search_recommend"; //书城推荐位

    public static String setImageUrl(String url){
        return BuildConfig.domain+"images/{"+url+"}";
    }

    public static String setToken(String token){
        return Bearer+token;
    }

    public static String setToken(){
        return Bearer+token;
    }

    // 你想生成几个字符的，就把9改成几，如果改成１,那就生成一个随机字母．
    public static String getString(int number){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < number; i++) {
            str.append((char) (Math.random() * 26 + 'a'));
        }
        return str.toString();
    }

    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String getTime(){
        return String.valueOf(System.currentTimeMillis()/1000);
    }

    public static String getVersion(Context context){
        return AllUtils.getInstance().getVerName(context);
    }

    //Post请求必须传的公共参数
    public static Map<String,Object> getPublicMap(Context context){
        Map<String, Object> map = new HashMap<>();
        map.put("app_id", RecordUtils.app_id);
        map.put("version", AllUtils.getInstance().getVerName(context));
        map.put("channel_id", RecordUtils.channel_id);
        map.put("band", RecordUtils.band);
        map.put("platform_id", RecordUtils.platform_id);
        map.put("time", RecordUtils.getTime());
        return map;
    }


}
