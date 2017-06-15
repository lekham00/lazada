package com.example.lekham.lazada.Model.Main.ActionMenu.Fragment.Electronics;

import android.util.Log;

import com.example.lekham.lazada.Model.ObjectClass.ThuongHieu;
import com.example.lekham.lazada.Network.APIService;
import com.example.lekham.lazada.Network.ApiUtils;
import com.example.lekham.lazada.Until.JsonParser;
import com.google.gson.JsonElement;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Le Kham on 6/14/2017.
 */

public class ElectronicsInterator implements ElectronicsContract.Interator {

    APIService mApiService;
    private ElectronicsContract.OnElectronicsListener mElectronicsListener;

    public ElectronicsInterator(ElectronicsContract.OnElectronicsListener onElectronicsListener) {
        mApiService = ApiUtils.getApiService();
        mElectronicsListener = onElectronicsListener;
    }

    @Override
    public void performFetListBrand() {

        mApiService.getThuongHieuYeuThich().enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Log.d("LK", "onResponse");
                if (response.isSuccessful()) {
                    JsonElement jsonElement = response.body();

                    if (jsonElement != null && jsonElement.isJsonArray()) {
                        String jsonString = jsonElement.getAsJsonArray().toString();
                        try {
                            List<ThuongHieu> thuongHieus = JsonParser.parseStringToList(jsonString,ThuongHieu[].class
                            );
                            mElectronicsListener.onResultGetListBrand(thuongHieus);
                        } catch (Exception e) {
                            e.printStackTrace();
                            mElectronicsListener.onResultGetListBrand(null);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.d("LK", "onFailure :" + t.getMessage().toString());
                mElectronicsListener.onResultGetListBrand(null);
            }
        });
    }
}
