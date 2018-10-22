package com.min.bailey.client.mvp.ui.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.min.bailey.client.R;
import com.min.bailey.client.di.component.DaggerLoginComponent;
import com.min.bailey.client.di.module.LoginModule;
import com.min.bailey.client.mvp.contract.LoginContract;
import com.min.bailey.client.mvp.presenter.LoginPresenter;
import com.min.bailey.client.mvp.ui.widget.FullScreenVideoView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.squareup.haha.perflib.Main;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * @author: Bailey
 * Email: 553258697@qq.com
 * Create Time: ${DATE}
 * Description: 登录界面
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.video_view)
    FullScreenVideoView mVideoView;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarDarkMode(this);
        playVideoView();
    }

    private void playVideoView() {
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));
        //播放
        mVideoView.start();
        //循环播放
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mVideoView.start();
            }
        });
    }

    @Override
    protected void onRestart() {
        playVideoView();
        super.onRestart();
    }

    @Override
    protected void onStop() {
        mVideoView.stopPlayback();
        super.onStop();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.mFabToLogin)
    public void login() {
        ArmsUtils.startActivity(MainActivity.class);
    }
}
