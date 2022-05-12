package com.ks.model;





import com.ks.BodyBean.BodyLoginPhone;
import com.ks.BodyBean.BodyRegister;
import com.ks.BodyBean.BodyUser;
import com.ks.bean.BaseBean;
import com.ks.bean.CategorysBean;
import com.ks.bean.LoginBean;
import com.ks.bean.LoginPhoneBean;
import com.ks.bean.RackBookSelfBean;
import com.ks.bean.RackRecommendBean;
import com.ks.bean.RankingBean;
import com.ks.bean.UserBean;
import com.ks.bean.bannerBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * 接口请求类
 * Created by Administrator on 2022/4/24.
 * xfs
 *
 * POST请求： 如果需要传Body  标志符： @Body 对象   对象类记得继承 PostPublicBean  因为有必传的7个公共参数
 * GET请求：  如果需要传Body  标志符： @Body 对象   对象类不需要继承PostPublicBean。因为Get请求不需要用到 sign参数
 *
 * 其他标记符说明：
 *  一：接口需要传参： 格式为："v1/rankings/{type}"  标志符：@Path("type")String type
 *  二：请求头带参数： 标志符：@Header ("Authorization")String token
 *  三：query带参数： 标志符：@Query("app_id") String app_id
 */

public interface Api{
    //不详
    @GET("v1/configs")
    Observable<LoginBean> setLogin(@Query("app_id") String app_id, @Query("version") String version, @Query("channel_id") String channel_id);

    //获取banner
    @GET("v1/home/banners/{home_channel_id}")
    Observable<bannerBean> getBanner(@Path("home_channel_id")String home_channel_id, @Query("app_id") String app_id, @Query("version") String version, @Query("channel_id") String channel_id
    , @Query("band") String band, @Query("platform_id") String platform_id, @Query("time") String time);

    //获取分类 推荐榜...新书榜的数据
    @GET("v1/rankings/{type}")
    Observable<RankingBean> getRankings(@Path("type")String type,@Query("app_id")String app_id,@Query("version")String version,@Query("channel_id")String channel_id,@Query("band")String band
    ,@Query("platform_id")String platform_id,@Query("time")String time,@Query("gender")String gender,@Query("scope")String scope,@Query("page")String page,@Query("pages")String pages
    ,@Query("category")String category,@Query("state")String state,@Query("today")String today,@Query("words")String words);

    //获取其他分类的总标题
    @GET("v1/categorys")
    Observable<CategorysBean> getCategorys(@Query("app_id")String app_id,@Query("version")String version,@Query("channel_id")String channel_id,@Query("band")String band
            ,@Query("platform_id")String platform_id,@Query("time")String time,@Query("gender")String gender);

    //获取书架推荐书籍
    @GET("v1/bookRecommend/{tag}")
    Observable<RackRecommendBean>getBookRecommendList(@Path("tag")String tag,@Query("app_id")String app_id,@Query("version")String version,@Query("channel_id")String channel_id,@Query("band")String band
            ,@Query("platform_id")String platform_id,@Query("time")String time);

    //获取书架列表
    @GET("v1/bookshelfs/{user_id}")
    Observable<RackBookSelfBean> getRackBookSelfBean(@Header ("Authorization")String token,@Path("user_id")String user_id, @Query("app_id")String app_id, @Query("version")String version, @Query("channel_id")String channel_id, @Query("band")String band
            , @Query("platform_id")String platform_id, @Query("time")String time, @Query("last_sync_time")String last_sync_time, @Query("sort")String sort, @Query("sort_by")String sort_by
            , @Query("page")String page, @Query("pages")String pages);

    //手机号登陆
    @POST("v1/users/login")
    Observable<LoginPhoneBean> setLogin(@Body BodyLoginPhone body);

    //手机注册
    @POST("v2/users/register")
    Observable<BaseBean> register(@Body BodyRegister body);

    //修改用户信息
    @PUT("v1/users/{user_id}")
    Observable<UserBean> upDataUser(@Path("user_id")String user_id, @Body BodyUser user);

}
