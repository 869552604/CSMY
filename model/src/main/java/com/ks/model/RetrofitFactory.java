package com.ks.model;




import android.content.Context;

import com.ks.BodyBean.BodyLoginPhone;
import com.ks.BodyBean.BodyRegister;
import com.ks.BodyBean.BodyUser;
import com.ks.centerdata.network.ModeNetwork;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2022/4/24.
 * xfs
 * 接口请求放在 Api 类中
 */

public class RetrofitFactory implements ModeNetwork {

    private static Api api;  //接口请求类，新增接口放在这个类中
    public RetrofitFactory(){
        if (api==null){
            synchronized (RetrofitFactory.class) {
                if (api == null){
                    OkHttpClient mOkHttpClient=new OkHttpClient.Builder()
                            .connectTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                            .readTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                            .writeTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                            .addInterceptor(InterceptorUtil.LogInterceptor())//添加日志拦截器
                            .addInterceptor(new ResponseDecryptInterceptor())//添加解密器
                            .build();
                    Retrofit mRetrofit=new Retrofit.Builder()
                            .baseUrl(HttpConfig.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                            .client(mOkHttpClient)
                            .build();
                    api =mRetrofit.create(Api.class);

                }

            }

        }
    }

    @Override
    public void login(Context context, String app_id, String version, String channel_id, Observer observer) {
        api.setLogin(app_id,version,channel_id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getBanner(Context context, String home_channel_id, String app_id, String version, String channel_id, String band, String platform_id, String time, Observer observer) {
        api.getBanner(home_channel_id,app_id,version,channel_id,band,platform_id,time)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getRankings(Context context, String type, String app_id, String version, String channel_id, String band, String platform_id, String time, String gender, String scope, String page, String pages, String category, String state, String today, String words, Observer observer) {
        api.getRankings(type,app_id,version,channel_id,band,platform_id,time,gender,scope,page,pages,category,state,today,words)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getCategorys(Context context, String app_id, String version, String channel_id, String band, String platform_id, String time, String gender, Observer observer) {
        api.getCategorys(app_id,version,channel_id,band,platform_id,time,gender)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getBookRecommendList(Context context,String tag, String app_id, String version, String channel_id, String band, String platform_id, String time, Observer observer) {
        api.getBookRecommendList(tag,app_id,version,channel_id,band,platform_id,time)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getRackBookSelfBean(Context context,String token, String user_id, String app_id, String version, String channel_id, String band, String platform_id, String time, String last_sync_time, String sort, String sort_by, String page, String pages, Observer observer) {
        api.getRackBookSelfBean(token,user_id, app_id, version, channel_id, band, platform_id, time, last_sync_time, sort, sort_by, page, pages)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void loginByPhone(Context context,String sign, String mobile, String tourist, String tourist_id, String code, String app_id, String version, String channel_id, String platform_id, String band, String os, String imei, String oaid, String uuid, String time, String phone_model, Observer observer) {
        BodyLoginPhone bean=new BodyLoginPhone();
        bean.sign=sign;
        bean.mobile=mobile;bean.tourist=tourist;bean.tourist_id=tourist_id;bean.code=code;bean.app_id=app_id;bean.version=version;
        bean.channel_id=channel_id;bean.platform_id=platform_id;bean.band=band;bean.os=os;bean.imei=imei;bean.oaid=oaid;bean.uuid=uuid;bean.time=time;bean.phone_model=phone_model;
        api.setLogin(bean)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void register(Context context, String sign, String mobile, String tourist_id, String code, String app_id, String version, String channel_id, String platform_id, String band, String time, String uuid, String phone_model, String password, Observer observer) {
        BodyRegister bean =new BodyRegister();
        bean.sign=sign;
        bean.mobile=mobile;bean.tourist_id=tourist_id;bean.code=code;bean.app_id=app_id;bean.version=version;
        bean.channel_id=channel_id;bean.platform_id=platform_id;bean.band=band;bean.uuid=uuid;bean.time=time;bean.phone_model=phone_model;bean.password=password;
        api.register(bean)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void upDataUser(Context context,String user_id, String nickname, String avatar, String gender, String birthday, String intro, String personalise_ad, String personalise_recommend, String local_avatar, Observer observer) {
        BodyUser user =new BodyUser();
        user.nickname=nickname;user.avatar=avatar;user.gender=gender;user.birthday=birthday;user.intro=intro;user.personalise_ad=personalise_ad;
        user.personalise_recommend=personalise_recommend;user.local_avatar=local_avatar;
        api.upDataUser(user_id,user)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


}
