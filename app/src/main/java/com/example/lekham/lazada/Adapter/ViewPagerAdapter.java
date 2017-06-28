package com.example.lekham.lazada.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

import com.example.lekham.lazada.Model.ObjectClass.ThucDon;
import com.example.lekham.lazada.R;
import com.example.lekham.lazada.Until.TabLayoutMenu;
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

    public ViewPagerAdapter(FragmentManager fm, Context context, List<ThucDon> thucDons) {
        super(fm);
        for (ThucDon thucDon : thucDons) {
            Fragment fragment = TabLayoutMenu.initTabLayoutMenu(context).getFragmentTabLayout(thucDon.getTENTHUCDON().toString().hashCode());
            if (fragment != null) {
                stringList.add(thucDon.getTENTHUCDON().toString());
                fragmentList.add(fragment);
                TabLayoutMenu.initTabLayoutMenu(context).saveThucDonHashMap(thucDon.getTENTHUCDON().toString().hashCode(), thucDon);

            }
        }
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
