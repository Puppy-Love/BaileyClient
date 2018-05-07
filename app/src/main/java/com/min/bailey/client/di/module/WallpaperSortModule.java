package com.min.bailey.client.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.min.bailey.client.mvp.contract.WallpaperSortContract;
import com.min.bailey.client.mvp.model.WallpaperSortModel;


@Module
public class WallpaperSortModule {
    private WallpaperSortContract.View view;

    /**
     * 构建WallpaperSortModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public WallpaperSortModule(WallpaperSortContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    WallpaperSortContract.View provideWallpaperSortView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    WallpaperSortContract.Model provideWallpaperSortModel(WallpaperSortModel model) {
        return model;
    }
}