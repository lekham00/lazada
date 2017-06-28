package com.example.lekham.lazada.View.DetailProduct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.lekham.lazada.Adapter.ViewPagerDetailProductAdapter;
import com.example.lekham.lazada.R;

/**
 * Created by Le Kham on 6/26/2017.
 */

public class DetailProductActivity extends AppCompatActivity {

    protected Toolbar mToolbar;
    private ViewPager mViewPager;
    private ViewPagerDetailProductAdapter mViewPagerDetailProductAdapter;
    private TabLayout mTabLayout;
    public static final String ID = "ID";


    public static void startIntent(Context context, int id) {
        Intent intent = new Intent(context, DetailProductActivity.class);
        intent.putExtra(ID, id);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle("Apps");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mViewPager = (ViewPager) findViewById(R.id.viewPagerDetailProduct);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPagerDetailProductAdapter = new ViewPagerDetailProductAdapter(getSupportFragmentManager(), getApplicationContext());
        mViewPager.setAdapter(mViewPagerDetailProductAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
