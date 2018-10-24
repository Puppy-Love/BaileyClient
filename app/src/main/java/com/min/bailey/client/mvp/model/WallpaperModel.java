package com.min.bailey.client.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.min.bailey.client.app.data.api.service.GirlService;
import com.min.bailey.client.app.data.entity.RandomPhotoData;
import com.min.bailey.client.mvp.contract.WallpaperContract;

import io.reactivex.Observable;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

import static com.min.bailey.client.app.data.api.Api.WALLPAPER_DOMAIN;
import static com.min.bailey.client.app.data.api.Api.WALLPAPER_DOMAIN_NAME;


@ActivityScope
public class WallpaperModel extends BaseModel implements WallpaperContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public WallpaperModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
        RetrofitUrlManager.getInstance().putDomain(WALLPAPER_DOMAIN_NAME, WALLPAPER_DOMAIN);
    }

    @Override
    public Observable<RandomPhotoData> getWallpaperBySort(String id, int limit, boolean adult, int first, String order) {
        return mRepositoryManager.obtainRetrofitService(GirlService.class)
                .getWallpaperBySort(id, limit, adult, first, order);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}