package com.zywz.csmy.ui.rack.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ks.bean.RackRecommendBean;
import com.ks.centerdata.network.CenterAPI;
import com.zywz.csmy.R;
import com.zywz.csmy.base.BaseObserver;
import com.zywz.csmy.base.RecordUtils;
import com.zywz.csmy.databinding.DialogRackCommendBinding;
import com.zywz.csmy.ui.rack.adapter.RackRecommendAdapter;
import com.zywz.csmy.utils.FormatUtils;

import java.util.ArrayList;
import java.util.List;

public class RackRecommendDialog extends DialogFragment implements View.OnClickListener {

    private Context context;
    private DialogRackCommendBinding binding;

    private String tag;
    private Updata updata;

    private RackRecommendAdapter adapter;
    private List<RackRecommendBean.DataBean> data;
    public RackRecommendDialog(Context context,String tag,Updata updata) {
        this.context=context;
        this.tag=tag;
        this.updata=updata;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setCancelable(true);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding= DataBindingUtil.inflate(inflater,R.layout.dialog_rack_commend,container,false);

        binding.left.setOnClickListener(this);
        binding.right.setOnClickListener(this);
        binding.batch.setOnClickListener(this);



        adapter=new RackRecommendAdapter(context,R.layout.adapter_rack_dialog,new ArrayList<RackRecommendBean.DataBean>());
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);

        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setAdapter(adapter);

        View view =View.inflate(context,R.layout.refresh_error,null);
        adapter.setEmptyView(view);

        TextView tv = view.findViewById(R.id.refresh);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHttpData();
            }
        });

        getHttpData();

        return binding.getRoot();
    }

    private void getHttpData() {
        CenterAPI.getInstance().getBookRecommendList(context,tag, RecordUtils.app_id,RecordUtils.getVersion(context),RecordUtils.channel_id,RecordUtils.band,RecordUtils.platform_id,RecordUtils.getTime(), new BaseObserver<RackRecommendBean>() {
            @Override
            protected void onSuccees(RackRecommendBean rackRecommendBean) throws Exception {

                //随机选3部
                adapter.setNewData(FormatUtils.getInstance().newRandomList(rackRecommendBean.data,3));

                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                        ((RackRecommendBean.DataBean)adapter.getData().get(position)).ifCheck= ! ((RackRecommendBean.DataBean)adapter.getData().get(position)).ifCheck;
                        adapter.notifyItemChanged(position);

                    }
                });

            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                adapter.setNewData(new ArrayList<RackRecommendBean.DataBean>());
                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.left:
                dismiss();
                break;
            case R.id.batch:
                getHttpData();
                break;

            case R.id.right:
                data=new ArrayList<>();
                for (int i = 0; i < adapter.getData().size(); i++) {
                    if(adapter.getData().get(i).ifCheck){
                        data.add(adapter.getData().get(i));
                    }
                }
                if(data.size()==0)
                Toast.makeText(context, "选中数量："+data.size(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        Window mWindow = getDialog().getWindow();

        WindowManager windowManager = mWindow.getWindowManager();
        Display d = windowManager.getDefaultDisplay(); // 获取屏幕宽、高用

        WindowManager.LayoutParams mLayoutParams = mWindow.getAttributes();
        mLayoutParams.width = (int) (d.getWidth() * 0.80); //设置宽度占比
        mLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;//设置高度自适应
        mLayoutParams.gravity = Gravity.CENTER; //设置显示位置

        // 设置activity变暗的效果
        mLayoutParams.dimAmount = 0.6f;  //0是毫无效果  1是全黑
        mLayoutParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;

        mWindow.setAttributes(mLayoutParams);
        mWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//隐藏dialog自带的背景，才会显示xml设置的背景

    }

    public interface Updata{
        void upData();
    }

}
