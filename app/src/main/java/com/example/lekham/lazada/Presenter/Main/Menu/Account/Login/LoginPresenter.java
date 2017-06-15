package com.example.lekham.lazada.Presenter.Main.Menu.Account.Login;

import android.content.Context;

import com.example.lekham.lazada.Model.Account.Login.LoginContract;
import com.example.lekham.lazada.Model.Account.Login.LoginInterator;
import com.example.lekham.lazada.Model.ObjectClass.NhanVien;
import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Le Kham on 6/11/2017.
 */

public class LoginPresenter implements LoginContract.Presenter, LoginContract.OnListenerLogin {


    private LoginContract.View mView;
    private LoginInterator mLoginInterator;

    public LoginPresenter(LoginContract.View view) {
        mView = view;
        mLoginInterator = new LoginInterator(this);
    }


    @Override
    public void loginAccount(Context context, NhanVien nhanVien) {
        mLoginInterator.performLoginAccount(context, nhanVien);
    }

    @Override
    public AccessToken GetCurrentAccessToken() {
        return mLoginInterator.GetCurrentAccessToken();
    }

    @Override
    public void stopTracking() {
        mLoginInterator.stopTracking();
    }

    @Override
    public GoogleApiClient GetGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return mLoginInterator.GetGoogleApiClient(context, onConnectionFailedListener);
    }

    @Override
    public GoogleSignInResult GetGoogleSignInResult(GoogleApiClient googleApiClient) {
        return mLoginInterator.GetGoogleSignInResult(googleApiClient);
    }

    @Override
    public void onSuccess(NhanVien nhanVien) {
        if (mView != null)
            mView.onLoginSuccess(nhanVien);
    }

    @Override
    public void onFailure() {
        if (mView != null)
            mView.onLoginFailure();

    }
}
