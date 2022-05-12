package com.zywz.csmy.ui.group.fragment;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zywz.csmy.R;
import com.zywz.csmy.base.BaseFragment;
import com.zywz.csmy.databinding.FragmentGroupGrilBinding;

/**
 * 分类 女生
 * Created by Administrator on 2022/5/6.
 * xfs
 */
public class GroupGrilFragment extends BaseFragment {

    private FragmentGroupGrilBinding binding;
    @Override
    public void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_group_gril,container,false);
    }

    @Override
    public View getLayoutResId() {
        return binding.getRoot();
    }

}
