package com.min.bailey.client.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.min.bailey.client.app.data.api.Api;
import com.min.bailey.client.app.data.api.service.GirlService;
import com.min.bailey.client.app.data.entity.RandomPhotoData;
import com.min.bailey.client.mvp.contract.GirlItemContract;

import io.reactivex.Observable;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

import static com.min.bailey.client.app.data.api.Api.WALLPAPER_DOMAIN;
import static com.min.bailey.client.app.data.api.Api.WALLPAPER_DOMAIN_NAME;


/**
 * @author Administrator
 */
@ActivityScope
public class GirlItemModel extends BaseModel implements GirlItemContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public GirlItemModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
        //使用 RetrofitUrlManager 切换 BaseUrl
        RetrofitUrlManager.getInstance().putDomain(WALLPAPER_DOMAIN_NAME, WALLPAPER_DOMAIN);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }


    @Override
    public Observable<RandomPhotoData> getRandomPhoto(int limit, int first, int skip, String order, boolean adult) {
        return mRepositoryManager.obtainRetrofitService(GirlService.class).getRandomPhoto(limit, adult, first, skip, order);
    }
}