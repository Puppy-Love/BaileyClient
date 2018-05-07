package com.min.bailey.client.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.min.bailey.client.R;
import com.min.bailey.client.app.service.PayCompleteService;
import com.min.bailey.client.di.component.DaggerMainTabComponent;
import com.min.bailey.client.di.module.MainTabModule;
import com.min.bailey.client.mvp.contract.MainTabContract;
import com.min.bailey.client.mvp.presenter.MainTabPresenter;
import com.min.bailey.client.mvp.ui.fragment.WelfareFragment;
import com.qmuiteam.qmui.widget.QMUIPagerAdapter;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUIViewPager;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * 主页面
 *
 * @author Administrator
 */
public class MainTabActivity extends BaseActivity<MainTabPresenter> implements MainTabContract.View {


    @BindView(R.id.act_main_tab_pager)
    QMUIViewPager mViewPager;
    @BindView(R.id.act_main_tab_segment)
    QMUITabSegment mTabSegment;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainTabComponent
                .builder()
                .appComponent(appComponent)
                .mainTabModule(new MainTabModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_main_tab;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        initTabAndPager();
        if (isNotificationListenerServiceEnabled(this)) {

        } else {
            toggleNotificationListenerService();
        }
    }

    /**
     * @describe 确认NotificationMonitor是否开启
     */
    private static boolean isNotificationListenerServiceEnabled(Context context) {
        Set<String> packageNames = NotificationManagerCompat.getEnabledListenerPackages(context);
        if (packageNames.contains(context.getPackageName())) {
            return true;
        }
        return false;
    }

    /**
     * @describe 重新开启NotificationMonitor
     */
    private void toggleNotificationListenerService() {
        ComponentName thisComponent = new ComponentName(this, PayCompleteService.class);
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(thisComponent, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        pm.setComponentEnabledSetting(thisComponent, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }




    /**
     * 初始化Tab
     */
    private void initTabAndPager() {
        QMUIPagerAdapter pagerAdapter = new QMUIPagerAdapter() {
            private FragmentTransaction mCurrentTransaction;
            private Fragment mCurrentPrimaryItem = null;

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == ((Fragment) object).getView();
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
//                switch (position) {
//                    case 0:
//                        return "Button";
//                    case 1:
//                        return "CollapsingTopBar";
//                    case 2:
//                    default:
//                        return "About";
//                }
                return null;
            }

            @Override
            protected Object hydrate(ViewGroup container, int position) {
                switch (position) {
                    case 0:
                        return new WelfareFragment();
                    case 1:
                        return new WelfareFragment();
                    case 2:
                        return new WelfareFragment();
                    default:
                        return null;
                }
            }

            @SuppressLint("CommitTransaction")
            @Override
            protected void populate(ViewGroup container, Object item, int position) {
                String name = makeFragmentName(container.getId(), position);
                if (mCurrentTransaction == null) {
                    mCurrentTransaction = getSupportFragmentManager()
                            .beginTransaction();
                }
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(name);
                if (fragment != null) {
                    mCurrentTransaction.attach(fragment);
                } else {
                    fragment = (Fragment) item;
                    mCurrentTransaction.add(container.getId(), fragment, name);
                }
                if (fragment != mCurrentPrimaryItem) {
                    fragment.setMenuVisibility(false);
                    fragment.setUserVisibleHint(false);
                }
            }

            @SuppressLint("CommitTransaction")
            @Override
            protected void destroy(ViewGroup container, int position, Object object) {
                if (mCurrentTransaction == null) {
                    mCurrentTransaction = getSupportFragmentManager()
                            .beginTransaction();
                }
                mCurrentTransaction.detach((Fragment) object);
            }

            @Override
            public void startUpdate(ViewGroup container) {
                if (container.getId() == View.NO_ID) {
                    throw new IllegalStateException("ViewPager with adapter " + this
                            + " requires a view id");
                }
            }

            @Override
            public void finishUpdate(ViewGroup container) {
                if (mCurrentTransaction != null) {
                    mCurrentTransaction.commitNowAllowingStateLoss();
                    mCurrentTransaction = null;
                }
            }

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                Fragment fragment = (Fragment) object;
                if (fragment != mCurrentPrimaryItem) {
                    if (mCurrentPrimaryItem != null) {
                        mCurrentPrimaryItem.setMenuVisibility(false);
                        mCurrentPrimaryItem.setUserVisibleHint(false);
                    }
                    if (fragment != null) {
                        fragment.setMenuVisibility(true);
                        fragment.setUserVisibleHint(true);
                    }
                    mCurrentPrimaryItem = fragment;
                }
            }

            private String makeFragmentName(int viewId, long id) {
                return "QDFitSystemWindowViewPagerFragment:" + viewId + ":" + id;
            }
        };
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(2);
        mTabSegment.setHasIndicator(false);
        QMUITabSegment.Tab homePager = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(this, R.drawable.ic_main_tab_home_normal),
                ContextCompat.getDrawable(this, R.drawable.ic_main_tab_home_press),
                getString(R.string.tab_home_pager), false
        );
        QMUITabSegment.Tab wealth = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(this, R.drawable.ic_main_tab_wealth_normal),
                ContextCompat.getDrawable(this, R.drawable.ic_main_tab_wealth_press),
                getString(R.string.tab_wealth), false
        );
        QMUITabSegment.Tab me = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(this, R.drawable.ic_main_tab_me_normal),
                ContextCompat.getDrawable(this, R.drawable.ic_main_tab_me_press),
                getString(R.string.tab_me), false
        );
        mTabSegment.addTab(homePager);
        mTabSegment.addTab(wealth);
        mTabSegment.addTab(me);
        mTabSegment.setTabTextSize(30);
        mTabSegment.selectTab(0);
        mTabSegment.setupWithViewPager(mViewPager, false);
        mTabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                mTabSegment.hideSignCountView(index);
            }

            @Override
            public void onTabUnselected(int index) {

            }

            @Override
            public void onTabReselected(int index) {
                mTabSegment.hideSignCountView(index);
            }

            @Override
            public void onDoubleTap(int index) {

            }
        });
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
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
