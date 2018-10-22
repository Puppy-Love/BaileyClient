package com.min.bailey.client.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.min.bailey.client.mvp.ui.fragment.GirlItemFragment;
import com.min.bailey.client.mvp.ui.fragment.MeiZhiFragment;
import com.min.bailey.client.mvp.ui.fragment.WallpaperSortFragment;

import java.util.ArrayList;


/**
 * 主页tab页面适配器
 *
 * @author Administrator
 */
public class TabFragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTabTitles = {"推荐", "分类", "妹子"};

    public TabFragmentAdapter(FragmentManager fm) {
        super(fm);
        mFragments.add(GirlItemFragment.newInstance());
        mFragments.add(WallpaperSortFragment.newInstance());
        mFragments.add(MeiZhiFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }
}
