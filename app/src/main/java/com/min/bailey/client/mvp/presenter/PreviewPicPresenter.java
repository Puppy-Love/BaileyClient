package com.min.bailey.client.mvp.presenter;

import android.app.Application;

import com.github.chrisbanes.photoview.PhotoView;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.min.bailey.client.R;
import com.min.bailey.client.mvp.contract.PreviewPicContract;


@ActivityScope
public class PreviewPicPresenter extends BasePresenter<PreviewPicContract.Model, PreviewPicContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public PreviewPicPresenter(PreviewPicContract.Model model, PreviewPicContract.View rootView) {
        super(model, rootView);
    }

    public void setImg(PhotoView photoView, String url) {
        mImageLoader.loadImage(mApplication,
                ImageConfigImpl
                        .builder()
                        .url(url)
                        .cacheStrategy(0)
                        .isCenterCrop(true)
                        .imageView(photoView)
                        .build());
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
