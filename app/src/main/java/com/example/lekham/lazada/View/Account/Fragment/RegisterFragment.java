package com.example.lekham.lazada.View.Account.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lekham.lazada.R;
import com.example.lekham.lazada.View.Account.Register.ViewRegister;

/**
 * Created by Le Kham on 6/2/2017.
 */

public class RegisterFragment extends Fragment implements ViewRegister {

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }

    @Override
    public void onRegisterSuccess(String messager) {
        Toast.makeText(getActivity(), messager, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegisterFails(String messager) {
        Toast.makeText(getActivity(), messager, Toast.LENGTH_SHORT).show();
    }
}
