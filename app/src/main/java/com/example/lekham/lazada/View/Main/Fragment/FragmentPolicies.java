package com.example.lekham.lazada.View.Main.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lekham.lazada.Manager.DialogManager;
import com.example.lekham.lazada.R;

/**
 * Created by Le Kham on 6/19/2017.
 */

public class FragmentPolicies extends Fragment implements View.OnClickListener {
    private View view;
    private Button mBtnSendToHome, mBtnSafe, mBtnSecurity;
    private DialogManager mDialogManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_policies, container, false);
        init();
        return view;
    }

    private void init() {
        if (view != null) {
            mDialogManager = new DialogManager(getActivity());
            mBtnSendToHome = (Button) view.findViewById(R.id.btnSendToHome);
            mBtnSafe = (Button) view.findViewById(R.id.btnSafe);
            mBtnSecurity = (Button) view.findViewById(R.id.btnSecurity);
            mBtnSendToHome.setOnClickListener(this);
            mBtnSafe.setOnClickListener(this);
            mBtnSecurity.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSendToHome:
                mDialogManager.showDialog(getString(R.string.text_title_dialog_Send_To_Home), getString(R.string.text_content_dialog_Send_To_Home));
                break;
            case R.id.btnSafe:
                mDialogManager.showDialog(getString(R.string.text_title_dialog_safe), getString(R.string.text_content_dialog_safe));
                break;
            case R.id.btnSecurity:
                mDialogManager.showDialog(getString(R.string.text_title_dialog_security), getString(R.string.text_content_dialog_security));
                break;
        }
    }
}
