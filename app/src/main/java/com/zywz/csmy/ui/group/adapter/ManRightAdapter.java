package com.zywz.csmy.ui.group.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zywz.csmy.R;
import com.zywz.csmy.ui.group.bean.GroupMultiItem;
import com.zywz.csmy.utils.ThumbUtils;

import java.util.List;

public class ManRightAdapter extends BaseMultiItemQuickAdapter<GroupMultiItem, BaseViewHolder> {
    private Context context;
    public ManRightAdapter(Context context,List<GroupMultiItem> data) {
        super(data);
        this.context=context;
        //设置当传入的itemType为某个常量显示不同的item
        addItemType(GroupMultiItem.ALL, R.layout.adapter_group_all_item);
        addItemType(GroupMultiItem.OTHER,R.layout.adapter_group_other_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, GroupMultiItem item) {
        switch (helper.getItemViewType()){
            case GroupMultiItem.ALL:
                helper.setImageResource(R.id.iv_img,item.getUrl());
                helper.setText(R.id.content,item.getTitle());
                break;
            case GroupMultiItem.OTHER:
                ThumbUtils.getInstance().setImage(context,(ImageView) helper.getView(R.id.iv_img),item.getImgUrl(),R.mipmap.cache_img);
                helper.setText(R.id.title,item.getTitle());
                helper.setText(R.id.content,item.getContent());
                helper.setText(R.id.type,item.getType());
                helper.setText(R.id.words,item.getWords());
                helper.setText(R.id.position,String.valueOf(helper.getLayoutPosition()+1));
                if(helper.getLayoutPosition()==0){
                    helper.setBackgroundRes(R.id.position,R.mipmap.table_group_img9);
                    helper.setTextColor(R.id.position, Color.parseColor("#FFFFFF"));
                }else if(helper.getLayoutPosition()==1){
                    helper.setBackgroundRes(R.id.position,R.mipmap.table_group_img10);
                    helper.setTextColor(R.id.position, Color.parseColor("#FFFFFF"));
                }else if(helper.getLayoutPosition()==2){
                    helper.setBackgroundRes(R.id.position,R.mipmap.table_group_img11);
                    helper.setTextColor(R.id.position, Color.parseColor("#FFFFFF"));
                }else{
                    helper.setBackgroundColor(R.id.position,Color.TRANSPARENT);
                    helper.setTextColor(R.id.position, Color.parseColor("#FE7D52"));
                }
                break;
        }

    }
}
