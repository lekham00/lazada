package com.example.lekham.lazada.Manager;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.lekham.lazada.R;

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

    public void showDialog(String title, String content) {
        final Dialog dialog = new Dialog(mActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog);
        TextView txtTitle = (TextView) dialog.findViewById(R.id.txtTitle);
        TextView txtContent = (TextView) dialog.findViewById(R.id.txtDialogContent);
        txtTitle.setText(title);
        txtContent.setText(content);
        Button btnOk = (Button) dialog.findViewById(R.id.btnDialogOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
