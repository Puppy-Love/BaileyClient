package com.min.bailey.client.app.data.api.service;

import com.min.bailey.client.app.data.entity.RandomPhotoData;
import com.min.bailey.client.app.data.entity.WallpaperSortData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.min.bailey.client.app.data.api.Api.WALLPAPER_DOMAIN_NAME;
import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

/**
 * @author Administrator on 2018/4/18.
 * @describe 福利美女
 */
public interface GirlService {

    /**
     * 获取美女图片
     *
     * @param limit 返回数量
     * @param adult 布尔值，暂时未知
     * @param first 数字，如1
     * @param skip  略过数量
     * @param order 值 hot为favs， new
     * @return
     */
    @Headers({DOMAIN_NAME_HEADER + WALLPAPER_DOMAIN_NAME})
    @GET("/v1/vertical/vertical")
    Observable<RandomPhotoData> getRandomPhoto(@Query("limit") int limit,
                                               @Query("adult") boolean adult,
                                               @Query("first") int first,
                                               @Query("skip") int skip,
                                               @Query("order") String order);

    /**
     * 壁纸
     *
     * @param adult
     * @param first
     * @return
     */
    @Headers({DOMAIN_NAME_HEADER + WALLPAPER_DOMAIN_NAME})
    @GET("/v1/vertical/category")
    Observable<WallpaperSortData> getWallpaperSort(@Query("adult") boolean adult,
                                                   @Query("first") int first);

    /**
     * 根据分类获取相关壁纸
     *
     * @param id    分类id
     * @param limit 返回数量
     * @param adult 布尔值，暂时未知
     * @param first 数字，如1
     * @param order 值 hot为favs， new
     * @return
     */
    @Headers({DOMAIN_NAME_HEADER + WALLPAPER_DOMAIN_NAME})
    @GET("/v1/vertical/category/{id}/vertical")
    Observable<RandomPhotoData> getWallpaperBySort(@Path("id") String id,
                                                       @Query("limit") int limit,
                                                       @Query("adult") boolean adult,
                                                       @Query("first") int first,
                                                       @Query("order") String order);
}
