package com.min.bailey.client.mvp.presenter;

import android.app.Application;
import android.content.Intent;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.min.bailey.client.app.data.entity.WallpaperSortData;
import com.min.bailey.client.app.utils.RxUtils;
import com.min.bailey.client.mvp.contract.WallpaperSortContract;
import com.min.bailey.client.mvp.ui.activity.WallpaperActivity;
import com.min.bailey.client.mvp.ui.adapter.WallpaperSortAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


/**
 * @author Administrator
 */
@ActivityScope
public class WallpaperSortPresenter extends BasePresenter<WallpaperSortContract.Model, WallpaperSortContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    private List<WallpaperSortData.ResBean.CategoryBean> mData = new ArrayList<>();
    private WallpaperSortAdapter mAdapter;

    @Inject
    public WallpaperSortPresenter(WallpaperSortContract.Model model, WallpaperSortContract.View rootView) {
        super(model, rootView);
    }

    /**
     * 初始化适配器
     */
    public void initAdapter() {
        if (mAdapter == null) {
            mAdapter = new WallpaperSortAdapter(mImageLoader, mData);
            mRootView.setAdapter(mAdapter);
            // 点击进入详细页面
            mAdapter.setOnItemClickListener((adapter, view, position) -> {
                WallpaperSortData.ResBean.CategoryBean data = (WallpaperSortData.ResBean.CategoryBean) adapter.getData().get(position);
                mRootView.launchActivity(new Intent(mApplication, WallpaperActivity.class)
                        .putExtra("id", data.getId())
                        .putExtra("name", data.getName()));
            });
        }
    }

    public void getSort() {
        mModel.getSort(false, 1)
                .retryWhen(new RetryWithDelay(3, 2))
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<WallpaperSortData>(mErrorHandler) {
                    @Override
                    public void onNext(WallpaperSortData data) {
                        if (data.isSuccess()) {
                            mAdapter.setNewData(data.getRes().getCategory());
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

}
