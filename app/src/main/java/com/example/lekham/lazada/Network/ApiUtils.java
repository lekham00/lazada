package com.example.lekham.lazada.Network;

import retrofit2.Retrofit;

/**
 * Created by Le Kham on 6/6/2017.
 */

public class ApiUtils {

    private ApiUtils() {
    }

    public static final String BASE_URL = "http://192.168.1.8/lazadaserver/api/";

    public static APIService getApiService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
