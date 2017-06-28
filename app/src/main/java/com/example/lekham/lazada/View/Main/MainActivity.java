package com.example.lekham.lazada.View.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.example.lekham.lazada.Adapter.ExpandableAdapter;
import com.example.lekham.lazada.Adapter.ViewPagerAdapter;
import com.example.lekham.lazada.Model.ObjectClass.ProductType;
import com.example.lekham.lazada.Model.ObjectClass.ThucDon;
import com.example.lekham.lazada.Model.TabLayoutMenu.TabLayoutMenuContract;
import com.example.lekham.lazada.Presenter.Main.Menu.Account.Login.LoginPresenter;
import com.example.lekham.lazada.Presenter.Main.Menu.PresenterLogicActionMenu;
import com.example.lekham.lazada.Presenter.TabLayoutPresenter;
import com.example.lekham.lazada.R;
import com.example.lekham.lazada.Until.SharePre;
import com.example.lekham.lazada.Until.TabLayoutMenu;
import com.example.lekham.lazada.View.Account.AccountActivity;
import com.example.lekham.lazada.View.Account.Fragment.LoginFragment;
import com.example.lekham.lazada.View.Main.Fragment.ElectronicsFragment;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewMenu, GoogleApiClient.OnConnectionFailedListener, AppBarLayout.OnOffsetChangedListener, TabLayoutMenuContract.View {

    public static final String TAG = MainActivity.class.getSimpleName();

    TabLayout mTabLayout;
    ViewPager mViewPager;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    PresenterLogicActionMenu mPresenterLogicActionMenu;
    ExpandableListView mExpandableListView;
    AccessToken mAccessToken;
    LoginPresenter mLoginPresenter;
    GoogleSignInResult mGoogleSignInResult;
    GoogleApiClient mGoogleApiClient;
    List<ProductType> mProductTypeList = null;
    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private LinearLayout mLayoutSearch;
    private Boolean mShowMenuSearch = null;
    private String mUserName = null;
    private TabLayoutPresenter mTabLayoutPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        updateTabLayoutMenu();
        setDrawerLayout();
        setExpandableListViewForMenu();
        mPresenterLogicActionMenu = new PresenterLogicActionMenu(this);
        mLoginPresenter = new LoginPresenter(null);
        mGoogleApiClient = mLoginPresenter.GetGoogleApiClient(this, this);
        initAppBarLayout();
    }

    private void updateTabLayoutMenu() {
        if (TabLayoutMenu.initTabLayoutMenu(getApplicationContext()).getListTabHost().size() == 0) {
            mTabLayoutPresenter = new TabLayoutPresenter(this);
            mTabLayoutPresenter.perfromGetMenuForTabLayout(getApplicationContext());
        } else {
            setViewPager(TabLayoutMenu.initTabLayoutMenu(getApplicationContext()).getListTabHost());
        }
    }

    private void initAppBarLayout() {
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        mLayoutSearch = (LinearLayout) findViewById(R.id.layoutSearch);
        mAppBarLayout.addOnOffsetChangedListener(this);
    }


    public static void startIntent(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    public static void startIntent(Activity activity, int flags) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(flags);
        activity.startActivity(intent);
    }


    private void setExpandableListViewForMenu() {
        mExpandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
    }

    private void setViewPager(List<ThucDon> thucDons) {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getApplicationContext(), thucDons);
        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setDrawerLayout() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (mProductTypeList == null) {
                    if (mPresenterLogicActionMenu != null) {
                        AsyncTask asyncTask = new AsyncTask() {
                            @Override
                            protected Object doInBackground(Object[] params) {
                                mPresenterLogicActionMenu.GetAllListDataMenu(0);
                                return null;
                            }
                        };
                        asyncTask.execute();

                    }
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        handelGetAccountFacebook(menu);
        handelGetGoogleSignInResult(menu);
        handelGetAccountNormal(menu);
        if (mAccessToken != null || mGoogleSignInResult != null || mUserName != null) {
            menu.findItem(R.id.menu_logout).setVisible(true);
        }
        return true;
    }


    private void handelGetAccountNormal(Menu menu) {
        mUserName = SharePre.instantSharePre(getApplicationContext()).getValueString(SharePre.KEY_ACCOUNT);
        if (mUserName != null) {
            menu.findItem(R.id.menu_login).setTitle(mUserName);
        }
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
        switch (id) {
            case R.id.menu_login:
                if (mAccessToken == null && mGoogleSignInResult == null && mUserName == null)
                    AccountActivity.startIntent(MainActivity.this);
                return true;
            case R.id.menu_logout:
                handelLogout();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void handelLogout() {
        if (mAccessToken != null) {
            LoginManager.getInstance().logOut();
            invalidateOptionsMenu();
        } else if (mGoogleSignInResult != null) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {
                    mGoogleSignInResult = null;
                    invalidateOptionsMenu();
                }
            });
        } else if (mUserName != null) {
            SharePre.instantSharePre(getApplicationContext()).setValueString(SharePre.KEY_ACCOUNT, null);
            invalidateOptionsMenu();
        }

    }

    @Override
    public void ViewAllListDataMenu(List<ProductType> productTypes) {
        if (productTypes != null && productTypes.size() > 0) {
            mProductTypeList = productTypes;
            ExpandableAdapter expandableAdapter = new ExpandableAdapter(this, productTypes);
            mExpandableListView.setAdapter(expandableAdapter);
        }
    }

    private void handelGetGoogleSignInResult(Menu menu) {
        if (mLoginPresenter != null) {
            if (mGoogleApiClient != null) {
                mGoogleSignInResult = mLoginPresenter.GetGoogleSignInResult(mGoogleApiClient);
                if (mGoogleSignInResult != null) {
                    menu.findItem(R.id.menu_login).setTitle(mGoogleSignInResult.getSignInAccount().getDisplayName());
                }
            }
        }
    }

    private void handelGetAccountFacebook(final Menu menu) {
        if (mLoginPresenter != null) {
            mAccessToken = mLoginPresenter.GetCurrentAccessToken();
            if (mAccessToken != null) {
                GraphRequest request = GraphRequest.newMeRequest(
                        mAccessToken,
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                try {
                                    String name = object.getString("name").toString();
                                    menu.findItem(R.id.menu_login).setTitle(name);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "name");
                request.setParameters(parameters);
                request.executeAsync();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.stopTracking();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        float alpha = (float) (ViewCompat.getMinimumHeight(mCollapsingToolbarLayout) + verticalOffset) / (float) ViewCompat.getMinimumHeight(mCollapsingToolbarLayout);
        mLayoutSearch.setAlpha(alpha);
        if (alpha == 1) {
            mShowMenuSearch = false;
        } else if (alpha == 0) {
            mShowMenuSearch = true;
        }
    }

    @Override
    public void resultMenuTabLayout(List<ThucDon> thucDons) {
        if (thucDons != null && thucDons.size() > 0) {
            TabLayoutMenu.initTabLayoutMenu(getApplicationContext()).saveListTabHost(thucDons);
            setViewPager(thucDons);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }
}
