package com.zywz.csmy.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.zywz.csmy.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.io.File;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * Created by Administrator on 2022/4/24.
 * xfs
 * 描述:加载图片
 */
public class ThumbUtils {

    private static ThumbUtils instance=null;
    private ThumbUtils(){ }
    public static ThumbUtils getInstance(){
        if(instance==null){
            synchronized(ThumbUtils.class){
                instance=new ThumbUtils();
            }
        }
        return instance;
    }

    public void setImageRadius(final Context context, final ImageView imageView, final String imgUrl,final int radius,int errorImg) {
        RoundedCorners roundedCorners = new RoundedCorners(radius);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(errorImg)//图片加载出来前，显示的图片
                .fallback(errorImg)//url为空的时候,显示的图片
                .error(errorImg);//图片加载失败后，显示的图片

        if (!TextUtils.isEmpty(imgUrl)) {
            File file = GlideImageUtils.getInstance().getCacheFile(context,imgUrl);
            if (null != file) {//缓存文件不为空 直接加载file对应的图片
                Glide.with(context.getApplicationContext())
                        .load(file)
                        .apply(options)
                        .into(imageView);
            } else {//缓存文件为空 加载url对应的图片
                Glide.with(context.getApplicationContext())
                        .load(imgUrl)
                        .apply(options)
//                        .listener(new RequestListener<Drawable>() { //设置拿3次图片 暂时去掉
//                            @Override
//                            public boolean onLoadFailed( GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                                imageView.post(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        imageView.setTag(imageView.getTag() == null ? 1 : (int) imageView.getTag() + 1);
//                                        if ((int) imageView.getTag() < 3) {
//                                            Log.e(TAG, "重试次数:  " + imageView.getTag());
//                                            setImageRadius(context, imageView, imgUrl,radius);
//                                        }
//                                    }
//                                });
//                                return false;
//                            }
//
//                            @Override
//                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
//                                return false;
//                            }
//
//
//                        })
                        .into(imageView);
            }
        }
    }

    public void setImage(final Context context, final ImageView imageView, final String imgUrl,int errorImg){
        setImageRadius(context,imageView,imgUrl,1,errorImg);
    }
}
