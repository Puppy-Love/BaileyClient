package com.min.bailey.client.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.min.bailey.client.R;
import com.min.bailey.client.app.data.api.Constant;
import com.min.bailey.client.app.utils.ObjectUtils;
import com.min.bailey.client.app.utils.SPUtils;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * @author Administrator
 */
public class SplashActivity extends BaseActivity {

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }


    @SuppressLint("CheckResult")
    @Override
    public void initData(Bundle savedInstanceState) {
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .compose(RxLifecycleUtils.bindToLifecycle(this))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
//                    Boolean isFirst = SPUtils.getInstance(this, Constant.ACCOUNT_INFO).getBoolean(Constant.ACCOUNT_FIRST, true);
//                    if (isFirst) {
//                        ArmsUtils.startActivity(IntroActivity.class);
//                        SPUtils.getInstance(this, Constant.ACCOUNT_INFO).put(Constant.ACCOUNT_FIRST, false);
//                    } else {
//                        ArmsUtils.startActivity(MainTabActivity.class);
//
//                        String mToken = SPUtils.getInstance(this, Constant.SP_TOKEN).getString(Constant.TOKEN_KEY);
//                        if (ObjectUtils.isNotEmpty(mToken)) {
//                            ArmsUtils.startActivity(MainTabActivity.class);
//                        } else {
//                            ArmsUtils.startActivity(LoginActivity.class);
//                        }
//                    }
                    ArmsUtils.startActivity(MainTabActivity.class);

                    finish();
                });
    }
}
