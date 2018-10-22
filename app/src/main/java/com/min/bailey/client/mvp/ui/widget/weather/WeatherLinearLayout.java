package com.min.bailey.client.mvp.ui.widget.weather;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;

/**
 * @author: Bailey
 * Email: 553258697@qq.com
 * Create Time: 2018/10/22
 * Description:
 */
public class WeatherLinearLayout extends LinearLayout {

    public WeatherLinearLayout(Context context) {
        super(context);
    }

    public WeatherLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WeatherLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (getChildCount() >= 2) {
            ViewParent parent = getParent();
            if (parent != null) {
                final int height = ((ViewGroup) parent).getMeasuredHeight();
                if (height > 0) {
                    final View firstChild = getChildAt(0);
                    LayoutParams firstParams = (LayoutParams) firstChild.getLayoutParams();
                    firstParams.height = height * 4 / 5;
                    firstChild.setLayoutParams(firstParams);
                    final View secondChild = getChildAt(1);
                    LayoutParams secondParams = (LayoutParams) secondChild.getLayoutParams();
                    secondParams.height = height * 1 / 5;
                    secondChild.setLayoutParams(secondParams);
                }
            }
        }
    }
}
