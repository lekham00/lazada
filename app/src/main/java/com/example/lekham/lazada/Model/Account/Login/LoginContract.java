package com.example.lekham.lazada.Model.Account.Login;

import android.content.Context;

import com.example.lekham.lazada.Model.ObjectClass.NhanVien;
import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Le Kham on 6/11/2017.
 */

public interface LoginContract {

    interface View {
        void onLoginSuccess(NhanVien nhanVien);

        void onLoginFailure();
    }

    interface Presenter {
        void loginAccount(Context context, NhanVien nhanVien);
        AccessToken GetCurrentAccessToken();

        void stopTracking();

        GoogleApiClient GetGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener);

        GoogleSignInResult GetGoogleSignInResult(GoogleApiClient googleApiClient);
    }

    interface Interator {
        void performLoginAccount(Context context, NhanVien nhanVien);
        AccessToken GetCurrentAccessToken();

        void stopTracking();

        GoogleApiClient GetGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener);

        GoogleSignInResult GetGoogleSignInResult(GoogleApiClient googleApiClient);
    }

    interface OnListenerLogin {
        void onSuccess(NhanVien nhanVien);

        void onFailure();
    }
}
