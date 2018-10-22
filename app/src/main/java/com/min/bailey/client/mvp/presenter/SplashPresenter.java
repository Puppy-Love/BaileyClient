package com.min.bailey.client.mvp.presenter;

import android.Manifest;
import android.app.Application;
import android.widget.ImageView;

import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.Observable;
import lombok.val;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import javax.inject.Inject;

import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.PermissionUtil;
import com.min.bailey.client.app.data.api.Constant;
import com.min.bailey.client.app.data.entity.MeiZhiItemData;
import com.min.bailey.client.app.utils.RxUtils;
import com.min.bailey.client.mvp.contract.SplashContract;
import com.min.bailey.client.mvp.ui.activity.LoginActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jess.arms.utils.PermissionUtil.*;


/**
 *
 * @author zhangmin
 */
@ActivityScope
public class SplashPresenter extends BasePresenter<SplashContract.Model, SplashContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public SplashPresenter(SplashContract.Model model, SplashContract.View rootView) {
        super(model, rootView);
    }

    public void getSplashImageInfo(ImageView view) {

        requestPermission(new RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                getUrl(view);
            }

            @Override
            public void onRequestPermissionFailure(List<String> permissions) {
                mRootView.showMessage("拒绝相关权限,软件无法使用.");
                mRootView.killMyself();
                return;
            }

            @Override
            public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {

            }
        }, mRootView.getRxPermissions(), mErrorHandler, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private void getUrl(ImageView view) {
        mModel.getSplashImage(Constant.SPLASH_IMAGE_URL)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<MeiZhiItemData>(mErrorHandler) {
                    @Override
                    public void onNext(MeiZhiItemData meiZhiItemData) {
                        if (!meiZhiItemData.isError()) {
                            mImageLoader.loadImage(mApplication,
                                    ImageConfigImpl
                                            .builder()
                                            .cacheStrategy(0)
                                            .url(meiZhiItemData.getResults().get(0).getUrl())
                                            .imageView(view)
                                            .build());
                            startCountDown();
                        }
                    }
                });
    }

    private void startCountDown() {
        Observable.intervalRange(1, 4, 0, 1, TimeUnit.SECONDS)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<Long>(mErrorHandler) {
                    @Override
                    public void onNext(Long aLong) {
                        Long currentCount = 4 - aLong;
                        if (currentCount == 0L) {
                            ArmsUtils.startActivity(LoginActivity.class);
                            mRootView.killMyself();
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
