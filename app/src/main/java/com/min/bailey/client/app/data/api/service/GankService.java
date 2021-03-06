package com.min.bailey.client.app.data.api.service;

import com.min.bailey.client.app.data.api.Constant;
import com.min.bailey.client.app.data.entity.MeiZhiItemData;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Url;

import static com.min.bailey.client.app.data.api.Api.MEIZHI_DOMAIN_NAME;
import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

/**
 * @author Administrator on 2018/5/7.
 * @describe MeiZhi 哟哟哟
 */
public interface GankService {

    /**
     * 妹纸列表
     *
     * @param num
     * @param page
     * @return
     */
    @Headers({DOMAIN_NAME_HEADER + MEIZHI_DOMAIN_NAME})
    @GET("/api/data/福利/{num}/{page}")
    Observable<MeiZhiItemData> getGirlList(@Path("num") int num, @Path("page") int page);

    /**
     * 获取启动页图片
     *
     * @param url
     * @return
     */
    @GET
    Observable<MeiZhiItemData> getSplashImage(@Url String url);

    /**
     * 下载图片
     *
     * @param url
     * @return
     */
    @GET
    Observable<ResponseBody> download(@Url String url);
}
