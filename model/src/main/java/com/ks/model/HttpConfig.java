package com.ks.model;


/**
 * Created by Administrator on 2022/4/24.
 * xfs
 */

public class HttpConfig {
    public static int HTTP_TIME=3;  //设置延时（秒）服务器没回应则触发 onFailure
    public static String BASE_URL= BuildConfig.domain;

}
