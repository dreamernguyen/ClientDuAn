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

    @POST("/baiviet/huyAn/{id}")
    Call<ResponseData> huyAnBaiViet(@Path("id") String idBaiViet);

    @GET("/baiViet/baiVietBanBe/{id}")
    Call<ResponseData> danhSachBaiVietBanBe(@Path("id") String idNguoiDung);

    @POST("/baiViet/caNhan/{id}")
    Call<ResponseData> xemTrangCaNhanCuaToi (@Path("id") String idNguoiDung);


    //  MatHang
    @POST("/matHang/dangBai")
    Call<ResponseData> themMatHang(@Body MatHang matHang);
    @POST("matHang/xoa/{id}")
    Call<ResponseData> xoaMatHang(@Path("id") String idMatHang);
    @POST("matHang/chinhSua/{id}")
    Call<ResponseData> chinhSuaMatHang(@Body MatHang matHang);
    @GET("/matHang/danhSach")
    Call<ResponseData> danhSachMatHang();
    @POST("/matHang/chiTiet/{id}")
    Call<ResponseData> matHangChiTiet(@Path("id") String idNguoiDung);
    @POST("/matHang/timKiemHangMuc/{tuKhoa}")
    Call<ResponseData> timKiemHangMuc(@Path("tuKhoa") String tuKhoa);
    @POST("/matHang/danhSachToiBan/{id}")
    Call<ResponseData> danhSachToiBan(@Path("id") String idNguoiDung);
    @POST("/matHang/timKiemTieuDe/{tieuDe}")
    Call<ResponseData> timKiemTieuDe(@Path("tieuDe") String tieuDe);
}
