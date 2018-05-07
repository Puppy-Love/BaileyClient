package com.min.bailey.client.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;
import com.min.bailey.client.app.data.entity.RandomPhotoData;
import com.min.bailey.client.mvp.ui.adapter.GirlItemAdapter;

import io.reactivex.Observable;


/**
 * 豆瓣美女
 *
 * @author Administrator
 */
public interface GirlItemContract {
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
         * 随机获取
         *
         * @param limit
         * @param first
         * @param skip
         * @param order
         * @param adult
         * @return
         */
        Observable<RandomPhotoData> getRandomPhoto(int limit, int first, int skip, String order, boolean adult);
    }
}
