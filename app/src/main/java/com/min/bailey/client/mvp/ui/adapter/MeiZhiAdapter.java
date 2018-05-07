package com.min.bailey.client.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.min.bailey.client.R;
import com.min.bailey.client.app.data.entity.MeiZhiItemData;
import com.min.bailey.client.app.data.entity.RandomPhotoData;

import java.util.List;


/**
 * 妹纸
 *
 * @author Administrator
 */
public class MeiZhiAdapter extends BaseQuickAdapter<MeiZhiItemData.ResultsBean, BaseViewHolder> {
    /**
     * @describe 用于加载图片的管理类, 默认使用glide, 使用策略模式, 可替换框架
     */
    private ImageLoader mImageLoader;

    public MeiZhiAdapter(ImageLoader imageLoader, @Nullable List<MeiZhiItemData.ResultsBean> data) {
        super(R.layout.item_girl_layout, data);
        this.mImageLoader = imageLoader;
    }

    @Override
    protected void convert(BaseViewHolder helper, MeiZhiItemData.ResultsBean item) {
        mImageLoader.loadImage(mContext,
                ImageConfigImpl
                        .builder()
                        .url(item.getUrl())
                        .cacheStrategy(0)
                        .isCenterCrop(true)
                        .imageView(helper.getView(R.id.girl_item_iv))
                        .build());
    }
}
