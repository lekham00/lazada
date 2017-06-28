package com.example.lekham.lazada.Until;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;

import com.example.lekham.lazada.Model.ObjectClass.ThucDon;
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
import java.util.HashMap;
import java.util.List;

/**
 * Created by Le Kham on 6/17/2017.
 */

public class TabLayoutMenu {
    private HashMap<Integer, Fragment> mListFragment = new HashMap<>();
    private List<ThucDon> mThucDonList = new ArrayList<>();
    private HashMap<Integer, ThucDon> mThucDonHashMap = new HashMap<>();
    private static Context mContext;
    private static TabLayoutMenu initTabLayoutMenu = null;

    public static TabLayoutMenu initTabLayoutMenu(Context context) {
        mContext = context;
        if (initTabLayoutMenu == null) {
            initTabLayoutMenu = new TabLayoutMenu();
        }
        return initTabLayoutMenu;
    }

    public void saveFragmentForTabLayout() {
        mListFragment.put(mContext.getResources().getString(R.string.tabs_highlights).hashCode(), new HighlightsFragment());
        mListFragment.put(mContext.getResources().getString(R.string.tabs_taobao_collection).hashCode(), new TaobaoCollectionFragment());
        mListFragment.put(mContext.getResources().getString(R.string.tabs_fashion).hashCode(), new FashionFragment());
        mListFragment.put(mContext.getResources().getString(R.string.tabs_health_beauty).hashCode(), new HealthBeautyFragment());
        mListFragment.put(mContext.getResources().getString(R.string.tabs_baby_toys).hashCode(), new BabyToysFragment());
        mListFragment.put(mContext.getResources().getString(R.string.tabs_home_living).hashCode(), new HomeLivingFragment());
        mListFragment.put(mContext.getResources().getString(R.string.tabs_electronics).hashCode(), new ElectronicsFragment());
        mListFragment.put(mContext.getResources().getString(R.string.tabs_motors).hashCode(), new MotorsFragment());
    }

    public Fragment getFragmentTabLayout(int key) {
        return mListFragment.get(key);
    }

    public void saveListTabHost(List<ThucDon> thucDonList) {
        mThucDonList = thucDonList;
    }

    public List<ThucDon> getListTabHost() {
        return mThucDonList;
    }

    public void saveThucDonHashMap(int key, ThucDon thucDon) {
        mThucDonHashMap.put(key, thucDon);
    }

    public ThucDon getThucDonHashMap(int key) {
        return mThucDonHashMap.get(key);
    }
}
