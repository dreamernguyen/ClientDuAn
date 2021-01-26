package com.dreamernguyen.ClientDuAn;

import android.util.Log;

import com.dreamernguyen.ClientDuAn.Models.BaiViet;
import com.dreamernguyen.ClientDuAn.Models.NguoiDung;
import com.dreamernguyen.ClientDuAn.Models.ResponseData;
import com.dreamernguyen.ClientDuAn.Retrofit.ApiInterface;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomCall {
    ApiInterface apiInterface;


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
    public void huyAnBaiViet()

}
