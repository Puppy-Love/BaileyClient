package com.min.bailey.client.app.config.lifecyclesOptioins;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import org.simple.eventbus.EventBus;


/**
 * @author Administrator
 */
public class MyActivityLifecycle implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        EventBus.getDefault().register(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
//        if (!activity.getIntent().getBooleanExtra(Constant.IS_TOOLBAR, false)) {
//            //由于加强框架的兼容性,故将 setContentView 放到 onActivityCreated 之后,onActivityStarted 之前执行
//            //而 findViewById 必须在 Activity setContentView() 后才有效,所以将以下代码从之前的 onActivityCreated 中移动到 onActivityStarted 中执行
//            String title = activity.getIntent().getStringExtra(Constant.TOOLBAR_TITLE);
//            //这里全局给Activity设置toolbar和title,你想象力有多丰富,这里就有多强大,以前放到BaseActivity的操作都可以放到这里
//            QMUITopBar toolbar = activity.findViewById(R.id.topbar);
//            if (toolbar != null) {
//                if (activity instanceof AppCompatActivity) {
//                    ((AppCompatActivity) activity).setSupportActionBar(toolbar);
//                    ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                    ((AppCompatActivity) activity).getSupportActionBar().setTitle(title);
//                } else {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        activity.setActionBar(activity.findViewById(R.id.toolbar));
//                        activity.getActionBar().setTitle(title);
//                        activity.getActionBar().setDisplayHomeAsUpEnabled(true);
//                    }
//                }
//                toolbar.setNavigationOnClickListener(view -> activity.onBackPressed());
//            }
//        }

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        EventBus.getDefault().unregister(activity);
    }
}
