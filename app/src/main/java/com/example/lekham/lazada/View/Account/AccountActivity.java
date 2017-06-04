package com.example.lekham.lazada.View.Account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.lekham.lazada.Adapter.ViewPagerAdapterAccount;
import com.example.lekham.lazada.R;

/**
 * Created by Le Kham on 6/2/2017.
 */

public class AccountActivity extends AppCompatActivity {

    ViewPager mViewPagerAccount;
    TabLayout mTabsAccount;
    ViewPagerAdapterAccount mViewPagerAdapterAccount;
    Toolbar mToolbar;


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
    }

    private void handelTabLayout() {
        mViewPagerAccount = (ViewPager) findViewById(R.id.viewPagerAccount);
        mTabsAccount = (TabLayout) findViewById(R.id.tabsAccount);
        mViewPagerAdapterAccount = new ViewPagerAdapterAccount(getSupportFragmentManager(), getApplicationContext());
        mViewPagerAccount.setAdapter(mViewPagerAdapterAccount);
        mTabsAccount.setupWithViewPager(mViewPagerAccount);
    }
}
