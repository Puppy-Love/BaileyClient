package com.min.bailey.client.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.min.bailey.client.mvp.contract.PreviewPicContract;
import com.min.bailey.client.mvp.model.PreviewPicModel;


@Module
public class PreviewPicModule {
    private PreviewPicContract.View view;

    /**
     * 构建PreviewPicModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public PreviewPicModule(PreviewPicContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PreviewPicContract.View providePreviewPicView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PreviewPicContract.Model providePreviewPicModel(PreviewPicModel model) {
        return model;
    }
}