package com.min.bailey.client.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;
import com.min.bailey.client.app.data.entity.WallpaperSortData;
import com.min.bailey.client.mvp.ui.adapter.MeiZhiAdapter;
import com.min.bailey.client.mvp.ui.adapter.WallpaperSortAdapter;

import io.reactivex.Observable;


/**
 * @author Administrator
 */
public interface WallpaperSortContract {
    interface View extends IView {
        /**
         * 设置适配器
         *
         * @param adapter
         */
        void setAdapter(WallpaperSortAdapter adapter);
    }

    interface Model extends IModel {
        /**
         * 获取壁纸分类
         *
         * @return
         */
        Observable<WallpaperSortData> getSort(boolean adult, int first);
    }
}
