package com.min.bailey.client.app.config.applyOptions.intercept;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加拦截器？嗯哼，添加header
 *
 * @author Administrator
 */
public class UserAgentInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request requestWithUserAgent = originalRequest.newBuilder()
//                .removeHeader("User-Agent")
//                .addHeader("device-type", "android")
//                .addHeader("token", SPUtils.getInstance(mContext, Constant.SP_TOKEN).getString(Constant.TOKEN_KEY))
                .build();
        return chain.proceed(requestWithUserAgent);
    }

}