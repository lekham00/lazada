package com.example.lekham.lazada.View.ReviewProduct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.lekham.lazada.R;
import com.example.lekham.lazada.View.BaseActivity;

import retrofit2.http.PUT;

/**
 * Created by Le Kham on 7/2/2017.
 */

public class ReviewProductActitvity extends BaseActivity {

    public static final String KEY_TITLE = "KEY_TITLE";
    public static final String KEY_PRICE = "KEY_PRICE";
    public static final String KEY_ID = "KEY_ID";

    public static void startIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ReviewProductActitvity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_product);
        handelToolbar(getString(R.string.text_view_rating_review));
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, ReviewProductFragment.initFragment(getIntent().getExtras())).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.menu_cart).setVisible(false);
        menu.findItem(R.id.menu_more).setVisible(false);
        return true;
    }

}