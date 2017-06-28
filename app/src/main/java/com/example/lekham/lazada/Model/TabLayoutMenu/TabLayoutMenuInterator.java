package com.example.lekham.lazada.Model.TabLayoutMenu;

import android.content.Context;
import android.util.Log;

import com.example.lekham.lazada.Model.ObjectClass.ThucDon;
import com.example.lekham.lazada.Network.APIService;
import com.example.lekham.lazada.Network.ApiUtils;
import com.example.lekham.lazada.Until.JsonParser;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Le Kham on 6/17/2017.
 */

public class TabLayoutMenuInterator implements TabLayoutMenuContract.Interator {

    public static final String TAG = TabLayoutMenuInterator.class.getSimpleName();

    private TabLayoutMenuContract.OnListenerTabLayoutMenu mOnListenerTabLayoutMenu;
    private APIService mApiService;

    public TabLayoutMenuInterator(TabLayoutMenuContract.OnListenerTabLayoutMenu onListenerTabLayoutMenu) {
        mOnListenerTabLayoutMenu = onListenerTabLayoutMenu;
        mApiService = ApiUtils.getApiService();
    }


    @Override
    public void getMenuForTabLayout(Context context) {
        mApiService.getTabLayoutMenu().enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Log.d(TAG, "response: " + response.body().toString());
                if (response.isSuccessful()) {
                    JsonElement jsonElement = response.body();
                    if (jsonElement != null && jsonElement.isJsonArray()) {
                        String jsonString = jsonElement.getAsJsonArray().toString();
                        try {
                            mOnListenerTabLayoutMenu.resultMenuTabLayout(JsonParser.parseStringToList(jsonString, ThucDon[].class));
                        } catch (Exception e) {
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage().toString());
            }
        });
    }
}
