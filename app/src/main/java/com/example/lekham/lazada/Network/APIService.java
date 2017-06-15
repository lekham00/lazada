package com.example.lekham.lazada.Network;

import com.example.lekham.lazada.Model.ObjectClass.NhanVien;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

/**
 * Created by Le Kham on 6/6/2017.
 */

public interface APIService {
    @POST("nhanvien/add")
    Call<JsonElement> registerAccount(@Body JsonElement jsonElement);

    @POST("nhanvien/dangnhap")
    Call<JsonElement> loginAccount(@Body JsonElement jsonElement);

    @GET("thuonghieu/thuonghieuyeuthich")
    Call<JsonElement> getThuongHieuYeuThich();
}
