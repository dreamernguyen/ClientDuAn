package com.dreamernguyen.ClientDuAn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamernguyen.ClientDuAn.Adapter.AnhBaiVietAdapter;
import com.dreamernguyen.ClientDuAn.Adapter.BinhLuanAdapter;
import com.dreamernguyen.ClientDuAn.Models.BaiViet;
import com.dreamernguyen.ClientDuAn.Models.BinhLuan;
import com.dreamernguyen.ClientDuAn.Models.DuLieuTraVe;
import com.dreamernguyen.ClientDuAn.Models.NguoiDung;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiVietChiTietActivity extends AppCompatActivity {
    TextView tvNoiDung,tvTenNguoiDung;
    List<String> listAnh = new ArrayList<>();
    AnhBaiVietAdapter anhBaiVietAdapter;
    BinhLuanAdapter binhLuanAdapter;
    ViewPager vpgAnh;
    String idBaiViet;
    RecyclerView rvBinhLuan;
    ImageView btnGui;
    EditText edBinhLuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_viet_chi_tiet);
        tvNoiDung = findViewById(R.id.tvNoiDung);
        tvTenNguoiDung = findViewById(R.id.tvTenNguoiDung);
        vpgAnh = findViewById(R.id.vpgImage);
        binhLuanAdapter = new BinhLuanAdapter(this);
        rvBinhLuan = findViewById(R.id.rvBinhLuan);
        btnGui = findViewById(R.id.btnGui);

        edBinhLuan = findViewById(R.id.edBinhLuan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvBinhLuan.setLayoutManager(linearLayoutManager);
        Intent i = getIntent();
        if(i.getStringExtra("chucNang").equals("Xem")){
            idBaiViet = i.getStringExtra("idBaiViet");
            loadChiTiet(idBaiViet);
            loadBinhLuan(idBaiViet);
            btnGui.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binhLuan(idBaiViet);
                }
            });
        }else {
            Toast.makeText(this, "Tạo bài mới", Toast.LENGTH_SHORT).show();
        }




    }
    private void loadChiTiet(String idBaiViet){
        Call<DuLieuTraVe> call = ApiService.apiService.xemChiTiet(idBaiViet);
        call.enqueue(new Callback<DuLieuTraVe>() {
            @Override
            public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
                BaiViet baiViet = response.body().getBaiViet();
                tvTenNguoiDung.setText(baiViet.getIdNguoiDung().getHoTen());
                tvNoiDung.setText(baiViet.getNoiDung());
                if(baiViet.getLinkAnh().size() > 0){
                    listAnh = baiViet.getLinkAnh();
                    anhBaiVietAdapter = new AnhBaiVietAdapter(listAnh);
                    vpgAnh.setVisibility(View.VISIBLE);
                    vpgAnh.setAdapter(anhBaiVietAdapter);
                    Log.d("ndndn", "onResponse: "+baiViet.getLinkAnh());
                }else {
                    vpgAnh.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<DuLieuTraVe> call, Throwable t) {
                Toast.makeText(BaiVietChiTietActivity.this, "Lỗi" +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loadBinhLuan(String idBaiViet){
        Call<DuLieuTraVe> call = ApiService.apiService.danhSachBinhLuan(idBaiViet);
        call.enqueue(new Callback<DuLieuTraVe>() {
            @Override
            public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
                List<BinhLuan> listBinhLuan = response.body().getDanhSachBinhLuan();
                Log.d("ssss", "onResponse: "+listBinhLuan);
                if(listBinhLuan.size() > 0){
                    binhLuanAdapter.setData(listBinhLuan);
                    rvBinhLuan.setAdapter(binhLuanAdapter);
                }

            }

            @Override
            public void onFailure(Call<DuLieuTraVe> call, Throwable t) {
                Toast.makeText(BaiVietChiTietActivity.this, "Lỗi" +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void binhLuan(String idBaiViet){
        BinhLuan binhLuan = new BinhLuan(idBaiViet,edBinhLuan.getText().toString());
        Call<DuLieuTraVe> call = ApiService.apiService.binhLuan("600f9186587aed14d0b62f46",binhLuan);
        call.enqueue(new Callback<DuLieuTraVe>() {
            @Override
            public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
                Toast.makeText(BaiVietChiTietActivity.this, ""+response.body().getThongBao(), Toast.LENGTH_SHORT).show();
                loadBinhLuan(idBaiViet);
            }

            @Override
            public void onFailure(Call<DuLieuTraVe> call, Throwable t) {
                Toast.makeText(BaiVietChiTietActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}