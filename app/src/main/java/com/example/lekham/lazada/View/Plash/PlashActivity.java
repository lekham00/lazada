package com.example.lekham.lazada.View.Plash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.lekham.lazada.R;
import com.example.lekham.lazada.Until.TabLayoutMenu;
import com.example.lekham.lazada.View.Main.MainActivity;

/**
 * Created by Le Kham on 5/28/2017.
 */

public class PlashActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plash_activity);
        TabLayoutMenu.initTabLayoutMenu(getApplicationContext()).saveFragmentForTabLayout();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {
                } finally {
                    startIntent();
                }
            }
        });
        thread.start();
    }

    public void startIntent() {
        Intent intent = new Intent(PlashActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
