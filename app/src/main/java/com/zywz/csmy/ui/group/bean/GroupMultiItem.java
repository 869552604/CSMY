package com.zywz.csmy.ui.group.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class GroupMultiItem implements MultiItemEntity {
    //全部类型
    public static final int ALL = 0;
    //子类
    public static final int OTHER = 1;

    //item类型
    private int fieldType;

    private int url;

    private String imgUrl;

    private String title;

    private String content;

    private String type;

    private String words;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public GroupMultiItem(int fieldType, int url, String title) {
        //将传⼊的type赋值
        this.fieldType = fieldType;
        this.url=url;
        this.title=title;
    }

    public GroupMultiItem(int fieldType,String url,String title,String content,String type,String words) {
        //将传⼊的type赋值
        this.fieldType = fieldType;
        this.imgUrl=url;
        this.title=title;
        this.content=content;
        this.type=type;
        this.words=words;
    }

    @Override
    public int getItemType() {
        //返回传⼊的itemType
        return fieldType;
    }
}

