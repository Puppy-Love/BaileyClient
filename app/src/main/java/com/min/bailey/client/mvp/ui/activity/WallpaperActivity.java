package com.min.bailey.client.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.min.bailey.client.R;
import com.min.bailey.client.di.component.DaggerWallpaperComponent;
import com.min.bailey.client.di.module.WallpaperModule;
import com.min.bailey.client.mvp.contract.WallpaperContract;
import com.min.bailey.client.mvp.presenter.WallpaperPresenter;
import com.min.bailey.client.mvp.ui.adapter.GirlItemAdapter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * @author zhangmin
 */
public class WallpaperActivity extends BaseActivity<WallpaperPresenter>
        implements WallpaperContract.View,
        BaseQuickAdapter.RequestLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_wallpaper)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @BindView(R.id.topBar)
    QMUITopBar mTopBar;

    /**
     * 类别ID
     */
    private String id;
    private String name;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerWallpaperComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .wallpaperModule(new WallpaperModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_wallpaper;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initTopBar();
        mPresenter.initAdapter();
        mPresenter.getWallpaperBySort(true, id);
    }

    private void initTopBar() {
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarDarkMode(this);
        id = this.getIntent().getStringExtra("id");
        name = this.getIntent().getStringExtra("name");
        mTopBar.setTitle(name);
        mTopBar.setBackgroundDividerEnabled(true);
        mTopBar.addLeftImageButton(R.drawable.ic_blue_back, R.id.act_qp_back).setOnClickListener(v -> {
            killMyself();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });

    }

    @Override
    public void showLoading() {
        mRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mRefresh.setRefreshing(false);
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
    public void setAdapter(GirlItemAdapter adapter) {
        mRefresh.setOnRefreshListener(this);
        mRefresh.setColorSchemeColors(Color.rgb(47, 233, 189));
//        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //可防止Item切换
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoadMoreComplete(GirlItemAdapter adapter) {
        adapter.loadMoreComplete();
        mRefresh.setEnabled(true);
        adapter.setEnableLoadMore(true);
    }

    @Override
    public void onLoadMoreError(GirlItemAdapter adapter) {
        adapter.loadMoreFail();
        mRefresh.setEnabled(true);
    }

    @Override
    public void onLoadMoreEnd(GirlItemAdapter adapter) {
        adapter.loadMoreEnd();
    }

    @Override
    public void onRefresh() {
        mPresenter.getWallpaperBySort(true, id);
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getWallpaperBySort(false, id);
        mRefresh.setEnabled(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
