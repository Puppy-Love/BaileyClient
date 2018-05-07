package com.min.bailey.client.mvp.presenter;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v4.app.Fragment;
import android.support.v4.app.SupportActivity;

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
import com.min.bailey.client.app.data.entity.MeiZhiItemData;
import com.min.bailey.client.app.data.entity.RandomPhotoData;
import com.min.bailey.client.mvp.contract.MeiZhiContract;
import com.min.bailey.client.mvp.ui.adapter.GirlItemAdapter;
import com.min.bailey.client.mvp.ui.adapter.MeiZhiAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.min.bailey.client.app.data.api.Constant.NUMBER_OF_PAGE;


/**
 * @author Administrator
 * 妹纸
 */
@ActivityScope
public class MeiZhiPresenter extends BasePresenter<MeiZhiContract.Model, MeiZhiContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    private List<MeiZhiItemData.ResultsBean> mData = new ArrayList<>();
    private MeiZhiAdapter mAdapter;
    private int lastPage = 1;

    @Inject
    public MeiZhiPresenter(MeiZhiContract.Model model, MeiZhiContract.View rootView) {
        super(model, rootView);
    }

    /**
     * 初始化适配器
     */
    public void initAdapter() {
        if (mAdapter == null) {
            mAdapter = new MeiZhiAdapter(mImageLoader, mData);
            mRootView.setAdapter(mAdapter);
            // 点击进入详细页面
            mAdapter.setOnItemClickListener((adapter, view, position) -> {

            });
        }
    }
    /**
     * 使用 2017 Google IO 发布的 Architecture Components 中的 Lifecycles 的新特性 (此特性已被加入 Support library)
     * 使 {@code Presenter} 可以与 {@link SupportActivity} 和 {@link Fragment} 的部分生命周期绑定
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {

    }

    /**
     * 获取妹纸
     *
     * @param refresh
     */
    public void getGirlList(boolean refresh) {
        if (refresh) {
            lastPage = 1;
        }
        mModel.getGirlList(NUMBER_OF_PAGE, lastPage)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .doOnSubscribe(disposable -> mRootView.showLoading())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.hideLoading())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<MeiZhiItemData>(mErrorHandler) {
                    @Override
                    public void onNext(MeiZhiItemData data) {
                        lastPage = lastPage + 1;
                        mRootView.onLoadMoreComplete(mAdapter);
                        if (refresh) {
                            mAdapter.setNewData(data.getResults());
                        } else {
                            mAdapter.addData(data.getResults());
                        }
                        if (data.getResults().size() < NUMBER_OF_PAGE) {
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
