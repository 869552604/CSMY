package com.zywz.csmy.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.githang.statusbar.StatusBarCompat;
import com.ks.bean.RankingBean;
import com.ks.centerdata.network.CenterAPI;
import com.zywz.csmy.R;
import com.zywz.csmy.base.BaseActivity;
import com.zywz.csmy.base.BaseObserver;
import com.zywz.csmy.base.RecordUtils;
import com.zywz.csmy.databinding.ActivityGroupSelectBinding;
import com.zywz.csmy.ui.group.adapter.ManRightAdapter;
import com.zywz.csmy.ui.group.adapter.SelectGroupAdapter;
import com.zywz.csmy.ui.group.bean.GroupMultiItem;
import com.zywz.csmy.ui.rack.dialog.RackRecommendDialog;
import com.zywz.csmy.utils.FormatUtils;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

public class GroupSelectActivity extends BaseActivity implements View.OnClickListener {

    private ActivityGroupSelectBinding binding;
    private String words=""; //字数： 1  50w以下，2  50w-100w，3  100w以上
    private String state="";//状态 连载，完本
    private String today="";//是否只获取当天有更新章节的书籍：0否，1是
    private String type="";
    private String gender="1";
    private String scope="2"; //默认给周榜
    private int page=1;
    private String category="";

    private List<GroupMultiItem> allItem;
    private List<RankingBean.DataBean> data;
    private SelectGroupAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_group_select);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor(getString(R.string.status_select_activity)));

        category=getIntent().getStringExtra("category");
        type=getIntent().getStringExtra("type");
        gender=getIntent().getStringExtra("gender");
        binding.title.setText(category);

        binding.bgBack.setOnClickListener(this);
        binding.search.setOnClickListener(this);

        binding.allWords.setOnClickListener(this);
        binding.words1.setOnClickListener(this);
        binding.words2.setOnClickListener(this);
        binding.words3.setOnClickListener(this);

        binding.allState.setOnClickListener(this);
        binding.state1.setOnClickListener(this);
        binding.state2.setOnClickListener(this);
        binding.state3.setOnClickListener(this);

        initAdapter();

        getHttpData();

        binding.refreshLayout.setColorSchemeColors(getResources().getColor(R.color.refresh_color));
        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                getHttpData();
            }
        });
    }

    private void initAdapter() {
        data=new ArrayList<>();
        adapter=new SelectGroupAdapter(R.layout.adapter_group_select,data,GroupSelectActivity.this);
        LinearLayoutManager manager =new LinearLayoutManager(GroupSelectActivity.this);
        binding.rightRecyclerView.setLayoutManager(manager);
        binding.rightRecyclerView.setAdapter(adapter);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                addData();

            }
        }, binding.rightRecyclerView);

        View view =View.inflate(GroupSelectActivity.this,R.layout.refresh_error,null);
        adapter.setEmptyView(view);

        TextView tv = view.findViewById(R.id.refresh);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page=1;
                getHttpData();
            }
        });
    }

    private void getHttpData() {
        CenterAPI.getInstance().getRankings(GroupSelectActivity.this, type, RecordUtils.app_id, RecordUtils.getVersion(this), RecordUtils.channel_id, RecordUtils.band, RecordUtils.platform_id
                , RecordUtils.getTime(), gender, scope, String.valueOf(page), RecordUtils.pages, category, state, today, words, new BaseObserver<RankingBean>() {
                    @Override
                    protected void onSuccees(RankingBean rankingBean) throws Exception {
                        if(binding.refreshLayout.isRefreshing()){
                            binding.refreshLayout.setRefreshing(false);
                            Toast.makeText(GroupSelectActivity.this,getString(R.string.refresh_success),Toast.LENGTH_SHORT).show();
                        }

//                        if(rankingBean.data.size()==0) {
//                            View view =View.inflate(GroupSelectActivity.this,R.layout.refresh_error,null);
//                            adapter.setEmptyView(view);
//                        }

                        adapter.setNewData(rankingBean.data);

                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if(binding.refreshLayout.isRefreshing()){
                            binding.refreshLayout.setRefreshing(false);
                        }

                        adapter.setNewData(new ArrayList<RankingBean.DataBean>());
                        Toast.makeText(GroupSelectActivity.this,getString(R.string.error),Toast.LENGTH_SHORT).show();
                        Log.i("TAG","GroupManFragment -- > getHttpData:()"+e.toString());

                    }
                });
    }


    private void addData(){
        CenterAPI.getInstance().getRankings(GroupSelectActivity.this, type, RecordUtils.app_id, RecordUtils.getVersion(this), RecordUtils.channel_id, RecordUtils.band, RecordUtils.platform_id
                , RecordUtils.getTime(), gender, scope, String.valueOf(page), RecordUtils.pages, category, state, today, words, new BaseObserver<RankingBean>() {
                    @Override
                    protected void onSuccees(RankingBean rankingBean) throws Exception {
                        if(rankingBean.data.size()==Integer.parseInt(RecordUtils.pages)){
                            adapter.loadMoreComplete();
                        }else{
                            adapter.loadMoreEnd();
                        }

                        adapter.addData(rankingBean.data);// adapter.addData() 再原先数据后面添加
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        adapter.loadMoreFail();
                        Toast.makeText(GroupSelectActivity.this,getString(R.string.error),Toast.LENGTH_SHORT).show();
                        Log.i("TAG","GroupManFragment -- > getHttpData:()"+e.toString());
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bg_back:
                finish();
                break;
            case R.id.search:
                //TODO 功能正在实现

                break;
            case R.id.allWords:
                page=1;
                words="";
                binding.allWords.setBackground(getDrawable(R.drawable.button_press));
                binding.words1.setBackground(null);
                binding.words2.setBackground(null);
                binding.words3.setBackground(null);
                binding.allWords.setTextColor(getColor(R.color.select_tv_press));
                binding.words1.setTextColor(getColor(R.color.select_tv_normal));
                binding.words2.setTextColor(getColor(R.color.select_tv_normal));
                binding.words3.setTextColor(getColor(R.color.select_tv_normal));
                getHttpData();
                break;
            case R.id.words1:
                page=1;
                words="1";
                binding.allWords.setBackground(null);
                binding.words1.setBackground(getDrawable(R.drawable.button_press));
                binding.words2.setBackground(null);
                binding.words3.setBackground(null);
                binding.allWords.setTextColor(getColor(R.color.select_tv_normal));
                binding.words1.setTextColor(getColor(R.color.select_tv_press));
                binding.words2.setTextColor(getColor(R.color.select_tv_normal));
                binding.words3.setTextColor(getColor(R.color.select_tv_normal));
                getHttpData();
                break;
            case R.id.words2:
                page=1;
                words="2";
                binding.allWords.setBackground(null);
                binding.words1.setBackground(null);
                binding.words2.setBackground(getDrawable(R.drawable.button_press));
                binding.words3.setBackground(null);
                binding.allWords.setTextColor(getColor(R.color.select_tv_normal));
                binding.words1.setTextColor(getColor(R.color.select_tv_normal));
                binding.words2.setTextColor(getColor(R.color.select_tv_press));
                binding.words3.setTextColor(getColor(R.color.select_tv_normal));
                getHttpData();
                break;
            case R.id.words3:
                page=1;
                words="3";
                binding.allWords.setBackground(null);
                binding.words1.setBackground(null);
                binding.words2.setBackground(null);
                binding.words3.setBackground(getDrawable(R.drawable.button_press));
                binding.allWords.setTextColor(getColor(R.color.select_tv_normal));
                binding.words1.setTextColor(getColor(R.color.select_tv_normal));
                binding.words2.setTextColor(getColor(R.color.select_tv_normal));
                binding.words3.setTextColor(getColor(R.color.select_tv_press));
                getHttpData();
                break;

            case R.id.allState:
                page=1;
                state="";
                today="";
                binding.allState.setBackground(getDrawable(R.drawable.button_press));
                binding.state1.setBackground(null);
                binding.state2.setBackground(null);
                binding.state3.setBackground(null);
                binding.allState.setTextColor(getColor(R.color.select_tv_press));
                binding.state1.setTextColor(getColor(R.color.select_tv_normal));
                binding.state2.setTextColor(getColor(R.color.select_tv_normal));
                binding.state3.setTextColor(getColor(R.color.select_tv_normal));
                binding.allState.setText(getString(R.string.table46));
                getHttpData();
                break;
            case R.id.state1:
                page=1;
                state=getString(R.string.table51);
                today="";
                binding.allState.setBackground(null);
                binding.state1.setBackground(getDrawable(R.drawable.button_press));
                binding.state2.setBackground(null);
                binding.state3.setBackground(null);
                binding.allState.setTextColor(getColor(R.color.select_tv_normal));
                binding.state1.setTextColor(getColor(R.color.select_tv_press));
                binding.state2.setTextColor(getColor(R.color.select_tv_normal));
                binding.state3.setTextColor(getColor(R.color.select_tv_normal));
                binding.allState.setText(getString(R.string.table50));
                getHttpData();
                break;
            case R.id.state2:
                page=1;
                state=getString(R.string.table48);
                today="";
                binding.allState.setBackground(null);
                binding.state1.setBackground(null);
                binding.state2.setBackground(getDrawable(R.drawable.button_press));
                binding.state3.setBackground(null);
                binding.allState.setTextColor(getColor(R.color.select_tv_normal));
                binding.state1.setTextColor(getColor(R.color.select_tv_normal));
                binding.state2.setTextColor(getColor(R.color.select_tv_press));
                binding.state3.setTextColor(getColor(R.color.select_tv_normal));
                binding.allState.setText(getString(R.string.table50));
                getHttpData();
                break;
            case R.id.state3:
                page=1;
                state="";
                today="1";
                binding.allState.setBackground(null);
                binding.state1.setBackground(null);
                binding.state2.setBackground(null);
                binding.state3.setBackground(getDrawable(R.drawable.button_press));
                binding.allState.setTextColor(getColor(R.color.select_tv_normal));
                binding.state1.setTextColor(getColor(R.color.select_tv_normal));
                binding.state2.setTextColor(getColor(R.color.select_tv_normal));
                binding.state3.setTextColor(getColor(R.color.select_tv_press));
                binding.allState.setText(getString(R.string.table50));
                getHttpData();
                break;
        }
    }
}
