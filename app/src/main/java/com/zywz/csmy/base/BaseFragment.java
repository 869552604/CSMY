package com.zywz.csmy.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.zywz.csmy.eventBusBean.MessageWrap;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2022/4/25.
 * xfs
 *
 */
public abstract class BaseFragment extends Fragment {

    private View mRootView;

    public <T> ObservableTransformer<T,T> setThread(){
        return new ObservableTransformer<T,T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        if (mRootView == null) {
            init(inflater,container,savedInstanceState);
            mRootView = getLayoutResId();

        }
        // 缓存的rootView需要判断是否已经被加过parent，
        // 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }

        return mRootView;
    }

    /**
     * 初始化操作
     */
    public abstract void init(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState);

    /**
     * @return 布局文件id
     */
    public abstract View getLayoutResId();

    @Override
    public void onStart() {
        super.onStart();
        //注册监听 已注册监听 不能继续注册
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消监听
        EventBus.getDefault().unregister(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetEventBus(MessageWrap wrap) {

    }

    private long DIFF = 1000;
    private int lastButtonId = -1;
    private long lastClickTime = 0;

    public boolean isFastDoubleClick(int buttonId) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (lastButtonId == buttonId && lastClickTime > 0 && timeD < DIFF) {
            return true;
        }
        lastClickTime = time;
        lastButtonId = buttonId;
        return false;
    }

}
