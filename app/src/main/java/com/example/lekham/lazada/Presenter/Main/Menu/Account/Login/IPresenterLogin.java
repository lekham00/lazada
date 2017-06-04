package com.example.lekham.lazada.Presenter.Main.Menu.Account.Login;

import android.content.Context;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Le Kham on 6/4/2017.
 */

public interface IPresenterLogin {
    AccessToken GetCurrentAccessToken();

    void stopTracking();

    GoogleApiClient GetGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener);

    GoogleSignInResult GetGoogleSignInResult(GoogleApiClient googleApiClient);

}
