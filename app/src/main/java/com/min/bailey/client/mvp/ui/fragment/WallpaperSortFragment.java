package com.min.bailey.client.mvp.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.min.bailey.client.R;
import com.min.bailey.client.di.component.DaggerWallpaperSortComponent;
import com.min.bailey.client.di.module.WallpaperSortModule;
import com.min.bailey.client.mvp.contract.WallpaperSortContract;
import com.min.bailey.client.mvp.presenter.WallpaperSortPresenter;
import com.min.bailey.client.mvp.ui.adapter.WallpaperSortAdapter;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * @author Administrator
 */
public class WallpaperSortFragment extends BaseFragment<WallpaperSortPresenter> implements WallpaperSortContract.View {

    @BindView(R.id.wallpaper_sort_recycler)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    public static WallpaperSortFragment newInstance() {
        WallpaperSortFragment fragment = new WallpaperSortFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerWallpaperSortComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .wallpaperSortModule(new WallpaperSortModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wallpaper_sort, container, false);
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        isCreated = true;
//    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.initAdapter();
        mPresenter.getSort();
    }
//
//    protected boolean isCreated = false;
//
//    /**
//     * @param isVisibleToUser
//     */
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (!isCreated) {
//            return;
//        }
//        if (isVisibleToUser) {
//            // 沉浸式状态栏
//            QMUIStatusBarHelper.translucent(getActivity());
//            QMUIStatusBarHelper.setStatusBarLightMode(getActivity());
//        }
//    }

    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     * <p>
     * Example usage:
     * <pre>
     * public void setData(@Nullable Object data) {
     *     if (data != null && data instanceof Message) {
     *         switch (((Message) data).what) {
     *             case 0:
     *                 loadData(((Message) data).arg1);
     *                 break;
     *             case 1:
     *                 refreshUI();
     *                 break;
     *             default:
     *                 //do something
     *                 break;
     *         }
     *     }
     * }
     *
     * // call setData(Object):
     * Message data = new Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    @Override
    public void setData(@Nullable Object data) {

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

    }

    @Override
    public void setAdapter(WallpaperSortAdapter adapter) {
        initRecyclerView();
        mRecyclerView.setAdapter(adapter);

    }

    /**
     * @describe 初始化recyclerView
     */
    private void initRecyclerView() {
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //可防止Item切换
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
