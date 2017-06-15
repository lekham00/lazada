package com.example.lekham.lazada.Presenter.Main.Menu.Account.Login.Register;

import android.content.Context;

import com.example.lekham.lazada.Model.Account.Login.Register.RegisterContract;
import com.example.lekham.lazada.Model.Account.Login.Register.RegisterInterator;
import com.example.lekham.lazada.Model.ObjectClass.NhanVien;


/**
 * Created by Le Kham on 6/7/2017.
 */

public class RegisterPresenter implements RegisterContract.Presenter, RegisterContract.OnRegisterListener {

    private RegisterContract.View mView;
    private RegisterInterator mRegisterInterator;

    public RegisterPresenter(RegisterContract.View view) {
        mView = view;
        mRegisterInterator = new RegisterInterator(this);
    }

    @Override
    public void registerAccount(Context context, NhanVien nhanVien) {
        mRegisterInterator.performRegisterAccount(context, nhanVien);
    }

    @Override
    public void onSuccess() {
        mView.onRegisterSuccess();
    }

    @Override
    public void onFailuer() {
        mView.onRegisterFailure();
    }
}
