package com.min.bailey.client.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

import javax.inject.Inject;

import com.jess.arms.utils.RxLifecycleUtils;
import com.min.bailey.client.app.data.entity.RandomPhotoData;
import com.min.bailey.client.mvp.contract.GirlItemContract;
import com.min.bailey.client.mvp.ui.adapter.GirlItemAdapter;

import java.util.ArrayList;
import java.util.List;


@ActivityScope
public class GirlItemPresenter extends BasePresenter<GirlItemContract.Model, GirlItemContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    private List<RandomPhotoData.ResBean.VerticalBean> mData = new ArrayList<>();
    private GirlItemAdapter mAdapter;
    private int skip = 0;
    private String order;

    @Inject
    public GirlItemPresenter(GirlItemContract.Model model, GirlItemContract.View rootView) {
        super(model, rootView);
    }

    /**
     * @describe 初始化适配器
     */
    public void initAdapter() {
        if (mAdapter == null) {
            mAdapter = new GirlItemAdapter(mImageLoader, mData);
            mRootView.setAdapter(mAdapter);
            // 点击进入详细页面
            mAdapter.setOnItemClickListener((adapter, view, position) -> {

            });
        }
    }




    /**
     * 获取豆瓣美女
     *
     * @param refresh
     * @param cid
     */
    public void getDouBanGirl(boolean refresh) {
        if (refresh) {
            skip = 0;
            order = "new";
        } else {
            order = "hot";
            skip = skip + 10;
        }
        mModel.getRandomPhoto(30, 1, skip, order, true)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .doOnSubscribe(disposable -> mRootView.showLoading())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.hideLoading())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
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
