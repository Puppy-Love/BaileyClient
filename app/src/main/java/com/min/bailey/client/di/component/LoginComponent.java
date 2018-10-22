package com.min.bailey.client.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.min.bailey.client.di.module.LoginModule;

import com.jess.arms.di.scope.ActivityScope;
import com.min.bailey.client.mvp.ui.activity.LoginActivity;

@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}