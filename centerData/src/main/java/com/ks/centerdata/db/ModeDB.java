package com.ks.centerdata.db;


import android.app.Application;

public interface ModeDB {

    //使用前先初始化数据库
    void initSQL(Application app);

    //保存基本数据
    //保存基本数据，value是null时尝试删除对应key保存的值
    void setValueForKey(String key, String value);
    //获取基本数据，当查询数据没有对应key的值时，返回defaulValue，defaulValue可以为空（null）
    String getValueForKey(String key,String defaulValue);

    //保存bean，每个bean类只保存一份，新的会替换旧的
    //保存或替换一个bean
    void setBean(Object bean);
    //获取一个bean
    <T extends Object> T getBean(Class cls);
    //删除一个bean
    void deleteBean(Class cls);
    //删除所有bean
    void deleteAllBean();

    //根据key保存或替换json
    void setJsonString(String key,String json);
    //根据key获取json
    String getJsonString(String key);
    //删除json
    void deleteJsonString(String key);
    //删除所有json
    void deleteAllJsonString();





}
