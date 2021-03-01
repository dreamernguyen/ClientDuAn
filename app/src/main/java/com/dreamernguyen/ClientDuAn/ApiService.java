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
            .baseUrl("http://192.168.0.114:3000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    //Người dùng
    
    //Bài viết
    @GET("baiViet/danhSach")
    Call<List<BaiViet>> danhSachBaiViet();
    @POST("baiViet/dangBai/{id}")
    Call<BaiViet> dangBai(@Path("id") String idNguoiDung,@Body BaiViet baiViet);
    @POST("baiViet/capNhat/{id}")
    Call<DuLieuTraVe> capNhatBaiViet(@Path("id") String idBaiViet,@Body BaiViet baiViet);
    @GET("baiViet/chiTiet/{id}")
    Call<DuLieuTraVe> xemChiTiet(@Path("id") String idBaiViet);
    @POST("baiViet/an/{id}")
    Call<DuLieuTraVe> anBaiViet(@Path("id") String id);
    @POST("baiViet/xoa/{id}")
    Call<DuLieuTraVe> xoaBaiViet(@Path("id") String id);


    //Bình luận
    //Tin nhắn
    @POST("/tinNhan/chat")
    Call<TinNhan> postMessage(@Body TinNhan tinNhan);
    @GET("tinNhan/danhSach")
    Call<List<TinNhan>> getTest();
    //Mặt hàng
    @POST("matHang/dangBai")
    Call<MatHang> testMatHang (@Path("idNguoiDung") String idNguoiDung, @Body MatHang matHang);

}
