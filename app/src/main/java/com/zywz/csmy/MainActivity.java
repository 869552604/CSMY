package com.zywz.csmy;


import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.githang.statusbar.StatusBarCompat;
import com.zywz.csmy.base.BaseActivity;
import com.zywz.csmy.base.RecordUtils;
import com.zywz.csmy.databinding.ActivityMainBinding;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.zywz.csmy.eventBusBean.MessageWrap;
import com.zywz.csmy.utils.SharedPreferencesUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2022/4/24.
 * xfs
 */
public class MainActivity extends BaseActivity {
    private long firstTime = 0;
    private ActivityMainBinding binding;

    private ArrayList<Integer> selectedIconRes = new ArrayList<>();         //tab选中图标集合
    private ArrayList<Integer> unselectedIconRes = new ArrayList<>();       //tab未选中图标集合
    private ArrayList<String> titleRes = new ArrayList<>();                 //tab标题集合
    private ArrayList<Fragment> fsRes = new ArrayList<>();                  //fragment集合
    private List<CustomTabEntity> data = new ArrayList<>();                 //CommonTabLayout 所需数据集合



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化dataBing
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor(getString(R.string.status_bar_compat_mall)));
        initData();
        initListener();

        RecordUtils.token= SharedPreferencesUtils.getInstance(MainActivity.this).getLoginPhoneBean().data.token;

    }

    /**
     * 初始化数据
     */
    private void initData() {
        //图片选中资源
        selectedIconRes.add(R.mipmap.icon_mall_press);
        selectedIconRes.add(R.mipmap.icon_rack_press);
        selectedIconRes.add(R.mipmap.icon_group_press);
        selectedIconRes.add(R.mipmap.icon_me_press);
        //图片未选中资源
        unselectedIconRes.add(R.mipmap.icon_mall_normal);
        unselectedIconRes.add(R.mipmap.icon_rack_normal);
        unselectedIconRes.add(R.mipmap.icon_group_normal);
        unselectedIconRes.add(R.mipmap.icon_me_normal);
        //标题资源
        titleRes.add(getString(R.string.table1));
        titleRes.add(getString(R.string.table2));
        titleRes.add(getString(R.string.table3));
        titleRes.add(getString(R.string.table4));
        //fragment数据
        fsRes.add(new TableMallFragment());
        fsRes.add(new TableRackFragment());
        fsRes.add(new TableGroupFragment());
        fsRes.add(new TableMeFragment());
        //设置数据
        for (int i = 0; i < titleRes.size(); i++) {
            final int index = i;
            data.add(new CustomTabEntity() {
                @Override
                public String getTabTitle() {
                    return titleRes.get(index);
                }

                @Override
                public int getTabSelectedIcon() {
                    return selectedIconRes.get(index);
                }

                @Override
                public int getTabUnselectedIcon() {
                    return unselectedIconRes.get(index);
                }
            });
        }
        //设置数据
        binding.mLayout.setTabData((ArrayList<CustomTabEntity>)data, this, R.id.main_layout, fsRes);

    }
    public void initListener(){
        binding.mLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                binding.mLayout.setCurrentTab(position);
                EventBus.getDefault().post(MessageWrap.getInstance(String.valueOf(position)));
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        binding.mLayout.setCurrentTab(0);
        EventBus.getDefault().post(MessageWrap.getInstance(String.valueOf(0)));

    }


    @Override

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Toast.makeText(MainActivity.this.getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
                return true;
            } else {
                System.exit(0);
            }
        }
        return super.onKeyUp(keyCode, event);
    }

}
