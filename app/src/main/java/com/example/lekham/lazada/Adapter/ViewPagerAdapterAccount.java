package com.example.lekham.lazada.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;

import com.example.lekham.lazada.R;
import com.example.lekham.lazada.View.Account.AccountActivity;
import com.example.lekham.lazada.View.Account.Fragment.LoginFragment;
import com.example.lekham.lazada.View.Account.Fragment.RegisterFragment;

/**
 * Created by Le Kham on 6/2/2017.
 */

public class ViewPagerAdapterAccount extends FragmentPagerAdapter {

    Context mContext;

    public ViewPagerAdapterAccount(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LoginFragment loginFragment = new LoginFragment();
                return loginFragment;
            case 1:
                RegisterFragment registerFragment = new RegisterFragment();
                return registerFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getResources().getString(R.string.tabs_login);
            case 1:
                return mContext.getResources().getString(R.string.tabs_register);
            default:
                return null;
        }
    }
}
