package com.min.bailey.client.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.min.bailey.client.di.module.PreviewPicModule;

import com.jess.arms.di.scope.ActivityScope;
import com.min.bailey.client.mvp.ui.activity.PreviewPicActivity;

@ActivityScope
@Component(modules = PreviewPicModule.class, dependencies = AppComponent.class)
public interface PreviewPicComponent {
    void inject(PreviewPicActivity activity);
}