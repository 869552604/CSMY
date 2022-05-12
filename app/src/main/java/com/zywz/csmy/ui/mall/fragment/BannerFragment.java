package com.zywz.csmy.ui.mall.fragment;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ks.bean.bannerBean;
import com.ks.centerdata.network.CenterAPI;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zywz.csmy.R;
import com.zywz.csmy.base.BaseFragment;
import com.zywz.csmy.base.BaseObserver;
import com.zywz.csmy.base.RecordUtils;
import com.zywz.csmy.databinding.FragmentMallBannerBinding;
import com.zywz.csmy.utils.MyImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 轮播Banner
 * Created by Administrator on 2022/5/5.
 * xfs
 */
public class BannerFragment extends BaseFragment {

    private FragmentMallBannerBinding binding;

    @Override
    public void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_mall_banner,container,false);

        initData();
    }

    @Override
    public View getLayoutResId() {
        return binding.getRoot();
    }

    private void initData() {
        final List<String> imgList = new ArrayList<>();
//        imgList.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2F1113%2F052420110515%2F200524110515-2-1200.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654312450&t=79358b37b3e9bf24f6d30baa2cdfb32d");
//        imgList.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2F1114%2F0G020114924%2F200G0114924-15-1200.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654312450&t=72e9e8bfccb04130f93b0c4d09f5991e");
//        imgList.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2F4k%2Fs%2F02%2F2109242332225H9-0-lp.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654312450&t=0ae01b9f911c7988fcda4e1447fea749");
//        imgList.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi-1.lanrentuku.com%2F2020%2F7%2F10%2Fb87c8e05-344a-48d1-869f-ef6929fc8b17.jpg&refer=http%3A%2F%2Fi-1.lanrentuku.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654312450&t=fde3bf9df1f1c0cf83a8235caac44d43");
//        imgList.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2F1114%2F113020142315%2F201130142315-1-1200.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654312450&t=722170819cedf337820b74715782ad1f");
//
//
//        binding.banner
//                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
//                .setImageLoader(new MyImageLoader())
//                .setBannerAnimation(Transformer.ZoomOutSlide)
//                .setDelayTime(3000)
//                .isAutoPlay(true)
//                .setIndicatorGravity(BannerConfig.CENTER)
//                .setImages(imgList)
//                .start();

        binding.banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getContext(),position+"",Toast.LENGTH_LONG).show();
            }
        });

        CenterAPI.getInstance().getBanner(getContext(), "1", "1", "1.0.0", "huawei", "huawei", "1", "1600000000", new BaseObserver<bannerBean>() {
            @Override
            protected void onSuccees(bannerBean bannerBean) throws Exception {
                Toast.makeText(getContext(),bannerBean.toString(),Toast.LENGTH_LONG).show();
                for (int i = 0; i < bannerBean.data.size(); i++) {
                    imgList.add(RecordUtils.setImageUrl(bannerBean.data.get(i).cover));

                }
                binding.banner
                        .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                        .setImageLoader(new MyImageLoader())
                        .setBannerAnimation(Transformer.ZoomOutSlide)
                        .setDelayTime(3000)
                        .isAutoPlay(true)
                        .setIndicatorGravity(BannerConfig.CENTER)
                        .setImages(imgList)
                        .start();
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
