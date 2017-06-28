package com.example.lekham.lazada.Model.DetailProduct;

import com.example.lekham.lazada.Model.ObjectClass.SanPham;
import com.example.lekham.lazada.Network.APIService;
import com.example.lekham.lazada.Network.ApiUtils;
import com.example.lekham.lazada.Until.JsonParser;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Le Kham on 6/28/2017.
 */

public class DetailProductInterator implements DetailProductContract.Interator {


    private DetailProductContract.OnDetailProductListener mOnDetailProductListener;
    private APIService mApiService;

    public DetailProductInterator(DetailProductContract.OnDetailProductListener onDetailProductListene) {
        mOnDetailProductListener = onDetailProductListene;
        mApiService = ApiUtils.getApiService();
    }

    @Override
    public void performgetDetailProduct(int id) {
        mApiService.getSanPhamTheoMa(id).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if(response.isSuccessful())
                {
                    JsonElement jsonElement = response.body();
                    if(jsonElement != null && jsonElement.isJsonObject())
                    {
                        String jsonString  = jsonElement.getAsJsonObject().toString();
                        try {
                            SanPham sanPham = JsonParser.fromJson(jsonString,SanPham.class);
                            mOnDetailProductListener.resultDetailProduct(sanPham);
                        } catch (Exception e) {
                            e.printStackTrace();
                            mOnDetailProductListener.resultDetailProduct(null);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                mOnDetailProductListener.resultDetailProduct(null);
            }
        });
    }
}
