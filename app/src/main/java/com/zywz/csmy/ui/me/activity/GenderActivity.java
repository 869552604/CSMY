package com.zywz.csmy.ui.me.activity;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.githang.statusbar.StatusBarCompat;
import com.ks.bean.LoginPhoneBean;
import com.ks.bean.UserBean;
import com.ks.centerdata.network.CenterAPI;
import com.zywz.csmy.MainActivity;
import com.zywz.csmy.R;
import com.zywz.csmy.base.BaseActivity;
import com.zywz.csmy.base.BaseObserver;
import com.zywz.csmy.databinding.ActivityGanderBinding;
import com.zywz.csmy.utils.SharedPreferencesUtils;

/**
 * Created by Administrator on 2022/5/11.
 * xfs
 * 选择性格页面
 */
public class GenderActivity extends BaseActivity implements View.OnClickListener {

    private ActivityGanderBinding binding;
    private int gender= 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_gander);
        //设置状态栏颜色
        StatusBarCompat.setStatusBarColor(this, Color.parseColor(getString(R.string.status_select_activity)));

        binding.imgMan.setOnClickListener(this);
        binding.imgGril.setOnClickListener(this);
        binding.back.setOnClickListener(this);
        binding.button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgMan:
                binding.imgMan.setImageDrawable(getDrawable(R.mipmap.table_login_man_press));
                binding.imgGril.setImageDrawable(getDrawable(R.mipmap.table_login_gril_normal));
                gender= 1;
                break;
            case R.id.imgGril:
                binding.imgMan.setImageDrawable(getDrawable(R.mipmap.table_login_man_normal));
                binding.imgGril.setImageDrawable(getDrawable(R.mipmap.table_login_gril_press));
                gender= 2;
                break;
            case R.id.button:
                LoginPhoneBean.DataBean.UserInfoBean bean = SharedPreferencesUtils.getInstance(GenderActivity.this).getLoginPhoneBean().data.userInfo;
                CenterAPI.getInstance().upDataUser(GenderActivity.this,bean.id,bean.nickName,bean.avatarUrl,String.valueOf(gender),"","","","","",
                        new BaseObserver<UserBean>() {
                            @Override
                            protected void onSuccees(UserBean userBean) throws Exception {

                            }

                            @Override
                            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                            }
                        });

                break;
            case R.id.back:
                Intent intent = new Intent(GenderActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }
}
