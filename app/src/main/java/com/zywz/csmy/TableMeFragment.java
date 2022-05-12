package com.zywz.csmy;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.githang.statusbar.StatusBarCompat;
import com.zywz.csmy.adapter.TableMeAdapter;
import com.zywz.csmy.base.BaseFragment;
import com.zywz.csmy.bean.AdapterTableMeBean;
import com.zywz.csmy.databinding.FragmentTableMeBinding;
import com.zywz.csmy.eventBusBean.MessageWrap;
import com.zywz.csmy.ui.me.activity.GenderActivity;
import com.zywz.csmy.ui.me.activity.LoginActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * 我的
 * Created by Administrator on 2022/4/25.
 * xfs
 */
public class TableMeFragment extends BaseFragment implements View.OnClickListener {
    public FragmentTableMeBinding binding;
    private List<AdapterTableMeBean> datas =new ArrayList<>();
    @Override
    public void init(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_table_me,container,false);

        //设置背景颜色
        binding.nestedOrderdetail.setBackgroundResource(R.drawable.table_me_bg);
        int[] imgs = new int[] {R.mipmap.table_me_img1, R.mipmap.table_me_img2,R.mipmap.table_me_img3, R.mipmap.table_me_img4,R.mipmap.table_me_img5,R.mipmap.table_me_img6,R.mipmap.table_me_img7,R.mipmap.table_me_img8,R.mipmap.table_me_img9};
        final String[]contents=new String[]{getString(R.string.table8),getString(R.string.table9),getString(R.string.table10),getString(R.string.table11),getString(R.string.table12),getString(R.string.table13),getString(R.string.table14),getString(R.string.table15),getString(R.string.table16)};
        AdapterTableMeBean bean;
        for (int i = 0; i < contents.length; i++) {
            bean=new AdapterTableMeBean();
            bean.setUrl(imgs[i]);
            bean.setContent(contents[i]);
            datas.add(bean);
        }
        GridLayoutManager manager =new GridLayoutManager(getContext(),3);
        TableMeAdapter adapter =new TableMeAdapter(R.layout.adapter_table_me,datas);
        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getContext(),((AdapterTableMeBean)adapter.getItem(position)).getContent(),Toast.LENGTH_SHORT).show();
            }
        });

        binding.constraint1.setOnClickListener(this);
        binding.imgSetting.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.constraint1:
                Intent intent =new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.img_setting:
                Intent intent1 =new Intent(getContext(), GenderActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public View getLayoutResId() {
        return binding.getRoot();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetEventBus(MessageWrap wrap) {
        //设置状态栏颜色
        if(wrap.message.equals("3")) {
            StatusBarCompat.setStatusBarColor((MainActivity) getContext(), Color.parseColor(getString(R.string.status_bar_compat_me)));

        }
    }
}
