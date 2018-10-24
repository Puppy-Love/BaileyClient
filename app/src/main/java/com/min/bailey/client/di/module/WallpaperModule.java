package com.min.bailey.client.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.min.bailey.client.mvp.contract.WallpaperContract;
import com.min.bailey.client.mvp.model.WallpaperModel;


@Module
public class WallpaperModule {
    private WallpaperContract.View view;

    /**
     * 构建WallpaperModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public WallpaperModule(WallpaperContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    WallpaperContract.View provideWallpaperView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    WallpaperContract.Model provideWallpaperModel(WallpaperModel model) {
        return model;
    }
}