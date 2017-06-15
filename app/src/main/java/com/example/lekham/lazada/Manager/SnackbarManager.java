package com.example.lekham.lazada.Manager;

import android.app.Activity;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;

/**
 * Created by Le Kham on 6/10/2017.
 */

public class SnackbarManager {
    private CoordinatorLayout mCoordinatorLayout;
    public Snackbar mSnackbar;

    public SnackbarManager(CoordinatorLayout coordinatorLayout) {
        mCoordinatorLayout = coordinatorLayout;
    }

    public void showSnackbar(String message) {
        mSnackbar = Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_SHORT);
        mSnackbar.show();
    }

}
