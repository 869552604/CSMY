package com.zywz.csmy;


import android.graphics.Color;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.githang.statusbar.StatusBarCompat;
import com.zywz.csmy.base.BaseFragment;
import com.zywz.csmy.databinding.FragmentTableGroupBinding;
import com.zywz.csmy.eventBusBean.MessageWrap;
import com.zywz.csmy.ui.group.fragment.GroupGrilFragment;
import com.zywz.csmy.ui.group.fragment.GroupManFragment;
import com.zywz.csmy.views.TabEntity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;


/**
 * 分类
 * Created by Administrator on 2022/4/25.
 * xfs
 */
public class TableGroupFragment extends BaseFragment {
    FragmentTableGroupBinding binding;
    private String[] mTitles;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    @Override
    public void init(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_table_group,container,false);

        initData();

    }

    @Override
    public View getLayoutResId() {
        return binding.getRoot();
    }

    public void initData() {
        mTitles = new String[]{getString(R.string.table6), getString(R.string.table7)};
        mFragments.add(new GroupManFragment());
        mFragments.add(new GroupGrilFragment());
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i]));
        }
        binding.tlGroup.setTabData(mTabEntities, getActivity(), R.id.frame_group, mFragments);
        final float selectedSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 18, getResources().getDisplayMetrics());
        final float unSelectedSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 15, getResources().getDisplayMetrics());

        binding.tlGroup.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                binding.tlGroup.setCurrentTab(position);
                //不知道为什么for循环无法改变字体大小
                if(position==0){
                    binding.tlGroup.getTitleView(0).setTextSize(TypedValue.COMPLEX_UNIT_SP,selectedSize);
                    binding.tlGroup.getTitleView(0).getPaint().setFakeBoldText(true);
                    binding.tlGroup.getTitleView(1).setTextSize(TypedValue.COMPLEX_UNIT_SP,unSelectedSize);
                    binding.tlGroup.getTitleView(1).getPaint().setFakeBoldText(false);

                }else if(position==1){
                    binding.tlGroup.getTitleView(0).setTextSize(TypedValue.COMPLEX_UNIT_SP,unSelectedSize);
                    binding.tlGroup.getTitleView(0).getPaint().setFakeBoldText(false);
                    binding.tlGroup.getTitleView(1).setTextSize(TypedValue.COMPLEX_UNIT_SP,selectedSize);
                    binding.tlGroup.getTitleView(1).getPaint().setFakeBoldText(true);
                }

            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        binding.tlGroup.setCurrentTab(0);
        binding.tlGroup.getTitleView(0).setTextSize(TypedValue.COMPLEX_UNIT_SP,selectedSize);
        binding.tlGroup.getTitleView(0).getPaint().setFakeBoldText(true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetEventBus(MessageWrap wrap) {
        //设置状态栏颜色
        if(wrap.message.equals("2")) {
            StatusBarCompat.setStatusBarColor((MainActivity) getContext(), Color.parseColor(getString(R.string.status_bar_compat_group)));

        }
    }
}
