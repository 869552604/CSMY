package com.zywz.csmy.ui.mall.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zywz.csmy.R;
import com.zywz.csmy.base.BaseFragment;
import com.zywz.csmy.databinding.FragmentMallSelectBinding;

/**
 * 书城 精选
 * Created by Administrator on 2022/5/5.
 * xfs
 */
public class MallSelectFragment extends BaseFragment {

    private FragmentMallSelectBinding binding;

    @Override
    public void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_mall_select,container,false);

    }

    @Override
    public View getLayoutResId() {
        return binding.getRoot();
    }
}
