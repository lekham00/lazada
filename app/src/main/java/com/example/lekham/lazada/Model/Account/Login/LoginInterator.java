package com.example.lekham.lazada.Model.Account.Login;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.lekham.lazada.Model.ObjectClass.NhanVien;
import com.example.lekham.lazada.Network.APIService;
import com.example.lekham.lazada.Network.ApiUtils;
import com.example.lekham.lazada.Until.JsonParser;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Le Kham on 6/11/2017.
 */

public class LoginInterator implements LoginContract.Interator {
    public static final String TAG = LoginInterator.class.getSimpleName();

    private LoginContract.OnListenerLogin mOnListenerLogin;
    private AccessTokenTracker mAccessTokenTracker;
    private AccessToken mAccessToken;
    private APIService mApiService;


    public LoginInterator(LoginContract.OnListenerLogin onListenerLogin) {
        mOnListenerLogin = onListenerLogin;
        mApiService = ApiUtils.getApiService();
    }

    @Override
    public void performLoginAccount(Context context, NhanVien nhanVien) {
        mApiService.loginAccount(JsonParser.toJsonElement(nhanVien)).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "response");
                    JsonElement jsonElement = response.body();
                    if (jsonElement != null && jsonElement.isJsonObject()) {
                        String json = jsonElement.getAsJsonObject().toString();
                        try {
                            mOnListenerLogin.onSuccess(JsonParser.fromJson(json, NhanVien.class));
                        } catch (Exception e) {
                            e.printStackTrace();
                            mOnListenerLogin.onFailure();
                        }
                    }
                } else {
                    mOnListenerLogin.onFailure();
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.d(TAG, "onFailure :" + t.getMessage().toString());
                mOnListenerLogin.onFailure();
            }
        });
    }

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
