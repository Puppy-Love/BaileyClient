package com.min.bailey.client.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.min.bailey.client.R;
import com.min.bailey.client.app.data.entity.RandomPhotoData;

import java.util.List;


/**
 * 豆瓣美女
 *
 * @author Administrator
 */
public class GirlItemAdapter extends BaseQuickAdapter<RandomPhotoData.ResBean.VerticalBean, BaseViewHolder> {

    public GirlItemAdapter(@Nullable List<RandomPhotoData.ResBean.VerticalBean> data) {
        super(R.layout.item_girl_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RandomPhotoData.ResBean.VerticalBean item) {
        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader().loadImage(mContext, ImageConfigImpl
                .builder()
                .url(item.getThumb())
                .cacheStrategy(0)
                .isCenterCrop(true)
                .imageView(helper.getView(R.id.girl_item_iv))
                .build());
    }
}
