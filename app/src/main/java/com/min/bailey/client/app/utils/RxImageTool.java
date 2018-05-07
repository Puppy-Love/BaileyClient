package com.min.bailey.client.app.utils;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * @author Administrator on 2018/4/20.
 * @describe
 */
public class RxImageTool {

    /**
     * bitmap转byteArr
     *
     * @param bitmap bitmap对象
     * @param format 格式
     * @return 字节数组
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap, Bitmap.CompressFormat format) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(format, 100, baos);
        return baos.toByteArray();
    }
}
