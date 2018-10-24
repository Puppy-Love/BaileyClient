package com.min.bailey.client.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.chrisbanes.photoview.PhotoView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.min.bailey.client.R;
import com.min.bailey.client.app.data.entity.RandomPhotoData;
import com.min.bailey.client.di.component.DaggerPreviewPicComponent;
import com.min.bailey.client.di.module.PreviewPicModule;
import com.min.bailey.client.mvp.contract.PreviewPicContract;
import com.min.bailey.client.mvp.presenter.PreviewPicPresenter;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * @author zhangmin
 */
public class PreviewPicActivity extends BaseActivity<PreviewPicPresenter> implements PreviewPicContract.View {

    @BindView(R.id.topBar)
    QMUITopBar mTopBar;
    @BindView(R.id.photo_view)
    PhotoView mPhotoView;

    private RandomPhotoData.ResBean.VerticalBean data;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPreviewPicComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .previewPicModule(new PreviewPicModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_preview_pic;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initViews();
    }

    private void initViews() {
        QMUIStatusBarHelper.translucent(this, 0x00FFFFFF);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        data = (RandomPhotoData.ResBean.VerticalBean) this.getIntent().getSerializableExtra("data");
        mPresenter.setImg(mPhotoView, data.getImg());
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
}
