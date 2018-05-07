package com.min.bailey.client.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.min.bailey.client.app.data.api.service.GankService;
import com.min.bailey.client.app.data.entity.MeiZhiItemData;
import com.min.bailey.client.mvp.contract.MeiZhiContract;

import io.reactivex.Observable;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

import static com.min.bailey.client.app.data.api.Api.MEIZHI_DOMAIN;
import static com.min.bailey.client.app.data.api.Api.MEIZHI_DOMAIN_NAME;
import static com.min.bailey.client.app.data.api.Api.WALLPAPER_DOMAIN;
import static com.min.bailey.client.app.data.api.Api.WALLPAPER_DOMAIN_NAME;


/**
 * @author Administrator
 */
@ActivityScope
public class MeiZhiModel extends BaseModel implements MeiZhiContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public MeiZhiModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
        RetrofitUrlManager.getInstance().putDomain(MEIZHI_DOMAIN_NAME, MEIZHI_DOMAIN);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<MeiZhiItemData> getGirlList(int num, int page) {
        return mRepositoryManager
                .obtainRetrofitService(GankService.class)
                .getGirlList(num, page);
    }
}