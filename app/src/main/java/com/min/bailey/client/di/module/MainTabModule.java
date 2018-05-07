package com.min.bailey.client.di.module;


import com.jess.arms.di.scope.ActivityScope;
import com.min.bailey.client.mvp.contract.MainTabContract;
import com.min.bailey.client.mvp.model.MainTabModel;

import dagger.Module;
import dagger.Provides;


@Module
public class MainTabModule {
    private MainTabContract.View view;

    /**
     * 构建MainTabModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MainTabModule(MainTabContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MainTabContract.View provideMainTabView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MainTabContract.Model provideMainTabModel(MainTabModel model) {
        return model;
    }
}