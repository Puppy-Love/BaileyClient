package com.min.bailey.client.app.utils;

import com.min.bailey.client.R;
import com.min.bailey.client.app.AppLifecyclesImpl;

/**
 * @author: Bailey
 * Email: 553258697@qq.com
 * Description:
 */
public class SettingsUtil {

    public static final String WEATHER_SRC = "weather_src";//天气源
    public static final String WEATHER_SHARE_TYPE = "weather_share_type";//天气分享形式
    public static final String WEATHER_KEY = "weather_key";//天气 key
    public static final String WEATHER_DATE_TYPE = "weather_date_type";//天气日期显示样式，日期 or 星期
    public static final String THEME = "theme_color";//主题
    public static final String CLEAR_CACHE = "clean_cache";//清空缓存

    public static final int WEATHER_DATE_TYPE_WEEK = 0;

    public static final int WEATHER_DATE_TYPE_DATE = 1;

    public static final int WEATHER_SRC_HEFENG = 0;

    public static final int WEATHER_SRC_XIAOMI = 1;

    public static void setWeatherShareType(String type) {
        SPUtils.getInstance(AppLifecyclesImpl.getContext()).put(WEATHER_SHARE_TYPE, type);
    }

    public static String getWeatherShareType() {
        return SPUtils.getInstance(AppLifecyclesImpl.getContext()).getString(WEATHER_SHARE_TYPE, AppLifecyclesImpl.getContext().getResources().getStringArray(R.array.share_type)[0]);
    }

    public static void setWeatherSrc(int src) {
        SPUtils.getInstance(AppLifecyclesImpl.getContext()).put(WEATHER_SRC, src);
    }

    public static int getWeatherSrc() {
        return SPUtils.getInstance(AppLifecyclesImpl.getContext()).getInt(WEATHER_SRC, WEATHER_SRC_HEFENG);
    }

    public static void setWeatherDateType(int type) {
        SPUtils.getInstance(AppLifecyclesImpl.getContext()).put( WEATHER_DATE_TYPE, type);
    }

    public static int getWeatherDateType() {
        return  SPUtils.getInstance(AppLifecyclesImpl.getContext()).getInt( WEATHER_DATE_TYPE, 0);
    }

    public static void setWeatherKey(String key) {
        SPUtils.getInstance(AppLifecyclesImpl.getContext()).put(WEATHER_KEY, key);
    }

    public static String getWeatherKey() {
        return SPUtils.getInstance(AppLifecyclesImpl.getContext()).getString(WEATHER_KEY, "");
    }

    public static void setTheme(int themeIndex) {
        SPUtils.getInstance(AppLifecyclesImpl.getContext()).put(THEME, themeIndex);
    }

    public static int getTheme() {
        return SPUtils.getInstance(AppLifecyclesImpl.getContext()).getInt(THEME, 0);
    }

}
