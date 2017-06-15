package com.example.lekham.lazada.View.Account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.lekham.lazada.Adapter.ViewPagerAdapterAccount;
import com.example.lekham.lazada.Manager.SnackbarManager;
import com.example.lekham.lazada.R;
import com.example.lekham.lazada.View.Account.Fragment.LoginFragment;
import com.example.lekham.lazada.View.Account.Fragment.RegisterFragment;

/**
 * Created by Le Kham on 6/2/2017.
 */

public class AccountActivity extends AppCompatActivity implements RegisterFragment.OnListener, LoginFragment.OnListenerLogin {

    ViewPager mViewPagerAccount;
    TabLayout mTabsAccount;
    ViewPagerAdapterAccount mViewPagerAdapterAccount;
    Toolbar mToolbar;
    SnackbarManager mSnackbarManager;
    CoordinatorLayout mCoordinatorLayout;


    public static void startIntent(Activity activity) {
        Intent intent = new Intent(activity, AccountActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        mToolbar = (Toolbar) findViewById(R.id.toolBarAccount);
        setSupportActionBar(mToolbar);
        handelTabLayout();
        mSnackbarManager = new SnackbarManager(mCoordinatorLayout);
    }

    private void handelTabLayout() {
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mViewPagerAccount = (ViewPager) findViewById(R.id.viewPagerAccount);
        mTabsAccount = (TabLayout) findViewById(R.id.tabsAccount);
        mViewPagerAdapterAccount = new ViewPagerAdapterAccount(getSupportFragmentManager(), getApplicationContext());
        mViewPagerAccount.setAdapter(mViewPagerAdapterAccount);
        mTabsAccount.setupWithViewPager(mViewPagerAccount);
    }

    @Override
    public void showSnackBar(String message) {
        mSnackbarManager.showSnackbar(message);
    }
}
