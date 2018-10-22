package com.min.bailey.client.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.min.bailey.client.di.module.MainModule;

import com.jess.arms.di.scope.ActivityScope;
import com.min.bailey.client.mvp.ui.activity.MainActivity;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}