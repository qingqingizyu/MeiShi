package com.example.administrator.android_a1607_okhttp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import java.util.List;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;
    private String[] mNavigation;

    public MyPagerAdapter(FragmentManager fm, List<Fragment> list,String[] navigation) {
        super(fm);
        this.mList=list;
        this.mNavigation=navigation;
    }



    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mNavigation.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mNavigation[position];
    }
}
