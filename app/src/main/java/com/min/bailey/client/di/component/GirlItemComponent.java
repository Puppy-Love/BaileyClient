package com.min.bailey.client.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.min.bailey.client.di.module.GirlItemModule;

import com.min.bailey.client.mvp.ui.fragment.GirlItemFragment;

@ActivityScope
@Component(modules = GirlItemModule.class, dependencies = AppComponent.class)
public interface GirlItemComponent {
    void inject(GirlItemFragment fragment);
}