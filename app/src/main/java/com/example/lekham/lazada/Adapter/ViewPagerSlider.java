package com.example.lekham.lazada.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Created by Le Kham on 6/29/2017.
 */

public class ViewPagerSlider extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = Collections.emptyList();

    public ViewPagerSlider(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
