package com.example.lekham.lazada.Presenter.Main.Menu.Account.Login.Register;

import com.example.lekham.lazada.Model.ObjectClass.NhanVien;
import com.example.lekham.lazada.Network.APIService;
import com.example.lekham.lazada.Network.ApiUtils;
import com.example.lekham.lazada.View.Account.Register.ViewRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

/**
 * Created by Le Kham on 6/7/2017.
 */

public class PresenterLogicRegister implements IPresenterRegister {

    private ViewRegister mViewRegister;
    APIService mApiService;

    public PresenterLogicRegister(ViewRegister viewRegister) {
        mViewRegister = viewRegister;
        mApiService = ApiUtils.getApiService();
    }

    @Override
    public void registerAccount(NhanVien nhanVien) {
        if (mApiService != null) {
            mApiService.registerAccount(nhanVien).enqueue(new Callback<POST>() {
                @Override
                public void onResponse(Call<POST> call, Response<POST> response) {
                    if (response.isSuccessful()) {
                        mViewRegister.onRegisterSuccess(response.message().toString());
                    }
                }

                @Override
                public void onFailure(Call<POST> call, Throwable t) {
                    mViewRegister.onRegisterSuccess(t.getMessage().toString());
                }
            });
        }
    }
}
