package com.min.bailey.client.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.min.bailey.client.R;
import com.min.bailey.client.app.utils.FragmentUtils;
import com.min.bailey.client.app.utils.RxDrawer;
import com.min.bailey.client.app.utils.RxUtils;
import com.min.bailey.client.di.component.DaggerMainComponent;
import com.min.bailey.client.di.module.MainModule;
import com.min.bailey.client.mvp.contract.MainContract;
import com.min.bailey.client.mvp.presenter.MainPresenter;
import com.min.bailey.client.mvp.ui.fragment.GirlItemFragment;
import com.min.bailey.client.mvp.ui.fragment.MeiZhiFragment;
import com.min.bailey.client.mvp.ui.fragment.WallpaperSortFragment;
import com.min.bailey.client.mvp.ui.fragment.WeatherFragment;
import com.min.bailey.client.mvp.ui.fragment.WelfareFragment;
import com.qmuiteam.qmui.widget.QMUIWindowInsetLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * @author: Bailey
 * Email: 553258697@qq.com
 * Description: 主页面
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.contentLayout)
    QMUIWindowInsetLayout mContentLayout;
    @BindView(R.id.navigation)
    NavigationView mNavigation;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private static final String FRAGMENT_TAG_WEATHER = "天气";
    private static final String FRAGMENT_TAG_GIRLS = "福利";
    private static final String FRAGMENT_TAG_BUS = "公交";
    private static final String FRAGMENT_TAG_GANK = "福利";
    private static final String FRAGMENT_TAG_READING = "闲读";
    private static final String FRAGMENT_TAG_EMPTY = "四大皆空";

    private Fragment[] mFragments = new Fragment[4];
    private int curIndex;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            curIndex = savedInstanceState.getInt("curIndex");
        }
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initNavigationViewHeader();
    }

    private void initNavigationViewHeader() {
        mFragments[0] = WeatherFragment.newInstance();
        mFragments[1] = MeiZhiFragment.newInstance();
        mFragments[2] = WallpaperSortFragment.newInstance();
        mFragments[3] = WelfareFragment.newInstance();
        FragmentUtils.add(getSupportFragmentManager(), mFragments, R.id.contentLayout, curIndex);
        mNavigation.setNavigationItemSelectedListener(this);
    }

    public void initDrawer(Toolbar toolbar) {
        if (toolbar != null) {
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                }
            };
            mDrawerToggle.syncState();
            mDrawerLayout.addDrawerListener(mDrawerToggle);
        }
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_weather:
                showCurrentFragment(0);
                return true;
            case R.id.nav_girls:
                showCurrentFragment(1);
                return true;
            case R.id.nav_manage:
                showCurrentFragment(2);
                return true;
            case R.id.nav_slideshow:
                showCurrentFragment(3);
                return true;
            case R.id.navigation_item_settings:
                ArmsUtils.startActivity(MainTabActivity.class);
                return true;
            case R.id.navigation_item_about:
                ArmsUtils.startActivity(MainTabActivity.class);
                return true;
            default:
                break;
        }
        return false;
    }

    private void showCurrentFragment(int index) {
        FragmentUtils.showHide(curIndex = index, mFragments);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        invalidateOptionsMenu();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("curIndex", curIndex);
    }
}
