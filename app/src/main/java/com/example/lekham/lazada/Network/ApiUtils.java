package com.example.lekham.lazada.Network;

import retrofit2.Retrofit;

/**
 * Created by Le Kham on 6/6/2017.
 */

public class ApiUtils {

    private ApiUtils() {
    }

    public static final String DOMAN = "http://192.168.1.6/lazadaserver";
    public static final String BASE_URL = DOMAN + "/api/";

    public static APIService getApiService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}

