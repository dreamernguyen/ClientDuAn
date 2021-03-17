package com.dreamernguyen.ClientDuAn;

import android.util.Log;

import com.dreamernguyen.ClientDuAn.Models.BaiViet;
import com.dreamernguyen.ClientDuAn.Models.MatHang;
import com.dreamernguyen.ClientDuAn.Models.NguoiDung;
import com.dreamernguyen.ClientDuAn.Models.ResponseData;
import com.dreamernguyen.ClientDuAn.Retrofit.ApiInterface;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomCall {
    ApiInterface apiInterface;


//    BaiViet
    public void danhSachBaiViet() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseData> callDanhSachBaiViet = apiInterface.danhSachBaiViet();
        callDanhSachBaiViet.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                Log.d("TAG", "onResponse: " + response.body().getThongBao());
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });
    }

    public void dangBaiViet(BaiViet baiViet, String idNguoiDung) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseData> callDangBaiViet = apiInterface.dangBaiViet(idNguoiDung,baiViet);

        callDangBaiViet.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                ResponseData responseData = response.body();
                Log.d("TAG", "onResponse2: "+responseData.getThongBao());
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });

    }


    public void xoaBaiViet(String idBaiViet) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseData> callXoaBaiViet = apiInterface.xoaBaiViet(idBaiViet);
        callXoaBaiViet.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                Log.d("TAG", "onResponse: " + response.body().getThongBao());

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());

            }
        });
    }
    public void anBaiViet(String idBaiViet){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseData> callAnBaiViet= apiInterface.anBaiViet(idBaiViet);
        callAnBaiViet.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                Log.d("TAG", "onResponse: "+response.body().getThongBao());
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());

            }
        });

    }
    public void huyAnBaiViet( String idBaiViet ){
        apiInterface=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseData> callHuyAnBaiViet = apiInterface.huyAnBaiViet(idBaiViet);
        callHuyAnBaiViet.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                Log.d("TAG", "onResponse: "+response.body().getThongBao());

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());

            }
        });

    }

    public void danhSachBaiVietBanBe(String idNguoiDung){
        apiInterface=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseData> callDanhSachBaiVietBanBe = apiInterface.danhSachBaiVietBanBe(idNguoiDung);
        callDanhSachBaiVietBanBe.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                Log.d("TAG", "onResponse: "+response.body().getBaiVietList());


            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());

            }
        });
    }
    public void xemTrangCaNhanCuaToi(String idNguoiDung){
        apiInterface=ApiClient.getClient().create(ApiInterface.class);
        Call <ResponseData> callXemTrangCaNhanCuaToi=apiInterface.xemTrangCaNhanCuaToi("6006875981484b2c7c2176c5");
        callXemTrangCaNhanCuaToi.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                Log.d("TAG", "onResponse: "+response.body().getThongBao());
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });
    }
//    MatHang

    public void themMatHang(MatHang matHang){
        apiInterface=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseData> callThemMatHang=apiInterface.themMatHang(matHang);
        callThemMatHang.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });
    }
    public void xoaMatHang(String idMatHang){
        apiInterface=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseData> callXoaMatHang=apiInterface.xoaMatHang(idMatHang);
        callXoaMatHang.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });

    }
    public void chinhSuaMatHang(MatHang matHang){
        apiInterface=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseData> callChinhSuaMatHang=apiInterface.chinhSuaMatHang(matHang);
        callChinhSuaMatHang.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });


    }
    public void danhSachMatHang(){
        apiInterface=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseData> callDanhSachMatHang= apiInterface.danhSachMatHang();
        callDanhSachMatHang.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });
    }
    public void matHangChiTiet(String idNguoiDung){
        apiInterface=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseData>callMatHangChiTiet =apiInterface.matHangChiTiet(idNguoiDung);
        callMatHangChiTiet.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });
    }
    public void timKiemHangMuc(String tuKhoa){
        apiInterface=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseData> callTimKiemHangMuc = apiInterface.timKiemHangMuc(tuKhoa);
        callTimKiemHangMuc.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });
    }
    public void danhSachToiBan(String idNguoiDung){
        apiInterface=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseData> callDanhSachToiBan =apiInterface.danhSachToiBan(idNguoiDung);
        callDanhSachToiBan.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });
    }
    public void timKiemTieuDe(String tieuDe){
        apiInterface=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseData> callTimKiemTieuDe=apiInterface.timKiemTieuDe(tieuDe);
        callTimKiemTieuDe.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });
    }
}
