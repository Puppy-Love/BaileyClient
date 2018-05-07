package com.min.bailey.client.app.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import com.min.bailey.client.app.utils.ObjectUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.realm.Realm;

/**
 * @author Administrator
 */
@SuppressLint("OverrideAbstract")
public class
PayCompleteService extends NotificationListenerService {

    private static final String WE_CHAT_NOTIFICATION_TIP = "微信支付收款元";
    private static final String ALI_PAY_NOTIFICATION_TIP = "通过扫码向你付款元";
    private final String WE_CHAT_PACKAGE_NAME = "com.tencent.mm";
    private final String ALI_PAY_PACKAGE_NAME = "com.eg.android.AlipayGphone";
    /**
     * 1 微信 2 支付宝
     */
    private String type;
    private Realm mRealm;

    public PayCompleteService() {

    }

    @SuppressLint("NewApi")
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        if (ObjectUtils.isEmpty(mRealm)){
            mRealm = Realm.getDefaultInstance();
        }
        Notification notification = sbn.getNotification();
        String pkg = sbn.getPackageName();
        String content = notification.extras.getString(Notification.EXTRA_TEXT);
        if (pkg.equals(WE_CHAT_PACKAGE_NAME)) {
            type = "1";
            parseData(content);
        } else if (pkg.equals(ALI_PAY_PACKAGE_NAME)) {
            type = "2";
            parseData(content);
        }
    }

    /**
     * 解析通知數據
     *
     * @param content
     */
    private void parseData(String content) {
        String mInt = "\\d*[.]\\d*";
//            String mFloat = "[1-9]+[0-9]*";
        Pattern r = Pattern.compile(mInt);
        Matcher m = r.matcher(content);
        while (m.find()) {
            if (!"".equals(m.group())) {
                String money = m.group();
                String mNewStr = m.replaceAll("");
                if (mNewStr.contains(WE_CHAT_NOTIFICATION_TIP) || mNewStr.contains(ALI_PAY_NOTIFICATION_TIP)) {
                }
            }
        }

    }


    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mRealm = Realm.getDefaultInstance();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (ObjectUtils.isNotEmpty(mRealm)) {
            mRealm.close();
        }
    }
}
