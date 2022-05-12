package com.zywz.csmy.ui.mall.fragment;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zywz.csmy.R;
import com.zywz.csmy.adapter.TableMeAdapter;
import com.zywz.csmy.base.BaseFragment;
import com.zywz.csmy.bean.AdapterTableMeBean;
import com.zywz.csmy.databinding.FragmentTitleBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 排行 书单 完结 新书选择栏
 * Created by Administrator on 2022/5/5.
 * xfs
 */
public class TitleFragment extends BaseFragment {
    private List<AdapterTableMeBean> datas =new ArrayList<>();
    FragmentTitleBinding binding;
    @Override
    public void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_title,container,false);

        int[] imgs = new int[] {R.mipmap.icon_ranking, R.mipmap.icon_book,R.mipmap.icon_end, R.mipmap.icon_new};
        final String[]contents=new String[]{getString(R.string.table17),getString(R.string.table18),getString(R.string.table19),getString(R.string.table20)};
        AdapterTableMeBean bean;
        for (int i = 0; i < contents.length; i++) {
            bean=new AdapterTableMeBean();
            bean.setUrl(imgs[i]);
            bean.setContent(contents[i]);
            datas.add(bean);
        }
        GridLayoutManager manager =new GridLayoutManager(getContext(),4);
        TableMeAdapter adapter =new TableMeAdapter(R.layout.adapter_title,datas);
        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getContext(),((AdapterTableMeBean)adapter.getItem(position)).getContent(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View getLayoutResId() {
        return binding.getRoot();
    }

}
