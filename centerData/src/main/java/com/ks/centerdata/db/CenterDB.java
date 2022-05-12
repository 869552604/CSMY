package com.ks.centerdata.db;


import android.app.Application;

import com.ks.centerdata.network.ModeNetwork;

public class CenterDB implements ModeDB {

    static private ModeDB proxy = null;
    static private CenterDB mInstance = null;
    static public CenterDB getInstance() {
        if (mInstance == null) {
            mInstance = new CenterDB();
            try {
                Class cls = Class.forName("com.ks.proxysql.ProxyDB");
                proxy = (ModeDB) cls.newInstance();
            } catch (ClassNotFoundException e) {
            } catch (IllegalAccessException e) {
            } catch (InstantiationException e) {
            }
        }
        return mInstance;
    }

    private boolean checkProxy() {
        if (proxy == null) {
            return false;
        }
        return true;
    }

    @Override
    public void initSQL(Application app) {
        if (!checkProxy()) {
            return;
        }
        proxy.initSQL(app);
    }

    @Override
    public void setValueForKey(String key, String value) {
        if (!checkProxy()) {
            return;
        }
        proxy.setValueForKey(key,value);
    }

    @Override
    public String getValueForKey(String key,String defaulValue) {
        if (!checkProxy()) {
            return defaulValue;
        }
        return proxy.getValueForKey(key,defaulValue);
    }

    @Override
    public void setBean(Object bean) {
        if (!checkProxy()) {
            return;
        }
        proxy.setBean(bean);
    }

    @Override
    public <T extends Object> T getBean(Class cls) {
        if (!checkProxy()) {
            return null;
        }
        return proxy.getBean(cls);
    }

    @Override
    public void deleteBean(Class cls) {
        if (!checkProxy()) {
            return;
        }
        proxy.deleteBean(cls);
    }

    @Override
    public void deleteAllBean() {
        if (!checkProxy()) {
            return;
        }
        proxy.deleteAllBean();
    }

    @Override
    public void setJsonString(String key, String json) {
        if (!checkProxy()) {
            return;
        }
        proxy.setJsonString(key,json);
    }

    @Override
    public String getJsonString(String key) {
        if (!checkProxy()) {
            return null;
        }
        return proxy.getJsonString(key);
    }

    @Override
    public void deleteJsonString(String key) {
        if (!checkProxy()) {
            return;
        }
        proxy.deleteJsonString(key);
    }

    @Override
    public void deleteAllJsonString() {
        if (!checkProxy()) {
            return;
        }
        proxy.deleteAllJsonString();
    }


}
