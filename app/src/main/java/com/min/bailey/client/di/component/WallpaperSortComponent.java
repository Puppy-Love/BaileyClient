package com.min.bailey.client.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.min.bailey.client.di.module.WallpaperSortModule;

import com.min.bailey.client.mvp.ui.fragment.WallpaperSortFragment;

@ActivityScope
@Component(modules = WallpaperSortModule.class, dependencies = AppComponent.class)
public interface WallpaperSortComponent {
    void inject(WallpaperSortFragment fragment);
}