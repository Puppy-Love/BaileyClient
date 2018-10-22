package com.min.bailey.client.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.min.bailey.client.app.data.api.service.GankService;
import com.min.bailey.client.app.data.entity.MeiZhiItemData;
import com.min.bailey.client.mvp.contract.SplashContract;

import io.reactivex.Observable;


@ActivityScope
public class SplashModel extends BaseModel implements SplashContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public SplashModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<MeiZhiItemData> getSplashImage(String url) {
        return mRepositoryManager.obtainRetrofitService(GankService.class)
                .getSplashImage(url);
    }
}