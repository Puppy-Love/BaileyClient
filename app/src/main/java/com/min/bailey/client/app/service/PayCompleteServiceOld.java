//package com.min.bailey.client.app.service;
//
//import android.accessibilityservice.AccessibilityService;
//import android.content.ComponentName;
//import android.content.pm.PackageManager;
//import android.view.accessibility.AccessibilityEvent;
//import android.view.accessibility.AccessibilityNodeInfo;
//
//import com.min.bailey.client.app.data.api.Constant;
//import com.min.bailey.client.app.data.entity.BaseJson;
//import com.min.bailey.client.app.utils.ObjectUtils;
//import com.min.bailey.client.app.utils.SPUtils;
//import com.jess.arms.utils.ArmsUtils;
//import com.jess.arms.utils.LogUtils;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.schedulers.Schedulers;
//import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
//import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
//
///**
// * 监听收款成功后的微信通知
// *
// * @author Administrator
// */
//public class PayCompleteServiceOld extends AccessibilityService {
//    private static final String TAG = "PayCompleteServiceOld";
//
//    /**
//     * <p>
//     * com.tencent.mm/.plugin.luckymoney.ui.En_fba4b94f
//     * com.tencent.mm/com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI
//     * </P>
//     *
//     * @describe
//     */
//    private static final String WE_CHAT_LUCKMONEY_RECEIVE_ACTIVITY = ".plugin.luckymoney.ui";
//    private static final String WE_CHAT_LUCKMONEY_DETAIL_ACTIVITY = "LuckyMoneyDetailUI";
//    private static final String WE_CHAT_LUCKMONEY_GENERAL_ACTIVITY = "LauncherUI";
//    private static final String WE_CHAT_LUCKMONEY_CHATTING_ACTIVITY = "ChattingUI";
//    private static final String WE_CHAT_NOTIFICATION_TIP = "[微信支付:微信支付收款元]";
//    private String currentActivityName = WE_CHAT_LUCKMONEY_GENERAL_ACTIVITY;
//    private String money;
//    private String matchStr;
//    private String type;
//
//    public PayCompleteServiceOld() {
//
//    }
//
//    @Override
//    public void onAccessibilityEvent(AccessibilityEvent event) {
//        setCurrentActivityName(event);
//        if (watchNotifications(event)) {
//            type = "1";
//            String mToken = SPUtils.getInstance(this, Constant.SP_TOKEN).getString(Constant.TOKEN_KEY);
//            String mRole = SPUtils.getInstance(this, Constant.ACCOUNT_INFO).getString(Constant.ACCOUNT_ROLE);
//            // 登陆状态，并且是财务账号
//            if (ObjectUtils.isNotEmpty(mToken) && mRole.equals(Constant.FINANCIAL)) {
//                try {
//                    ArmsUtils.obtainAppComponentFromContext(this)
//                            .repositoryManager()
//                            .obtainRetrofitService(QrPayService.class).confirmOrder(new ConfirmOrderBean(type, money))
//                            .subscribeOn(Schedulers.io())
//                            .retryWhen(new RetryWithDelay(3, 2))
//                            .subscribeOn(AndroidSchedulers.mainThread())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new ErrorHandleSubscriber<BaseJson>(ArmsUtils.obtainAppComponentFromContext(this).rxErrorHandler()) {
//                                @Override
//                                public void onNext(BaseJson data) {
//                                    if (data.isSuccess()) {
//                                        LogUtils.debugInfo(data.getMsg());
//                                    } else {
//                                        LogUtils.debugInfo(data.getMsg());
//                                    }
//                                }
//                            });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//            } else {
//                ArmsUtils.startActivity(LoginActivity.class);
//            }
//            return;
//        }
//    }
//
//    @Override
//    public void onInterrupt() {
//
//    }
//
//
//    /**
//     * 设置当前的活动名
//     *
//     * @param event
//     */
//    private void setCurrentActivityName(AccessibilityEvent event) {
//        if (event.getEventType() != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
//            return;
//        }
//
//        try {
//            ComponentName componentName = new ComponentName(
//                    event.getPackageName().toString(),
//                    event.getClassName().toString()
//            );
//
//            getPackageManager().getActivityInfo(componentName, 0);
//            currentActivityName = componentName.flattenToShortString();
//        } catch (PackageManager.NameNotFoundException e) {
//            currentActivityName = WE_CHAT_LUCKMONEY_GENERAL_ACTIVITY;
//        }
//    }
//
//
//    private boolean watchNotifications(AccessibilityEvent event) {
//        // 不是通知消息
//        if (event.getEventType() != AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
//            AccessibilityNodeInfo eventSource = event.getSource();
//            // 没有消息
//            if (event.getEventType() != AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED || eventSource == null) {
//                return false;
//            } else {
//                //避免当订阅号中出现标题为“xxx”（其实并支付）的信息时误判
//                String tip = event.getText().toString();
//                String pattern = "[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*";
//                Pattern r = Pattern.compile(pattern);
//                Matcher m = r.matcher(tip);
//                if (m.find()) {
//                    money = m.group(0);
//                    matchStr = m.replaceAll("");
//                    if (matchStr.equals(WE_CHAT_NOTIFICATION_TIP)) {
//                        return true;
//                    }
//                    LogUtils.debugInfo(TAG, money + "====" + matchStr);
//                }
//                return false;
//            }
//        } else {
//            //避免当订阅号中出现标题为“xxx”（其实并支付）的信息时误判
//            String tip = event.getText().toString();
//            String pattern = "([1-9]\\d*.\\d*)|(0.\\d*[1-9]\\d*)";
//            Pattern r = Pattern.compile(pattern);
//            Matcher m = r.matcher(tip);
//            if (m.find()) {
//                money = m.group(0);
//                matchStr = m.replaceAll("").replace(" ", "");
//                if (matchStr.equals(WE_CHAT_NOTIFICATION_TIP)) {
//                    return true;
//                }
//                LogUtils.debugInfo(TAG, money + "====" + matchStr);
//            }
//            return false;
//
////            // 跳转到收款通知
////            Parcelable parcelable = event.getParcelableData();
////            if (parcelable instanceof Notification) {
////                Notification notification = (Notification) parcelable;
////                try {
////                    notification.contentIntent.send();
////                } catch (PendingIntent.CanceledException e) {
////                    e.printStackTrace();
////                }
////            }
//        }
//    }
//
//}
