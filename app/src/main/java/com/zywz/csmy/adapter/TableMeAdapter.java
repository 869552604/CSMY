package com.zywz.csmy.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zywz.csmy.R;
import com.zywz.csmy.bean.AdapterTableMeBean;

import java.util.List;

public class TableMeAdapter extends BaseQuickAdapter<AdapterTableMeBean, BaseViewHolder> {
    public TableMeAdapter( int layoutResId, List<AdapterTableMeBean> data){
        super(layoutResId,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AdapterTableMeBean item) {
        helper.setImageResource(R.id.iv_img,item.getUrl());
        helper.setText(R.id.content,item.getContent());
    }
}
