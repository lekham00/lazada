package com.example.lekham.lazada.View.Account.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.lekham.lazada.Manager.DialogManager;
import com.example.lekham.lazada.Model.Account.Login.LoginContract;
import com.example.lekham.lazada.Model.ObjectClass.NhanVien;
import com.example.lekham.lazada.Presenter.Main.Menu.Account.Login.LoginPresenter;
import com.example.lekham.lazada.R;
import com.example.lekham.lazada.Until.Keyboard;
import com.example.lekham.lazada.Until.SharePre;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
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

public class LoginFragment extends Fragment implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener, LoginContract.View {

    View mView;
    Button mBtnFacebook, mBtnGoogle, mBtnLogin;
    CallbackManager mCallbackManager;
    GoogleApiClient mGoogleApiClient;
    LoginPresenter mLoginPresenter;
    private final int RC_SIGN_IN = 999;
    private DialogManager mDialogManager;
    private EditText mEditTextEmail, mEditTextPassword;
    private OnListenerLogin mOnListenerLogin;
    private TextInputLayout mTextInputLayoutEmail, mTextInputLayoutPass;

    public interface OnListenerLogin {
        void showSnackBar(String message);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login, container, false);
        mLoginPresenter = new LoginPresenter(this);
        mGoogleApiClient = mLoginPresenter.GetGoogleApiClient(getContext(), this);
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager, callbackFacebook);
        mDialogManager = new DialogManager(getActivity());
        init();
        return mView;
    }

    private void handelConditionRegiserButton() {
        mTextInputLayoutEmail = (TextInputLayout) mEditTextEmail.getParent().getParent();
        mTextInputLayoutPass = (TextInputLayout) mEditTextPassword.getParent().getParent();
        setErrorInputLayout(mTextInputLayoutEmail, false, null);
        setErrorInputLayout(mTextInputLayoutPass, false, null);
        if (TextUtils.isEmpty(mEditTextEmail.getText().toString())) {
            mEditTextEmail.requestFocus();
            Keyboard.showKeyboard(getContext(), mEditTextEmail);
            setErrorInputLayout(mTextInputLayoutEmail, true, getString(R.string.error_email_empty));
        } else if (TextUtils.isEmpty(mEditTextPassword.getText().toString())) {
            mEditTextPassword.requestFocus();
            Keyboard.showKeyboard(getContext(), mEditTextPassword);
            setErrorInputLayout(mTextInputLayoutPass, true, getString(R.string.error_pass_empty));
        }
    }

    private void setErrorInputLayout(TextInputLayout inputLayout, boolean isEnabled, String text) {
        inputLayout.setErrorEnabled(isEnabled);
        inputLayout.setError(text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListenerLogin) {
            mOnListenerLogin = (OnListenerLogin) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnListenerLogin = null;
    }

    private void init() {
        mBtnLogin = (Button) mView.findViewById(R.id.btnLogin);
        mEditTextEmail = (EditText) mView.findViewById(R.id.editTextEmail);
        mEditTextPassword = (EditText) mView.findViewById(R.id.editTextPassword);
        mBtnFacebook = (Button) mView.findViewById(R.id.btnFacebook);
        mBtnGoogle = (Button) mView.findViewById(R.id.btnGoogle);
        mBtnFacebook.setOnClickListener(this);
        mBtnGoogle.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
    }

    FacebookCallback<LoginResult> callbackFacebook = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
//            MainActivity.startIntent(getActivity());
            getActivity().finish();
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
                mDialogManager.showProgressDialog(getString(R.string.message_progress_dialog));
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
                break;
            case R.id.btnGoogle:
                if (mGoogleApiClient != null) {
                    mDialogManager.showProgressDialog(getString(R.string.message_progress_dialog));
                    Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                    startActivityForResult(intent, RC_SIGN_IN);
                }
                break;
            case R.id.btnLogin:
                onLogin();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            mDialogManager.dismiss();
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
//                MainActivity.startIntent(getActivity(), Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().finish();
            }
        } else {
            mDialogManager.dismiss();
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mDialogManager.dismiss();
    }

    private void onLogin() {
        handelConditionRegiserButton();
        if (!mTextInputLayoutEmail.isErrorEnabled()
                && !mTextInputLayoutPass.isErrorEnabled()) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setEMAIL(mEditTextEmail.getText().toString());
            nhanVien.setMATKHAU(mEditTextPassword.getText().toString());
            Keyboard.hideKeyboard(getActivity());
            mDialogManager.showProgressDialog(getString(R.string.message_progress_dialog));
            mLoginPresenter.loginAccount(getContext(), nhanVien);
        }
    }

    @Override
    public void onLoginSuccess(NhanVien nhanVien) {
        mDialogManager.dismiss();
        if (nhanVien != null) {
            mOnListenerLogin.showSnackBar("Login account success");
            SharePre.instantSharePre(getContext()).setValueString(SharePre.KEY_ACCOUNT, nhanVien.getHOTENNV());
//            MainActivity.startIntent(getActivity(), Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            getActivity().finish();
        } else {
            mOnListenerLogin.showSnackBar("Login account failure");
        }
    }

    @Override
    public void onLoginFailure() {
        mDialogManager.dismiss();
        mOnListenerLogin.showSnackBar("Login account failure");
    }
}
