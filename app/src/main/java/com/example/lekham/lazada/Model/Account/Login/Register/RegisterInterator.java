package com.example.lekham.lazada.Model.Account.Login.Register;

import android.content.Context;
import android.util.Log;

import com.example.lekham.lazada.Manager.DialogManager;
import com.example.lekham.lazada.Model.ObjectClass.NhanVien;
import com.example.lekham.lazada.Network.APIService;
import com.example.lekham.lazada.Network.ApiUtils;
import com.example.lekham.lazada.Until.JsonParser;
import com.example.lekham.lazada.Until.SharePre;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

/**
 * Created by Le Kham on 6/7/2017.
 */

public class RegisterInterator implements RegisterContract.Interator {

    public static final String TAG = RegisterInterator.class.getSimpleName();

    private RegisterContract.OnRegisterListener mOnRegisterListener;
    APIService mApiService;

    public RegisterInterator(RegisterContract.OnRegisterListener onRegisterListener) {
        mOnRegisterListener = onRegisterListener;
        mApiService = ApiUtils.getApiService();
    }


    @Override
    public void performRegisterAccount(final Context context, NhanVien nhanVien) {
        if (mApiService != null) {
            mApiService.registerAccount(JsonParser.toJsonElement(nhanVien)).enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    Log.d(TAG, "onResponse");
                    if (response.isSuccessful()) {
                        JsonElement jsonElement = response.body();
                        if (jsonElement != null && jsonElement.isJsonObject()) {
                            String json = jsonElement.getAsJsonObject().toString();
                            if (json != null) {
                                try {
                                    NhanVien parserNhanVien = JsonParser.fromJson(json, NhanVien.class);
                                    SharePre.instantSharePre(context).setValueString(SharePre.KEY_ACCOUNT, parserNhanVien.getHOTENNV());
                                    mOnRegisterListener.onSuccess();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    mOnRegisterListener.onFailuer();
                                }
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    Log.d(TAG, "onFailure :" + t.getMessage().toString());
                    mOnRegisterListener.onFailuer();
                }
            });
        }
    }
}
