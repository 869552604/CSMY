package com.zywz.csmy;


import android.graphics.Color;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;
import com.ks.bean.RackBookSelfBean;
import com.ks.centerdata.network.CenterAPI;
import com.zywz.csmy.base.BaseFragment;
import com.zywz.csmy.base.BaseObserver;
import com.zywz.csmy.base.RecordUtils;
import com.zywz.csmy.databinding.FragmentTableRackBinding;
import com.zywz.csmy.eventBusBean.MessageWrap;
import com.zywz.csmy.ui.rack.dialog.RackRecommendDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * 书架
 * Created by Administrator on 2022/4/25.
 * xfs
 */
public class TableRackFragment extends BaseFragment {
    FragmentTableRackBinding binding;
    private int page=1;
    @Override
    public void init(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_table_rack,container,false);
        binding.refreshLayout.setColorSchemeColors(getResources().getColor(R.color.refresh_color));
        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                getHttpData(page);
            }
        });

        getHttpData(page);

    }

    private void getHttpData(int page) {
        CenterAPI.getInstance().getRackBookSelfBean(getContext(),RecordUtils.setToken(), RecordUtils.userId,RecordUtils.app_id,RecordUtils.getVersion(getContext()),RecordUtils.channel_id,RecordUtils.band,RecordUtils.platform_id
        ,RecordUtils.getTime(),RecordUtils.last_sync_time,RecordUtils.sort,RecordUtils.sort_by,String.valueOf(page),RecordUtils.pages, new BaseObserver<RackBookSelfBean>() {
                    @Override
                    protected void onSuccees(RackBookSelfBean rackBookSelfBean) throws Exception {
                        if(binding.refreshLayout.isRefreshing()){
                            binding.refreshLayout.setRefreshing(false);
                            Toast.makeText(getContext(),getString(R.string.refresh_success),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if(binding.refreshLayout.isRefreshing()){
                            binding.refreshLayout.setRefreshing(false);
                        }
                    }
                });
    }

    @Override
    public View getLayoutResId() {
        return binding.getRoot();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetEventBus(MessageWrap wrap) {
        //设置状态栏颜色
        if(wrap.message.equals("1")) {
            StatusBarCompat.setStatusBarColor((MainActivity) getContext(), Color.parseColor(getString(R.string.status_bar_compat_rack)));

            //弹出推荐弹窗
            RackRecommendDialog dialog =new RackRecommendDialog(getContext(), getString(R.string.rack_type), new RackRecommendDialog.Updata() {
                @Override
                public void upData() {

                }
            });
            dialog.show(getParentFragmentManager(),"1");

        }
    }
}
