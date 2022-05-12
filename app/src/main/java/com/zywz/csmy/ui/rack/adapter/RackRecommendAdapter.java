package com.zywz.csmy.ui.rack.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ks.bean.RackRecommendBean;
import com.zywz.csmy.R;
import com.zywz.csmy.utils.ThumbUtils;

import java.util.List;

public class RackRecommendAdapter extends BaseQuickAdapter<RackRecommendBean.DataBean, BaseViewHolder> {
    private Context context;
    public RackRecommendAdapter(Context context,int layoutResId,List<RackRecommendBean.DataBean> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, RackRecommendBean.DataBean item) {
        ThumbUtils.getInstance().setImageRadius(context,(ImageView) helper.getView(R.id.iv_img),item.cover,20,R.mipmap.cache_img);

        helper.setImageResource(R.id.checkbox,item.ifCheck?R.mipmap.rack_check_press:R.mipmap.rack_check_normal);
    }
}
