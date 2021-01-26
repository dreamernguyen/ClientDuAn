package com.dreamernguyen.ClientDuAn.Retrofit;

import com.dreamernguyen.ClientDuAn.Models.BaiViet;
import com.dreamernguyen.ClientDuAn.Models.BinhLuan;
import com.dreamernguyen.ClientDuAn.Models.MatHang;
import com.dreamernguyen.ClientDuAn.Models.NguoiDung;
import com.dreamernguyen.ClientDuAn.Models.ResponseData;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    //  NguoiDung
    @GET("/nguoiDung/danhSach")
    Call<ResponseData> danhSachNguoiDung();

    @POST("/nguoiDung/dangKy")
    Call<ResponseData> dangKyNguoiDung(@Body NguoiDung nguoiDung);

    //  BinhLuan
    @GET("/binhLuan/danhSach")
    Call<ResponseData> danhSachBinhLuan();

    @POST("/binhLuan/binhLuan")
    Call<ResponseData> binhLuan(@Body BinhLuan binhLuan);

    //  BaiViet
    @GET("/baiViet/danhSach")
    Call<ResponseData> danhSachBaiViet();


    @POST("/baiViet/dangBai/{id}")
    Call<ResponseData> dangBaiViet( @Path("id") String idNguoiDung , @Body BaiViet baiViet);

    @POST("/baiViet/xoa/{id}")
    Call<ResponseData> xoaBaiViet(@Path("id") String idBaiViet);

    @POST("/baiViet/an/{id}")
    Call<ResponseData> anBaiViet(@Path("id") String idBaiViet);

    @GET("/baiViet/danhSachAn/{id}")
    Call<ResponseData> danhSachBaiVietAn(@Path("id") String idNguoiDung);

    //  MatHang
    @GET("/matHang/danhSach")
    Call<List<MatHang>> danhSachMatHang();
}
