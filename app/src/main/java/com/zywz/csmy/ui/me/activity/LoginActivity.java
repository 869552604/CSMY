package com.zywz.csmy.ui.me.activity;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;
import com.ks.bean.BaseBean;
import com.ks.bean.LoginPhoneBean;
import com.ks.centerdata.network.CenterAPI;
import com.zywz.csmy.R;
import com.zywz.csmy.base.BaseActivity;
import com.zywz.csmy.base.BaseObserver;
import com.zywz.csmy.base.RecordUtils;
import com.zywz.csmy.databinding.ActivityLoginBinding;
import com.zywz.csmy.utils.AllUtils;
import com.zywz.csmy.utils.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by Administrator on 2022/5/10.
 * xfs
 * 登陆页面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private ActivityLoginBinding binding;
    private CountDownTimer timer;
    private long number=10000;
    private String password="123456";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_login);
        //设置状态栏颜色
        StatusBarCompat.setStatusBarColor(this, Color.parseColor(getString(R.string.status_select_activity)));

        binding.back.setOnClickListener(this);
        binding.getCode.setOnClickListener(this);
        binding.button.setOnClickListener(this);
        binding.delete.setOnClickListener(this);
        binding.register.setOnClickListener(this);

        binding.phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(AllUtils.getInstance().isMobileNo(charSequence.toString())){
                    binding.getCode.setEnabled(true);
                    binding.button.setEnabled(true);
                    binding.getCode.setTextColor(getResources().getColor(R.color.login_get_code_press));
                    binding.button.setBackground(getDrawable(R.drawable.button_bg_ff7443_5));
                }else{
                    binding.getCode.setEnabled(false);
                    binding.button.setEnabled(false);
                    binding.getCode.setTextColor(getResources().getColor(R.color.login_get_code_normal));
                    binding.button.setBackground(getDrawable(R.drawable.button_bg_ccc_5));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(TextUtils.isEmpty(charSequence.toString())){
                    binding.delete.setVisibility(View.GONE);
                }else{
                    binding.delete.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.getCode:
                binding.getCode.setEnabled(false);
                binding.getCode.setTextColor(getResources().getColor(R.color.login_get_code_loading));
                timer=new CountDownTimer(number,1000) {
                    @Override
                    public void onTick(long l) {
                        binding.getCode.setText(getString(R.string.login_get_code,l/1000));
                    }

                    @Override
                    public void onFinish() {
                        binding.getCode.setText("获取验证码");
                        binding.getCode.setEnabled(true);
                        binding.getCode.setTextColor(getResources().getColor(R.color.login_get_code_press));
                    }
                };
                timer.start();
                break;
            case R.id.button: {
                if (TextUtils.isEmpty(binding.code.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "请输入验证码", Toast.LENGTH_LONG).show();
                    return;
                }
                if (binding.code.getText().toString().length() != 6) {
                    Toast.makeText(LoginActivity.this, "code必须是6个字符", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!binding.checkbox.isChecked()) {
                    Toast.makeText(LoginActivity.this, "请勾选我同意登陆", Toast.LENGTH_LONG).show();
                    return;
                }
                Map<String, Object> map = RecordUtils.getPublicMap(LoginActivity.this);
                map.put("mobile", binding.phone.getText().toString());
                map.put("tourist", RecordUtils.tourist);
                map.put("tourist_id", RecordUtils.tourist);
                map.put("code", binding.code.getText().toString());
                map.put("os", RecordUtils.os);
                map.put("imei", RecordUtils.imei);
                map.put("oaid", RecordUtils.oaid);
                map.put("uuid", RecordUtils.uuid);
                map.put("phone_model", RecordUtils.phone_model);
                String sign = AllUtils.getInstance().getSignature(map);


                CenterAPI.getInstance().loginByPhone(LoginActivity.this,
                        sign,
                        map.get("mobile").toString(),
                        map.get("tourist").toString(),
                        map.get("tourist_id").toString(),
                        map.get("code").toString(),
                        map.get("app_id").toString(),
                        map.get("version").toString(),
                        map.get("channel_id").toString(),
                        map.get("platform_id").toString(),
                        map.get("band").toString(),
                        map.get("os").toString(),
                        map.get("imei").toString(),
                        map.get("oaid").toString(),
                        map.get("uuid").toString(),
                        map.get("time").toString(),
                        map.get("phone_model").toString(), new BaseObserver<LoginPhoneBean>() {
                            @Override
                            protected void onSuccees(LoginPhoneBean loginPhoneBean) throws Exception {
                                if (loginPhoneBean.status.equals("100")) {
                                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                    SharedPreferencesUtils.getInstance(LoginActivity.this).setLoginPhoneBean(loginPhoneBean);
                                    if(loginPhoneBean.data.userInfo.isOld==0){
                                        //旧用户
                                    }else{
                                        //新用户
                                    }


                                } else {
                                    Toast.makeText(LoginActivity.this, loginPhoneBean.message, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                                Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
                break;
            case R.id.delete:
                binding.code.setText("");
                binding.delete.setVisibility(View.GONE);
                break;

            case R.id.register: {
                if (TextUtils.isEmpty(binding.code.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "请输入验证码", Toast.LENGTH_LONG).show();
                    return;
                }
                if (binding.code.getText().toString().length() != 6) {
                    Toast.makeText(LoginActivity.this, "code必须是6个字符", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!binding.checkbox.isChecked()) {
                    Toast.makeText(LoginActivity.this, "请勾选我同意登陆", Toast.LENGTH_LONG).show();
                    return;
                }
                Map<String, Object> map = RecordUtils.getPublicMap(LoginActivity.this);
                map.put("uuid", RecordUtils.uuid);
                map.put("tourist_id", RecordUtils.tourist);
                map.put("code", binding.code.getText().toString());
                map.put("password", password);
                map.put("mobile", binding.phone.getText().toString());
                map.put("phone_model", RecordUtils.phone_model);

                String sign = AllUtils.getInstance().getSignature(map);

                CenterAPI.getInstance().register(LoginActivity.this,sign,binding.phone.getText().toString(),RecordUtils.tourist,binding.code.getText().toString(),
                        RecordUtils.app_id,RecordUtils.getVersion(this),RecordUtils.channel_id,RecordUtils.platform_id,RecordUtils.band,RecordUtils.getTime(),RecordUtils.uuid,RecordUtils.phone_model,password, new BaseObserver<BaseBean>() {
                            @Override
                            protected void onSuccees(BaseBean baseBean) throws Exception {
                                if(baseBean.status.equals("100")){
                                    Toast.makeText(LoginActivity.this, baseBean.message, Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(LoginActivity.this, baseBean.message, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                                Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer != null){
            timer.cancel();
        }
    }
}
