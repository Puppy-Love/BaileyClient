package com.min.bailey.client.di.component;


import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.min.bailey.client.di.module.MainTabModule;
import com.min.bailey.client.mvp.ui.activity.MainTabActivity;

import dagger.Component;

@ActivityScope
@Component(modules = MainTabModule.class, dependencies = AppComponent.class)
public interface MainTabComponent {
    void inject(MainTabActivity activity);
}