package com.min.bailey.client.app.config.applyOptions;

import android.content.Context;

import com.min.bailey.client.BuildConfig;
import com.min.bailey.client.app.config.applyOptions.intercept.LoggingInterceptor;
import com.min.bailey.client.app.config.applyOptions.intercept.UserAgentInterceptor;
import com.jess.arms.di.module.ClientModule;


import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


/**
 * @author Administrator
 */
public class MyRetrofitConfiguration implements ClientModule.RetrofitConfiguration {
    @Override
    public void configRetrofit(Context context, Retrofit.Builder builder) {
        // 配置多BaseUrl支持
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            //使用自定义的Log拦截器
            clientBuilder.addInterceptor(new LoggingInterceptor());
        }
        //使用自定义heard
//        clientBuilder.addInterceptor(new UserAgentInterceptor(context));
        builder.client(RetrofitUrlManager.getInstance()
                .with(clientBuilder)
                .build());
    }
}
