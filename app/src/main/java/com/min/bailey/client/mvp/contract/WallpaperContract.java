package com.min.bailey.client.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;
import com.min.bailey.client.app.data.entity.RandomPhotoData;
import com.min.bailey.client.mvp.ui.adapter.GirlItemAdapter;

import io.reactivex.Observable;


public interface WallpaperContract {
    interface View extends IView {
        /**
         * 设置适配器
         *
         * @param adapter
         */
        void setAdapter(GirlItemAdapter adapter);

        /**
         * 加载完成
         *
         * @param adapter
         */
        void onLoadMoreComplete(GirlItemAdapter adapter);

        /**
         * 加载更多错误
         *
         * @param adapter
         */
        void onLoadMoreError(GirlItemAdapter adapter);

        /**
         * 加载结束
         *
         * @param adapter
         */
        void onLoadMoreEnd(GirlItemAdapter adapter);
    }

    interface Model extends IModel {
        /**
         * 根据id获取壁纸
         *
         * @param id
         * @param limit
         * @param first
         * @param order
         * @param adult
         * @return
         */
        Observable<RandomPhotoData> getWallpaperBySort(String id, int limit, boolean adult, int first, String order);
    }
}
