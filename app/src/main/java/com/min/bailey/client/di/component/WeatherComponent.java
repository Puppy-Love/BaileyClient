package com.min.bailey.client.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.min.bailey.client.di.module.WeatherModule;

import com.jess.arms.di.scope.FragmentScope;
import com.min.bailey.client.mvp.ui.fragment.WeatherFragment;

@FragmentScope
@Component(modules = WeatherModule.class, dependencies = AppComponent.class)
public interface WeatherComponent {
    void inject(WeatherFragment fragment);
}