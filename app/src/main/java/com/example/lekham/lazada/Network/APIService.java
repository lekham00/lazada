package com.example.lekham.lazada.Network;

import com.example.lekham.lazada.Model.ObjectClass.NhanVien;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Le Kham on 6/6/2017.
 */

public interface APIService {
    @POST("nhanvien/add")
    @FormUrlEncoded
    Call<POST> registerAccount(@Body NhanVien nhanVien);
}
