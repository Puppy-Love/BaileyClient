package com.min.bailey.client.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.min.bailey.client.R;
import com.min.bailey.client.app.data.entity.MeiZhiItemData;
import com.min.bailey.client.app.data.entity.WallpaperSortData;

import java.util.List;


/**
 * 妹纸
 *
 * @author Administrator
 */
public class WallpaperSortAdapter extends BaseQuickAdapter<WallpaperSortData.ResBean.CategoryBean, BaseViewHolder> {
    /**
     * @describe 用于加载图片的管理类, 默认使用glide, 使用策略模式, 可替换框架
     */
    private ImageLoader mImageLoader;

    public WallpaperSortAdapter(ImageLoader imageLoader, @Nullable List<WallpaperSortData.ResBean.CategoryBean> data) {
        super(R.layout.item_wallpaper_sort, data);
        this.mImageLoader = imageLoader;
    }

    @Override
    protected void convert(BaseViewHolder helper, WallpaperSortData.ResBean.CategoryBean item) {
        mImageLoader.loadImage(mContext,
                ImageConfigImpl
                        .builder()
                        .url(item.getCover())
                        .cacheStrategy(0)
                        .isCenterCrop(true)
                        .imageView(helper.getView(R.id.wallpaper_sort_item_iv))
                        .build());
        helper.setText(R.id.wallpaper_sort_item_tv, item.getName());
    }
}
