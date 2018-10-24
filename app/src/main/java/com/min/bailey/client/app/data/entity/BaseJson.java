package com.min.bailey.client.app.data.entity;

import com.google.gson.annotations.SerializedName;
import com.min.bailey.client.app.data.api.Api;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Administrator on 2018/4/8.
 * @describe 数据返回基类
 */
@Getter
@Setter
public class BaseJson<T> {

    @SerializedName("code")
    private int mCode;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("data")
    private T mData;

    /**
     * 请求是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        if (mCode == Api.REQUEST_SUCCESS) {
            return true;
        } else {
            return false;
        }
    }


}
