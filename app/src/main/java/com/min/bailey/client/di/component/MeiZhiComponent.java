package com.min.bailey.client.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.min.bailey.client.di.module.MeiZhiModule;

import com.min.bailey.client.mvp.ui.fragment.MeiZhiFragment;

@ActivityScope
@Component(modules = MeiZhiModule.class, dependencies = AppComponent.class)
public interface MeiZhiComponent {
    void inject(MeiZhiFragment fragment);
}