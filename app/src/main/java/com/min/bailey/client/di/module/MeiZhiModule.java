package com.min.bailey.client.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.min.bailey.client.mvp.contract.MeiZhiContract;
import com.min.bailey.client.mvp.model.MeiZhiModel;


@Module
public class MeiZhiModule {
    private MeiZhiContract.View view;

    /**
     * 构建MeiZhiModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MeiZhiModule(MeiZhiContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MeiZhiContract.View provideMeiZhiView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MeiZhiContract.Model provideMeiZhiModel(MeiZhiModel model) {
        return model;
    }
}