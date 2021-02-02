package com.dreamernguyen.ClientDuAn;

import com.dreamernguyen.ClientDuAn.Models.BaiViet;
import com.dreamernguyen.ClientDuAn.Models.DuLieuTraVe;
import com.dreamernguyen.ClientDuAn.Models.MatHang;
import com.dreamernguyen.ClientDuAn.Models.NguoiDung;
import com.dreamernguyen.ClientDuAn.Models.TinNhan;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.12:3000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    //Người dùng
    @FormUrlEncoded
    @POST("nguoiDung/theoDoi")
    Call<DuLieuTraVe> theoDoi(@Field("idNguoi1")String idNguoi1, @Field("idNguoi2") String idNguoi2);
    @FormUrlEncoded
    @POST("nguoiDung/huyTheoDoi")
    Call<DuLieuTraVe> huyTheoDoi(@Field("idNguoi1")String idNguoi1,@Field("idNguoi2") String idNguoi2);

    //Bài viết
    @GET("baiViet/danhSach")
    Call<DuLieuTraVe> danhSachBaiViet();
    @GET("baiViet/dangTheoDoi/{id}")
    Call<DuLieuTraVe> baiVietTheoDoi(@Path("id")String idNguoiDung);
    @POST("baiViet/dangBai/{id}")
    Call<DuLieuTraVe> dangBai(@Path("id") String idNguoiDung,@Body BaiViet baiViet);
    @POST("baiViet/xoa/{id}")
    Call<DuLieuTraVe> xoaBaiViet(@Path("id") String idBaiViet);
    @POST("baiViet/an/{id}")
    Call<DuLieuTraVe> anBaiViet(@Path("id") String idBaiViet);

    //Bình luận

    //Tin nhắn
    @POST("/tinNhan/chat")
    Call<TinNhan> postMessage(@Body TinNhan tinNhan);
    @GET("tinNhan/danhSach")
    Call<List<TinNhan>> getTest();

    //Mặt hàng
    @POST("matHang/dangBai")
    Call<DuLieuTraVe> testMatHang (@Path("idNguoiDung") String idNguoiDung, @Body MatHang matHang);
    @GET("matHang/danhSach")
    Call<DuLieuTraVe> danhSachMatHang();
    @POST("matHang/chiTiet/{id}")
    Call<DuLieuTraVe> matHangChiTiet(@Path("id")String idMatHang);

}
