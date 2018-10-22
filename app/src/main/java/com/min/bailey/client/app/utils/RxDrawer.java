package com.min.bailey.client.app.utils;

import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;


import com.jess.arms.utils.ArmsUtils;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.observable.ObservableObserveOn;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static io.reactivex.Observable.*;


/**
 * @author: Bailey
 * Email: 553258697@qq.com
 * Create Time: 2018/10/19
 * Description:
 */
public class RxDrawer {
    private static final float OFFSET_THRESHOLD = 0.03f;

    public static Observable<Void> close(final DrawerLayout drawer) {
        return Observable.unsafeCreate(new ObservableSource<Void>() {
            @Override
            public void subscribe(Observer<? super Void> subscriber) {
                drawer.closeDrawer(GravityCompat.START);
                final DrawerLayout.DrawerListener listener = new DrawerLayout.SimpleDrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        if (slideOffset < OFFSET_THRESHOLD) {
                            subscriber.onNext(null);
                            subscriber.onComplete();
                        }
                    }
                };
                drawer.addDrawerListener(listener);
                subscriber.onSubscribe(new Disposable() {
                    @Override
                    public void dispose() {
                        drawer.removeDrawerListener(listener);
                    }

                    @Override
                    public boolean isDisposed() {
                        return false;
                    }
                });
            }
        });
    }
}
