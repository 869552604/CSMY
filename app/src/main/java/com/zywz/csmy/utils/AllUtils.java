package com.zywz.csmy.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class AllUtils {

    private AllUtils() {
    }

    private static AllUtils instance = null;

    public static AllUtils getInstance() {
        if (instance == null) {
            synchronized (AllUtils.class) {
                if (instance == null) {
                    instance = new AllUtils();
                }
            }
        }
        return instance;
    }

    public boolean isMobileNo(String mobileNo) {

        Pattern mobilePattern = Pattern.compile("^((13[0-9])|(14[5,7])|(15[0-3,5-8])|(17[0,3,5-8])|(18[0-9])|(147))\\d{8}$");

        return mobilePattern.matcher(mobileNo).matches();
    }

    //获取sign的值
    public String getSignature(Map<String, Object> map) {
        List<Map.Entry<String, Object>> list = new ArrayList<Map.Entry<String, Object>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Object>>() {
            //升序排序
            public int compare(Map.Entry<String, Object> o1,
                               Map.Entry<String, Object> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        String unencrypted = "";
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                unencrypted = unencrypted + "&";
            }
            Map.Entry<String, Object> mapping = list.get(i);
            unencrypted = unencrypted + mapping.getKey() + "=" + getURLEncoderString(mapping.getValue().toString());
        }
        unencrypted = "sf2Fug24$@G2H5P^" + unencrypted + "sf2Fug24$@G2H5P^";
        Log.i("TAG","====="+unencrypted);
        String encryption = ToolMD5.getMD5String(unencrypted);
        return encryption;
    }

    public String getURLEncoderString(String str) {
        String result = str;
        if (null == str) {
            return "";
        }
        try {
            result = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    //获取APP版本
    public String getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return String.valueOf(versionCode);
    }

    //获取版本号名称
    public String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    public String getChannelName(Context context) {

        if (context == null) {
            return null;
        }

        String channelName = null;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getOpPackageName(), PackageManager.GET_META_DATA);
                channelName = String.valueOf(applicationInfo.metaData.get("UMENG_CHANNEL"));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

        }

        return channelName;

    }


}
