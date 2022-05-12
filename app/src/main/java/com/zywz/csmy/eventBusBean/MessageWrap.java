package com.zywz.csmy.eventBusBean;

/**
 * EventBus类，用来通知
 * Created by Administrator on 2022/5/5.
 * xfs
 *
 * 目前"0"，"1"，"2"，"3" 分别用来通知书城、书架、分类、我的这4个模块更新状态栏颜色
 */
public class MessageWrap {
    public final String message;

    public static MessageWrap getInstance(String message) {
        return new MessageWrap(message);
    }

    private MessageWrap(String message) {
        this.message = message;
    }
}
