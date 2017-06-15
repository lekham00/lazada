package com.example.lekham.lazada.Manager;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;

/**
 * Created by Le Kham on 6/9/2017.
 */

public class DialogManager {

    private Activity mActivity;
    private ProgressDialog mDialog;

    public DialogManager(Activity activity) {
        mActivity = activity;
    }

    public void showProgressDialog(String messager) {
        mDialog = new ProgressDialog(mActivity);
        mDialog.setMessage(messager);
        mDialog.setIndeterminate(true);
        mDialog.show();
    }

    public void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

}
