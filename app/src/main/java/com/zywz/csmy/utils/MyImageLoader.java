package com.zywz.csmy.utils;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;
import com.zywz.csmy.R;
import com.zywz.csmy.views.RoundImageView;

public class MyImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        ThumbUtils.getInstance().setImageRadius(context,imageView,path.toString(),20, R.mipmap.cache_img2);
    }

    @Override
    public ImageView createImageView(Context context) {
        RoundImageView img = new RoundImageView(context);
        return img;

    }

}
