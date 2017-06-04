package com.example.lekham.lazada.View.Account.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lekham.lazada.Presenter.Main.Menu.Account.Login.PresenterLogin;
import com.example.lekham.lazada.R;
import com.example.lekham.lazada.Until.SharePre;
import com.example.lekham.lazada.View.Main.MainActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;

/**
 * Created by Le Kham on 6/2/2017.
 */

public class LoginFragment extends Fragment implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    View mView;
    Button mBtnFacebook, mBtnGoogle;
    CallbackManager mCallbackManager;
    GoogleApiClient mGoogleApiClient;
    PresenterLogin mPresenterLogin;
    private final int RC_SIGN_IN = 999;
    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login, container, false);
        mPresenterLogin = new PresenterLogin();
        mGoogleApiClient = mPresenterLogin.GetGoogleApiClient(getContext(), this);
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager, callbackFacebook);

        init();
        return mView;
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage(getString(R.string.message_progress_dialog));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    private void hideProgressDailog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    private void init() {
        mBtnFacebook = (Button) mView.findViewById(R.id.btnFacebook);
        mBtnGoogle = (Button) mView.findViewById(R.id.btnGoogle);
        mBtnFacebook.setOnClickListener(this);
        mBtnGoogle.setOnClickListener(this);
    }

    FacebookCallback<LoginResult> callbackFacebook = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            MainActivity.startIntent(getActivity());
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFacebook:
                showProgressDialog();
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
                break;
            case R.id.btnGoogle:
                if (mGoogleApiClient != null) {
                    showProgressDialog();
                    Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                    startActivityForResult(intent, RC_SIGN_IN);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            hideProgressDailog();
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                MainActivity.startIntent(getActivity());
            }
        } else {
            hideProgressDailog();
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        hideProgressDailog();
    }
}
