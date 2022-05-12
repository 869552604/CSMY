package com.zywz.csmy.ui.group.fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ks.bean.CategorysBean;
import com.ks.bean.RankingBean;
import com.ks.centerdata.network.CenterAPI;
import com.zywz.csmy.R;
import com.zywz.csmy.base.BaseFragment;
import com.zywz.csmy.base.BaseObserver;
import com.zywz.csmy.base.RecordUtils;
import com.zywz.csmy.bean.AdapterTitleBean;
import com.zywz.csmy.databinding.FragmentGroupManBinding;
import com.zywz.csmy.ui.activity.GroupSelectActivity;
import com.zywz.csmy.ui.group.adapter.ManLeftAdapter;
import com.zywz.csmy.ui.group.adapter.ManRightAdapter;
import com.zywz.csmy.ui.group.bean.GroupMultiItem;
import com.zywz.csmy.utils.FormatUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 分类 男生
 * Created by Administrator on 2022/5/6.
 * xfs
 */
public class GroupManFragment extends BaseFragment implements View.OnClickListener {

    private FragmentGroupManBinding binding;
    private int currentPosition = 0;

    private List<AdapterTitleBean> datas = new ArrayList<>();
    private List<GroupMultiItem> allItem;
    private String[] type;
    private String[] contents;
    private String gender = "1"; //女生应该这个改为2
    private String scope = "2";//榜单范围：1日榜，2周榜，3月榜，4总榜（90天）
    private int page = 1;
    private PopupWindow popupWindow;
    private String itentType = "popularity"; //目前不知道全部分类，该选那个type

    @Override
    public void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_group_man, container, false);

        contents = new String[]{getString(R.string.table21), getString(R.string.table22), getString(R.string.table23), getString(R.string.table24), getString(R.string.table25), getString(R.string.table26), getString(R.string.table27)};
        type = new String[]{getString(R.string.popularity), getString(R.string.recommend), getString(R.string.collect), getString(R.string.score), getString(R.string.search), getString(R.string.over), getString(R.string.news)};

        binding.title.setText(getString(R.string.table21));
        binding.title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        binding.scope.setVisibility(View.GONE);


        AdapterTitleBean bean;
        for (int i = 0; i < contents.length; i++) {
            bean = new AdapterTitleBean();
            bean.setContent(contents[i]);
            datas.add(bean);
        }
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        ManLeftAdapter adapter = new ManLeftAdapter(R.layout.adapter_man_left, datas);
        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setAdapter(adapter);
        //左边recycleView点击事件  切换右边recycleView展示数据
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                currentPosition = position;
                page = 1;
                adapter.notifyDataSetChanged();
                if (position == 0) {
                    //这里是全部分类
                    setAllData();
                } else {
                    //这里是加载其他模块的数据
                    getHttpData(position);
                }
            }
        });

        //设置左边RecycleView选中时的样式
        adapter.setItemSelectedCallBack(new ManLeftAdapter.ItemSelectedCallBack() {
            @Override
            public void convert(BaseViewHolder holder, int position) {

                if (position == currentPosition) {
                    ((TextView) holder.getView(R.id.content)).setTextColor(getResources().getColor(R.color.colorAccent));
                    holder.getView(R.id.view).setVisibility(View.VISIBLE);

                } else {
                    ((TextView) holder.getView(R.id.content)).setTextColor(getResources().getColor(R.color.table_text_normal));
                    holder.getView(R.id.view).setVisibility(View.INVISIBLE);
                }

            }
        });


        setAllData();

        binding.refreshLayout.setColorSchemeColors(getResources().getColor(R.color.refresh_color));
        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(currentPosition==0){
                    setAllData();
                }else {
                    page = 1;
                    getHttpData(currentPosition);
                }
            }
        });


        View popupView = getLayoutInflater().inflate(R.layout.popup_window_group, null);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);

        binding.scope.setOnClickListener(this);
        popupView.findViewById(R.id.scope_week).setOnClickListener(this);
        popupView.findViewById(R.id.scope_mouth).setOnClickListener(this);
        popupView.findViewById(R.id.scope_all).setOnClickListener(this);

    }

    private void getHttpData(final int position) {
        binding.refreshLayout.setEnabled(true);
        binding.title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        binding.scope.setVisibility(View.VISIBLE);
        if (scope.equals("2")) {
            binding.title.setText(getString(R.string.table39));
        } else if (scope.equals("3")) {
            binding.title.setText(getString(R.string.table40));
        } else if (scope.equals("4")) {
            binding.title.setText(getString(R.string.table41));
        } else {
            binding.title.setText(getString(R.string.table39));
        }

        CenterAPI.getInstance().getRankings(getContext(), type[position], RecordUtils.app_id, RecordUtils.getVersion(getContext()), RecordUtils.channel_id, RecordUtils.band, RecordUtils.platform_id
                , RecordUtils.getTime(), gender, scope, String.valueOf(page), RecordUtils.pages, "", "", "", "", new BaseObserver<RankingBean>() {
                    @Override
                    protected void onSuccees(RankingBean rankingBean) throws Exception {
                        if (binding.refreshLayout.isRefreshing()) {
                            binding.refreshLayout.setRefreshing(false);
                            Toast.makeText(getContext(), getString(R.string.refresh_success), Toast.LENGTH_SHORT).show();
                        }

                        allItem = new ArrayList<>();
                        for (int i = 0; i < rankingBean.data.size(); i++) {
                            allItem.add(new GroupMultiItem(1, RecordUtils.setImageUrl(rankingBean.data.get(i).cover), rankingBean.data.get(i).title
                                    , rankingBean.data.get(i).intro
                                    , String.format(getString(R.string.append1), rankingBean.data.get(i).category, rankingBean.data.get(i).state)
                                    , String.format(getString(R.string.append2), FormatUtils.getInstance().formatNumberWithUnit(rankingBean.data.get(i).words, 1), rankingBean.data.get(i).read_user_count)));

                        }

                        setNewData(allItem, position);

                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (binding.refreshLayout.isRefreshing()) {
                            binding.refreshLayout.setRefreshing(false);
                        }
                        allItem = new ArrayList<>();
                        setNewData(allItem, position);
                        Toast.makeText(getContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
                        Log.i("TAG", "GroupManFragment -- > getHttpData:(" + position + ")" + e.toString());


                    }
                });

    }

    private void setNewData(List<GroupMultiItem> allItem, final int position) {
        final ManRightAdapter manRightAdapter = new ManRightAdapter(getContext(), allItem);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());


        binding.rightRecyclerView.setLayoutManager(manager);
        binding.rightRecyclerView.setAdapter(manRightAdapter);
        if (allItem.size() == 0) {
            setEmptyView(manRightAdapter);
        }

        manRightAdapter.setNewData(allItem); // adapter.setNewData() 清除原先数据再重新添加


        //这里是上拉加载更多的监听
        manRightAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                addData(position, manRightAdapter);

            }
        }, binding.rightRecyclerView);


    }

    private void addData(final int position, final ManRightAdapter manRightAdapter) {
        CenterAPI.getInstance().getRankings(getContext(), type[position], RecordUtils.app_id, RecordUtils.getVersion(getContext()), RecordUtils.channel_id, RecordUtils.band, RecordUtils.platform_id
                , RecordUtils.getTime(), gender, scope, String.valueOf(page), RecordUtils.pages, "", "", "", "", new BaseObserver<RankingBean>() {
                    @Override
                    protected void onSuccees(RankingBean rankingBean) throws Exception {
                        if (rankingBean.data.size() == Integer.parseInt(RecordUtils.pages)) {
                            manRightAdapter.loadMoreComplete();
                        } else {
                            manRightAdapter.loadMoreEnd();
                        }
                        allItem = new ArrayList<>();
                        for (int i = 0; i < rankingBean.data.size(); i++) {
                            allItem.add(new GroupMultiItem(1, RecordUtils.setImageUrl(rankingBean.data.get(i).cover), rankingBean.data.get(i).title
                                    , rankingBean.data.get(i).intro
                                    , getString((R.string.append1), rankingBean.data.get(i).category, rankingBean.data.get(i).state)
                                    , getString((R.string.append2), FormatUtils.getInstance().formatNumberWithUnit(rankingBean.data.get(i).words, 1), rankingBean.data.get(i).read_user_count)));

                        }
                        manRightAdapter.addData(allItem);// adapter.addData() 再原先数据后面添加
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        manRightAdapter.loadMoreFail();
                        Toast.makeText(getContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
                        Log.i("TAG", "GroupManFragment -- > getHttpData:(" + position + ")" + e.toString());
                    }
                });
    }

    private void setAllData() {
//        binding.refreshLayout.setEnabled(false);
        binding.title.setText(getString(R.string.table21));
        binding.title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        binding.scope.setVisibility(View.GONE);


        allItem = new ArrayList<>();
        CenterAPI.getInstance().getCategorys(getContext(), RecordUtils.app_id, RecordUtils.getVersion(getContext()), RecordUtils.channel_id, RecordUtils.band, RecordUtils.platform_id
                , RecordUtils.getTime(), gender, new BaseObserver<CategorysBean>() {
                    @Override
                    protected void onSuccees(CategorysBean categorysBean) throws Exception {
                        if (binding.refreshLayout.isRefreshing()) {
                            binding.refreshLayout.setRefreshing(false);
                            Toast.makeText(getContext(), getString(R.string.refresh_success), Toast.LENGTH_SHORT).show();
                        }
                        for (int i = 0; i < categorysBean.data.size(); i++) {
                            if(categorysBean.data.get(i).category.equals(getString(R.string.table28))) {
                                allItem.add(new GroupMultiItem(0, R.mipmap.table_group_img1, categorysBean.data.get(i).category));
                            }else if(categorysBean.data.get(i).category.equals(getString(R.string.table29))) {
                                allItem.add(new GroupMultiItem(0, R.mipmap.table_group_img2, categorysBean.data.get(i).category));
                            }else if(categorysBean.data.get(i).category.equals(getString(R.string.table30))) {
                                allItem.add(new GroupMultiItem(0, R.mipmap.table_group_img3, categorysBean.data.get(i).category));
                            }else if(categorysBean.data.get(i).category.equals(getString(R.string.table31))) {
                                allItem.add(new GroupMultiItem(0, R.mipmap.table_group_img4, categorysBean.data.get(i).category));
                            }else if(categorysBean.data.get(i).category.equals(getString(R.string.table32))) {
                                allItem.add(new GroupMultiItem(0, R.mipmap.table_group_img5, categorysBean.data.get(i).category));
                            }else if(categorysBean.data.get(i).category.equals(getString(R.string.table33))) {
                                allItem.add(new GroupMultiItem(0, R.mipmap.table_group_img6, categorysBean.data.get(i).category));
                            }else if(categorysBean.data.get(i).category.equals(getString(R.string.table34))) {
                                allItem.add(new GroupMultiItem(0, R.mipmap.table_group_img7, categorysBean.data.get(i).category));
                            }else if(categorysBean.data.get(i).category.equals(getString(R.string.table35))) {
                                allItem.add(new GroupMultiItem(0, R.mipmap.table_group_img8, categorysBean.data.get(i).category));
                            }else{
                                allItem.add(new GroupMultiItem(0, R.mipmap.table_group_img8, categorysBean.data.get(i).category));
                            }
                        }

                        final ManRightAdapter manRightAdapter = new ManRightAdapter(getContext(), allItem);
                        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);

                        binding.rightRecyclerView.setLayoutManager(manager);
                        binding.rightRecyclerView.setAdapter(manRightAdapter);
                        manRightAdapter.notifyDataSetChanged();

                        manRightAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(getContext(), GroupSelectActivity.class);
                                intent.putExtra("category", ((GroupMultiItem) adapter.getItem(position)).getTitle());
                                intent.putExtra("type", itentType);
                                intent.putExtra("gender", gender);
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                    }
                });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scope:
                popupWindow.showAsDropDown(binding.scope);
                break;
            case R.id.scope_week:
                scope = "2";
                page = 1;
                binding.scope.setText(getString(R.string.table36));
                popupWindow.dismiss();
                getHttpData(currentPosition);
                break;
            case R.id.scope_mouth:
                scope = "3";
                page = 1;
                binding.scope.setText(getString(R.string.table37));
                popupWindow.dismiss();
                getHttpData(currentPosition);
                break;
            case R.id.scope_all:
                scope = "4";
                page = 1;
                binding.scope.setText(getString(R.string.table38));
                popupWindow.dismiss();
                getHttpData(currentPosition);
                break;
        }
    }

    private void setEmptyView(ManRightAdapter manRightAdapter) {
        View view = View.inflate(getContext(), R.layout.refresh_error, null);
        manRightAdapter.setEmptyView(view);

        TextView tv = view.findViewById(R.id.refresh);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = 1;
                getHttpData(currentPosition);
            }
        });
    }

    @Override
    public View getLayoutResId() {
        return binding.getRoot();
    }
}
