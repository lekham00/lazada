package com.example.lekham.lazada.Model.ListProduct;

import com.example.lekham.lazada.Model.ObjectClass.SanPham;
import com.example.lekham.lazada.Network.APIService;
import com.example.lekham.lazada.Network.ApiUtils;
import com.example.lekham.lazada.Until.JsonParser;
import com.google.gson.JsonElement;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Le Kham on 6/23/2017.
 */

public class ListProductInterator implements ListProductContract.Interator {

    APIService apiService;
    ListProductContract.OnElectronicsListener mOnElectronicsListener;

    public ListProductInterator(ListProductContract.OnElectronicsListener onElectronicsListener) {
        apiService = ApiUtils.getApiService();
        mOnElectronicsListener = onElectronicsListener;
    }

    @Override
    public void performGetListProductBrand(int id, int skip, int take) {
        apiService.getSanPhamBanTheoThuongHieu(id, skip, take).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()) {
                    try {
                        JsonElement jsonElement = response.body();
                        if (jsonElement != null && jsonElement.isJsonArray()) {
                            String jsonString = jsonElement.getAsJsonArray().toString();
                            List<SanPham> sanPhams = JsonParser.parseStringToList(jsonString, SanPham[].class);
                            mOnElectronicsListener.onResultGetListProduct(sanPhams);
                        }
                    } catch (Exception ex) {
                        mOnElectronicsListener.onResultGetListProduct(null);
                    }
                } else {
                    mOnElectronicsListener.onResultGetListProduct(null);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                mOnElectronicsListener.onResultGetListProduct(null);
            }
        });
    }
}
