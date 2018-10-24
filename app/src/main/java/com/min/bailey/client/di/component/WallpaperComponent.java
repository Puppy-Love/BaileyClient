package com.min.bailey.client.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.min.bailey.client.di.module.WallpaperModule;

import com.jess.arms.di.scope.ActivityScope;
import com.min.bailey.client.mvp.ui.activity.WallpaperActivity;

@ActivityScope
@Component(modules = WallpaperModule.class, dependencies = AppComponent.class)
public interface WallpaperComponent {
    void inject(WallpaperActivity activity);
}