//package com.min.bailey.client.app.sdk.wechat.share;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//
//import com.min.bailey.client.app.utils.RxImageTool;
//import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
//import com.tencent.mm.opensdk.modelmsg.WXImageObject;
//import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
//import com.tencent.mm.opensdk.modelmsg.WXMusicObject;
//import com.tencent.mm.opensdk.modelmsg.WXTextObject;
//import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
//import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.opensdk.openapi.WXAPIFactory;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//
///**
// * @author Administrator
// */
//public class WechatShareTools {
//
//    /**
//     * 发送到聊天界面——WXSceneSession
//     * 发送到朋友圈——WXSceneTimeline
//     * 添加到微信收藏——WXSceneFavorite
//     */
//
//    private static IWXAPI iwxapi;
//
//    public static void init(Context mContext, String WX_APP_ID) {
//        iwxapi = WXAPIFactory.createWXAPI(mContext, WX_APP_ID, true);
//        iwxapi.registerApp(WX_APP_ID);
//    }
//
//    public static void shareText(String text, SharePlace sharePlace) {
//        WXTextObject wxTextObject = new WXTextObject();
//        wxTextObject.text = text;
//
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = wxTextObject;
//        msg.description = text;
//        Date now = new Date();
//        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//        String s = outFormat.format(now);
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = s;
//
//        req.message = msg;
//        switch (sharePlace) {
//            case Friend:
//                req.scene = SendMessageToWX.Req.WXSceneSession;
//                break;
//            case Zone:
//                req.scene = SendMessageToWX.Req.WXSceneTimeline;
//                break;
//            case Favorites:
//                req.scene = SendMessageToWX.Req.WXSceneFavorite;
//                break;
//            default:
//                break;
//        }
//
//        if (iwxapi != null) {
//            iwxapi.sendReq(req);
//        } else {
//            throw new NullPointerException("请先调用WechatShare.init()方法");
//        }
//    }
//
//    public static void shareImage(Bitmap bitmap, SharePlace sharePlace) {
//        WXImageObject wxImageObject = new WXImageObject(bitmap);
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = wxImageObject;
//
//        Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
//        bitmap.recycle();
//        msg.thumbData = RxImageTool.bitmap2Bytes(bitmap, Bitmap.CompressFormat.PNG);
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        Date now = new Date();
//        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//        String s = outFormat.format(now);
//        req.transaction = s;
//
//        req.message = msg;
//        switch (sharePlace) {
//            case Friend:
//                req.scene = SendMessageToWX.Req.WXSceneSession;
//                break;
//            case Zone:
//                req.scene = SendMessageToWX.Req.WXSceneTimeline;
//                break;
//            case Favorites:
//                req.scene = SendMessageToWX.Req.WXSceneFavorite;
//                break;
//            default:
//                break;
//        }
//
//        if (iwxapi != null) {
//            iwxapi.sendReq(req);
//        } else {
//            throw new NullPointerException("请先调用WechatShare.init()方法");
//        }
//    }
//
//    public static void shareMusic(WechatShareModel shareModel, SharePlace sharePlace) {
//        WXMusicObject wxMusicObject = new WXMusicObject();
//        wxMusicObject.musicUrl = shareModel.getUrl();
//
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = wxMusicObject;
//        msg.title = shareModel.getTitle();
//        msg.description = shareModel.getDescription();
//
//        msg.thumbData = shareModel.getThumbData();
//
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        Date now = new Date();
//        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//        String s = outFormat.format(now);
//        req.transaction = s;
//        req.message = msg;
//
//        switch (sharePlace) {
//            case Friend:
//                req.scene = SendMessageToWX.Req.WXSceneSession;
//                break;
//            case Zone:
//                req.scene = SendMessageToWX.Req.WXSceneTimeline;
//                break;
//            case Favorites:
//                req.scene = SendMessageToWX.Req.WXSceneFavorite;
//                break;
//            default:
//                break;
//        }
//
//        if (iwxapi != null) {
//            iwxapi.sendReq(req);
//        } else {
//            throw new NullPointerException("请先调用WechatShare.init()方法");
//        }
//    }
//
//    public static void shareVideo(WechatShareModel shareModel, SharePlace sharePlace) {
//        WXVideoObject wxVideoObject = new WXVideoObject();
//        wxVideoObject.videoUrl = shareModel.getUrl();
//
//        WXMediaMessage msg = new WXMediaMessage(wxVideoObject);
//        msg.title = shareModel.getTitle();
//        msg.description = shareModel.getDescription();
//
//        msg.thumbData = shareModel.getThumbData();
//
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        Date now = new Date();
//        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//        String s = outFormat.format(now);
//        req.transaction = s;
//        req.message = msg;
//
//        switch (sharePlace) {
//            case Friend:
//                req.scene = SendMessageToWX.Req.WXSceneSession;
//                break;
//            case Zone:
//                req.scene = SendMessageToWX.Req.WXSceneTimeline;
//                break;
//            case Favorites:
//                req.scene = SendMessageToWX.Req.WXSceneFavorite;
//                break;
//            default:
//                break;
//
//        }
//
//        if (iwxapi != null) {
//            iwxapi.sendReq(req);
//        } else {
//            throw new NullPointerException("请先调用WechatShare.init()方法");
//        }
//    }
//
//    public static void shareURL(WechatShareModel shareModel, SharePlace sharePlace) {
//        WXWebpageObject webpageObject = new WXWebpageObject();
//        webpageObject.webpageUrl = shareModel.getUrl();
//
//        WXMediaMessage msg = new WXMediaMessage(webpageObject);
//        msg.title = shareModel.getTitle();
//        msg.description = shareModel.getDescription();
//
//        msg.thumbData = shareModel.getThumbData();
//
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        Date now = new Date();
//        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//        String s = outFormat.format(now);
//        req.transaction = s;
//        req.message = msg;
//
//        switch (sharePlace) {
//            case Friend:
//                req.scene = SendMessageToWX.Req.WXSceneSession;
//                break;
//            case Zone:
//                req.scene = SendMessageToWX.Req.WXSceneTimeline;
//                break;
//            case Favorites:
//                req.scene = SendMessageToWX.Req.WXSceneFavorite;
//                break;
//                default:
//                    break;
//        }
//
//        if (iwxapi != null) {
//            iwxapi.sendReq(req);
//        } else {
//            throw new NullPointerException("请先调用WechatShare.init()方法");
//        }
//    }
//
//    public enum SharePlace {
//        Friend,
//        Zone,
//        Favorites
//    }
//}
