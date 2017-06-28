package com.example.lekham.lazada.View.ListProduct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lekham.lazada.R;

/**
 * Created by Le Kham on 6/22/2017.
 */

public class ListProductActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    public static final String ID_KEY = "ID_KEY";
    public static final String TITLE_KEY = "TITLE_KEY";

    public static void startIntent(Context context, int id, String title) {
        Intent intent = new Intent(context, ListProductActivity.class);
        intent.putExtra(ID_KEY, id);
        intent.putExtra(TITLE_KEY, title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_list_product);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            setTitle(bundle.getString(TITLE_KEY));
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayoutContent, ListProductFragment.initDetailFragment(bundle), ListProductFragment.TAG);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}

