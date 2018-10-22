package com.min.bailey.client.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import com.min.bailey.client.mvp.contract.WeatherContract;
import com.min.bailey.client.mvp.model.WeatherModel;


@Module
public class WeatherModule {
    private WeatherContract.View view;

    /**
     * 构建WeatherModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public WeatherModule(WeatherContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    WeatherContract.View provideWeatherView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    WeatherContract.Model provideWeatherModel(WeatherModel model) {
        return model;
    }
}