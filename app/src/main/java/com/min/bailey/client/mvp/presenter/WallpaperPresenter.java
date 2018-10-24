package com.min.bailey.client.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import javax.inject.Inject;

import com.min.bailey.client.app.data.entity.RandomPhotoData;
import com.min.bailey.client.app.utils.RxUtils;
import com.min.bailey.client.mvp.contract.WallpaperContract;
import com.min.bailey.client.mvp.ui.adapter.GirlItemAdapter;


@ActivityScope
public class WallpaperPresenter extends BasePresenter<WallpaperContract.Model, WallpaperContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    private GirlItemAdapter mAdapter;
    private String order;

    @Inject
    public WallpaperPresenter(WallpaperContract.Model model, WallpaperContract.View rootView) {
        super(model, rootView);
    }

    /**
     * @describe 初始化适配器
     */
    public void initAdapter() {
        if (mAdapter == null) {
            mAdapter = new GirlItemAdapter(null);
            mRootView.setAdapter(mAdapter);
            // 点击进入详细页面
            mAdapter.setOnItemClickListener((adapter, view, position) -> {

            });
        }
    }

    public void getWallpaperBySort(boolean refresh, String id) {
        if (refresh) {
            order = "new";
        } else {
            order = "hot";
        }
        mModel.getWallpaperBySort(id, 30, true, 1, order)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<RandomPhotoData>(mErrorHandler) {
                    @Override
                    public void onNext(RandomPhotoData data) {
                        mRootView.onLoadMoreComplete(mAdapter);
                        if (data.isSuccess()) {
                            if (refresh) {
                                mAdapter.setNewData(data.getRes().getVertical());
                            } else {
                                mAdapter.addData(data.getRes().getVertical());
                            }
                        } else {
                            mRootView.onLoadMoreEnd(mAdapter);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        mRootView.onLoadMoreError(mAdapter);
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
