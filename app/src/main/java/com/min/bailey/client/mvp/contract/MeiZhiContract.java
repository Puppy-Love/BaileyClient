package com.min.bailey.client.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;
import com.min.bailey.client.app.data.entity.MeiZhiItemData;
import com.min.bailey.client.mvp.ui.adapter.MeiZhiAdapter;

import java.util.List;

import io.reactivex.Observable;


public interface MeiZhiContract {
    interface View extends IView {
        /**
         * 设置适配器
         *
         * @param adapter
         */
        void setAdapter(MeiZhiAdapter adapter);

        /**
         * 加载完成
         *
         * @param adapter
         */
        void onLoadMoreComplete(MeiZhiAdapter adapter);

        /**
         * 加载更多错误
         *
         * @param adapter
         */
        void onLoadMoreError(MeiZhiAdapter adapter);

        /**
         * 加载结束
         *
         * @param adapter
         */
        void onLoadMoreEnd(MeiZhiAdapter adapter);
    }

    /**
     *
     */
    interface Model extends IModel {
        /**
         * @param num
         * @param page
         * @return
         */
        Observable<MeiZhiItemData> getGirlList(int num, int page);
    }
}
