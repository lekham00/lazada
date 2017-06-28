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
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Le Kham on 6/6/2017.
 */

public interface APIService {
    @POST("nhanvien/add")
    Call<JsonElement> registerAccount(@Body JsonElement jsonElement);

    @POST("nhanvien/dangnhap")
    Call<JsonElement> loginAccount(@Body JsonElement jsonElement);

    @GET("thuonghieu/thuonghieuyeuthichtheoma/{id}")
    Call<JsonElement> getThuongHieuYeuThich(@Path("id") int id);

    @GET("thucdon/laydanhsachthucdon")
    Call<JsonElement> getTabLayoutMenu();

    @GET("sanpham/sanphambanchay/{id}")
    Call<JsonElement> getSanPhamBanChay(@Path("id") int id);

    @GET("sanpham/sanphambantheothuonghieu/{id}/{skip}/{take}")
    Call<JsonElement> getSanPhamBanTheoThuongHieu(@Path("id") int id, @Path("skip") int skip, @Path("take") int take);

    @GET("sanpham/sanphamtheoma/{id}")
    Call<JsonElement> getSanPhamTheoMa(@Path("id") int id);
}
