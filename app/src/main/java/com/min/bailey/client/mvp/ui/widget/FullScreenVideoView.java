package com.min.bailey.client.mvp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Author: Bailey
 * Email: 553258697@qq.com
 * Create Time: 2018/7/19
 * Description: 全屏播放
 */
public class FullScreenVideoView extends VideoView {
    public FullScreenVideoView(Context context) {
        super(context);
    }

    public FullScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 实现全屏,重新计算高度和宽度
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
