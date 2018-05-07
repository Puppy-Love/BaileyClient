package com.min.bailey.client.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.min.bailey.client.mvp.contract.GirlItemContract;
import com.min.bailey.client.mvp.model.GirlItemModel;


@Module
public class GirlItemModule {
    private GirlItemContract.View view;

    /**
     * 构建GirlItemModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public GirlItemModule(GirlItemContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    GirlItemContract.View provideGirlItemView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    GirlItemContract.Model provideGirlItemModel(GirlItemModel model) {
        return model;
    }
}