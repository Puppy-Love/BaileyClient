package com.min.bailey.client.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.min.bailey.client.app.data.api.service.GirlService;
import com.min.bailey.client.app.data.entity.WallpaperSortData;
import com.min.bailey.client.mvp.contract.WallpaperSortContract;

import io.reactivex.Observable;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

import static com.min.bailey.client.app.data.api.Api.WALLPAPER_DOMAIN;
import static com.min.bailey.client.app.data.api.Api.WALLPAPER_DOMAIN_NAME;


@ActivityScope
public class WallpaperSortModel extends BaseModel implements WallpaperSortContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public WallpaperSortModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
        RetrofitUrlManager.getInstance().putDomain(WALLPAPER_DOMAIN_NAME, WALLPAPER_DOMAIN);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<WallpaperSortData> getSort(boolean adult, int first) {
        return mRepositoryManager
                .obtainRetrofitService(GirlService.class)
                .getWallpaperSort(adult, first);
    }
}