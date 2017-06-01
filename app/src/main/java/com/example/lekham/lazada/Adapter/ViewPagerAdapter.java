package com.example.lekham.lazada.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lekham.lazada.R;
import com.example.lekham.lazada.View.Main.Fragment.BabyToysFragment;
import com.example.lekham.lazada.View.Main.Fragment.ElectronicsFragment;
import com.example.lekham.lazada.View.Main.Fragment.FashionFragment;
import com.example.lekham.lazada.View.Main.Fragment.HealthBeautyFragment;
import com.example.lekham.lazada.View.Main.Fragment.HighlightsFragment;
import com.example.lekham.lazada.View.Main.Fragment.HomeLivingFragment;
import com.example.lekham.lazada.View.Main.Fragment.MotorsFragment;
import com.example.lekham.lazada.View.Main.Fragment.TaobaoCollectionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Le Kham on 5/28/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList = new ArrayList<Fragment>();
    List<String> stringList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        fragmentList.add(new HighlightsFragment());
        fragmentList.add(new TaobaoCollectionFragment());
        fragmentList.add(new FashionFragment());
        fragmentList.add(new HealthBeautyFragment());
        fragmentList.add(new BabyToysFragment());
        fragmentList.add(new HomeLivingFragment());
        fragmentList.add(new ElectronicsFragment());
        fragmentList.add(new MotorsFragment());
        stringList.add(context.getResources().getString(R.string.tabs_highlights));
        stringList.add(context.getResources().getString(R.string.tabs_taobao_collection));
        stringList.add(context.getResources().getString(R.string.tabs_fashion));
        stringList.add(context.getResources().getString(R.string.tabs_health_beauty));
        stringList.add(context.getResources().getString(R.string.tabs_baby_toys));
        stringList.add(context.getResources().getString(R.string.tabs_home_living));
        stringList.add(context.getResources().getString(R.string.tabs_electronics));
        stringList.add(context.getResources().getString(R.string.tabs_motors));
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position);
    }
}
