package com.zywz.csmy.ui.group.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zywz.csmy.R;
import com.zywz.csmy.bean.AdapterTitleBean;

import java.util.List;

public class ManLeftAdapter extends BaseQuickAdapter<AdapterTitleBean, BaseViewHolder> {
    private ItemSelectedCallBack mCallBack;
    public ManLeftAdapter(int layoutResId, List<AdapterTitleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AdapterTitleBean item) {
        helper.setText(R.id.content, item.getContent());
        if (mCallBack != null) {
            mCallBack.convert(helper, helper.getLayoutPosition());
        }

    }

    public void setItemSelectedCallBack(ItemSelectedCallBack CallBack) {
        this.mCallBack = CallBack;
    }

    public interface ItemSelectedCallBack {
        void convert(BaseViewHolder holder, int position);
    }
}