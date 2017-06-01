package com.example.lekham.lazada.View.Main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.example.lekham.lazada.Adapter.ExpandableAdapter;
import com.example.lekham.lazada.Adapter.ViewPagerAdapter;
import com.example.lekham.lazada.Model.ObjectClass.ProductType;
import com.example.lekham.lazada.Presenter.Main.Menu.PresenterLogicActionMenu;
import com.example.lekham.lazada.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewMenu {

    TabLayout mTabLayout;
    ViewPager mViewPager;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    PresenterLogicActionMenu mPresenterLogicActionMenu;
    ExpandableListView mExpandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        setViewPager();
        setDrawerLayout();
        setExpandableListViewForMenu();
        mPresenterLogicActionMenu = new PresenterLogicActionMenu(this);
        mPresenterLogicActionMenu.GetAllListDataMenu(0);
    }

    private void setExpandableListViewForMenu() {
        mExpandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
    }

    private void setViewPager() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getApplicationContext());
        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setDrawerLayout() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open_drawer, R.string.close_drawer);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void ViewAllListDataMenu(List<ProductType> productTypes) {
        if (productTypes != null && productTypes.size() > 0) {
            ExpandableAdapter expandableAdapter = new ExpandableAdapter(this, productTypes);
            mExpandableListView.setAdapter(expandableAdapter);
        }
    }
}
