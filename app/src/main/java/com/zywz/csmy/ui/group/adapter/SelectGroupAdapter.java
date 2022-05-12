package com.zywz.csmy.ui.group.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ks.bean.RankingBean;
import com.zywz.csmy.R;
import com.zywz.csmy.bean.AdapterTableMeBean;
import com.zywz.csmy.utils.FormatUtils;
import com.zywz.csmy.utils.ThumbUtils;

import java.util.List;

public class SelectGroupAdapter extends BaseQuickAdapter<RankingBean.DataBean, BaseViewHolder> {
    private Context context;
    public SelectGroupAdapter(int layoutResId, List<RankingBean.DataBean> data,Context context){
        super(layoutResId,data);
        this.context=context;
    }

    @SuppressLint("StringFormatMatches")
    @Override
    protected void convert(BaseViewHolder helper, RankingBean.DataBean item) {
        ThumbUtils.getInstance().setImageRadius(context,(ImageView) helper.getView(R.id.iv_img),item.cover,20,R.mipmap.cache_img);
        helper.setText(R.id.title,item.title);
        helper.setText(R.id.content,item.intro);
        helper.setText(R.id.score, context.getString(R.string.group_select_score,item.score));
        helper.setText(R.id.info, context.getString(R.string.group_select_info,item.author, FormatUtils.getInstance().formatNumberWithUnit(item.words,0),item.state));

    }
}
