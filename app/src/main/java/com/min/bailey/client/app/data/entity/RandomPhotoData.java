package com.min.bailey.client.app.data.entity;

import com.google.gson.annotations.SerializedName;
import com.min.bailey.client.app.data.api.Api;
import com.min.bailey.client.app.data.api.Constant;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Administrator on 2018/5/4.
 * @describe 随机获取壁纸
 */
@Getter
@Setter
public class RandomPhotoData implements Serializable {

    /*
    msg：响应信息
    res：返回的数据
    vertical：返回的壁纸数据
    preview：壁纸地址
    thumb：小缩略图地址
    img：大缩略图地址
    views：查看数
    cid：所属的类别ID
    rank：点赞数
    tag：壁纸标签
    rule：返回不同大小壁纸规则
    wp：手机版下载地址
    favs：收藏数
    atime：创建时间（单位：秒）
    id：ID
    store：云服务器地址
    desc：描述
    code：返回码
    */

    @SerializedName("msg")
    private String mMsg;
    @SerializedName("res")
    private ResBean mRes;
    @SerializedName("code")
    private int mCode;

    public boolean isSuccess() {
        if (mCode == Api.REQUEST_SUCCESS) {
            return true;
        }
        return false;
    }

    @Getter
    @Setter
    public static class ResBean {
        @SerializedName("vertical")
        private List<VerticalBean> mVertical;

        @Getter
        @Setter
        public static class VerticalBean {

            @SerializedName("preview")
            private String mPreview;
            @SerializedName("thumb")
            private String mThumb;
            @SerializedName("img")
            private String mImg;
            @SerializedName("views")
            private int mViews;
            @SerializedName("ncos")
            private int mNcos;
            @SerializedName("rank")
            private int mRank;
            @SerializedName("rule")
            private String mRule;
            @SerializedName("wp")
            private String mWp;
            @SerializedName("xr")
            private boolean mXr;
            @SerializedName("cr")
            private boolean mCr;
            @SerializedName("favs")
            private int mFavs;
            @SerializedName("atime")
            private int mAtime;
            @SerializedName("id")
            private String mId;
            @SerializedName("store")
            private String mStore;
            @SerializedName("desc")
            private String mDesc;
            @SerializedName("cid")
            private List<String> mCid;
            @SerializedName("url")
            private List<?> mUrl;
            @SerializedName("tag")
            private List<String> mTag;

        }
    }
}
