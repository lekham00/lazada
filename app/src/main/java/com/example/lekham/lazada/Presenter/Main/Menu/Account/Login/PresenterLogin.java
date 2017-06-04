package com.example.lekham.lazada.Presenter.Main.Menu.Account.Login;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.lekham.lazada.Model.Account.Login.ModelLogin;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

/**
 * Created by Le Kham on 6/4/2017.
 */

public class PresenterLogin implements IPresenterLogin {
    private AccessTokenTracker mAccessTokenTracker;
    private AccessToken mAccessToken;

    @Override
    public AccessToken GetCurrentAccessToken() {
        mAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
                mAccessToken = currentAccessToken;
            }
        };
        // If the access token is available already assign it.
        mAccessToken = AccessToken.getCurrentAccessToken();
        return mAccessToken;
    }

    @Override
    public void stopTracking() {
        if (mAccessTokenTracker != null)
            mAccessTokenTracker.stopTracking();
    }

    @Override
    public GoogleApiClient GetGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        GoogleApiClient googleApiClient = null;
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        if (googleSignInOptions != null) {
            googleApiClient = new GoogleApiClient.Builder(context).enableAutoManage((AppCompatActivity) context, onConnectionFailedListener)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                    .build();
        }
        return googleApiClient;
    }

    @Override
    public GoogleSignInResult GetGoogleSignInResult(GoogleApiClient googleApiClient) {
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone())
            return opr.get();
        return null;
    }
}
