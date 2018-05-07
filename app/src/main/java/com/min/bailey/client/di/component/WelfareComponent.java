package com.min.bailey.client.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.min.bailey.client.di.module.WelfareModule;

import com.min.bailey.client.mvp.ui.fragment.WelfareFragment;

@ActivityScope
@Component(modules = WelfareModule.class, dependencies = AppComponent.class)
public interface WelfareComponent {
    void inject(WelfareFragment fragment);
}