package com.min.bailey.client.mvp.ui.widget.weather.dynamic;

import android.content.Context;
import android.graphics.Canvas;

/**
 * @author: Bailey
 * Email: 553258697@qq.com
 * Description:
 */
public interface WeatherHandler {
    void onDrawElements(Canvas canvas);

    void onSizeChanged(Context context, int width, int height);
}
