package com.example.lekham.lazada.View.Account.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lekham.lazada.Manager.DialogManager;
import com.example.lekham.lazada.Model.Account.Login.Register.RegisterContract;
import com.example.lekham.lazada.Model.ObjectClass.NhanVien;
import com.example.lekham.lazada.Presenter.Main.Menu.Account.Login.Register.RegisterPresenter;
import com.example.lekham.lazada.R;
import com.example.lekham.lazada.Until.Keyboard;
import com.example.lekham.lazada.View.Main.MainActivity;

/**
 * Created by Le Kham on 6/2/2017.
 */

public class RegisterFragment extends Fragment implements RegisterContract.View, View.OnClickListener {

    View view;
    RegisterPresenter mRegisterPresenter;
    private EditText mEditTextFullName, mEditTextEmail, mEditTextPassword, mEditTextRetypePass;
    private Button mBtnRegister, mBtnFacebook, mBtnGoogle;
    private SwitchCompat mSwitchInformation;
    private TextInputLayout mTextInputLayoutFullName, mTextInputLayoutEmail, mTextInputLayoutPass, mTextInputLayoutRetypePass;
    private DialogManager mDialogManager;

    private OnListener mOnListener;

    public interface OnListener {
        void showSnackBar(String message);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);
        mRegisterPresenter = new RegisterPresenter(this);
        mDialogManager = new DialogManager(getActivity());
        init();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListener) {
            mOnListener = (OnListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnListener = null;
    }

    private void handelConditionRegiserButton() {
        mTextInputLayoutEmail = (TextInputLayout) mEditTextEmail.getParent().getParent();
        mTextInputLayoutFullName = (TextInputLayout) mEditTextFullName.getParent().getParent();
        mTextInputLayoutPass = (TextInputLayout) mEditTextPassword.getParent().getParent();
        mTextInputLayoutRetypePass = (TextInputLayout) mEditTextRetypePass.getParent().getParent();
        if (TextUtils.isEmpty(mEditTextEmail.getText().toString())) {
            setErrorInputLayout(mTextInputLayoutEmail, true, getString(R.string.error_email_empty));
        }
        if (TextUtils.isEmpty(mEditTextFullName.getText().toString())) {
            setErrorInputLayout(mTextInputLayoutFullName, true, getString(R.string.error_full_name_empty));
        }
        if (TextUtils.isEmpty(mEditTextPassword.getText().toString())) {
            setErrorInputLayout(mTextInputLayoutPass, true, getString(R.string.error_pass_empty));
        }
        if (TextUtils.isEmpty(mEditTextRetypePass.getText().toString())) {
            setErrorInputLayout(mTextInputLayoutRetypePass, true, getString(R.string.error_pass_empty));
        }
    }

    private void setErrorInputLayout(TextInputLayout inputLayout, boolean isEnabled, String text) {
        inputLayout.setErrorEnabled(isEnabled);
        inputLayout.setError(text);
    }

    private void init() {
        mEditTextFullName = (EditText) view.findViewById(R.id.editTextFullName);
        mEditTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
        mEditTextPassword = (EditText) view.findViewById(R.id.editTextPassword);
        mEditTextRetypePass = (EditText) view.findViewById(R.id.editTextRetypePass);
        mBtnRegister = (Button) view.findViewById(R.id.btnRegister);
        mBtnFacebook = (Button) view.findViewById(R.id.btnFacebook);
        mBtnGoogle = (Button) view.findViewById(R.id.btnGoogle);
        mSwitchInformation = (SwitchCompat) view.findViewById(R.id.switchInformation);
        mBtnRegister.setOnClickListener(this);
    }

    @Override
    public void onRegisterSuccess() {
        mOnListener.showSnackBar("Register Success");
        mDialogManager.dismiss();
//        MainActivity.startIntent(getActivity());
        getActivity().finish();
    }

    @Override
    public void onRegisterFailure() {
        mOnListener.showSnackBar("Register Failure");
        mDialogManager.dismiss();
    }

    private NhanVien getInfomationNhanVien() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setHOTENNV(mEditTextFullName.getText().toString());
        nhanVien.setEMAIL(mEditTextEmail.getText().toString());
        nhanVien.setMATKHAU(mEditTextPassword.getText().toString());
        nhanVien.setMALOAINV(3);
        nhanVien.setSENDEMAIL(mSwitchInformation.isChecked() ? true : false);
        return nhanVien;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                onRegisterAccount();
                break;
        }
    }

    private void onRegisterAccount() {
        handelConditionRegiserButton();
        if (!mTextInputLayoutEmail.isErrorEnabled()
                && !mTextInputLayoutFullName.isErrorEnabled()
                && !mTextInputLayoutPass.isErrorEnabled()
                && !mTextInputLayoutRetypePass.isErrorEnabled()) {
            if (!mEditTextPassword.getText().toString().equals(mEditTextRetypePass.getText().toString())) {
                setErrorInputLayout(mTextInputLayoutRetypePass, true, getString(R.string.error_pass_not_match));
                return;
            }
            NhanVien nhanVien = getInfomationNhanVien();
            mDialogManager.showProgressDialog(getString(R.string.message_progress_dialog));
            Keyboard.hideKeyboard(getActivity());
            mRegisterPresenter.registerAccount(getContext(), nhanVien);
            Log.d("LK", nhanVien.getSENDEMAIL() + "");
        }
//        Log.d("LK", " textInputLayoutFullName.isErrorEnabled() :" + textInputLayoutFullName.isErrorEnabled());
    }
}
