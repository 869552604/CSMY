package com.zywz.csmy.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.ks.bean.LoginPhoneBean;

public class SharedPreferencesUtils {
    private final String KEY="csmy_info" ;
    private final String LOGINPHONEBEAN="loginPhoneBean" ;

    private static SharedPreferencesUtils instance = null;

    public static SharedPreferencesUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (SharedPreferencesUtils.class) {
                if (instance == null) {
                    instance = new SharedPreferencesUtils(context);
                }
            }
        }
        return instance;
    }

    private SharedPreferences sp ;
    private SharedPreferencesUtils(Context context){
        sp=context.getSharedPreferences(KEY,Context.MODE_PRIVATE);
    }

    //保存登陆的对象bean
    public void setLoginPhoneBean(LoginPhoneBean bean){
        sp.edit().putString(LOGINPHONEBEAN,new Gson().toJson(bean)).apply();
    }

    public LoginPhoneBean getLoginPhoneBean(){
        String str=sp.getString(LOGINPHONEBEAN,"");
        return new Gson().fromJson(str,LoginPhoneBean.class);
    }
}
