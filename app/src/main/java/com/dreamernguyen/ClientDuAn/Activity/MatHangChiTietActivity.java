package com.dreamernguyen.ClientDuAn.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dreamernguyen.ClientDuAn.ApiService;
import com.dreamernguyen.ClientDuAn.Models.DuLieuTraVe;
import com.dreamernguyen.ClientDuAn.Models.MatHang;
import com.dreamernguyen.ClientDuAn.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatHangChiTietActivity extends AppCompatActivity {

    ImageView imgHinhAnh;
    TextView tvTieuDe, tvGiaBan, tvDiaChi, tvNoiDung;
    public static String idMatHangChiTiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat_hang_chi_tiet);

        imgHinhAnh = findViewById(R.id.imgHinhAnh);
        tvTieuDe = findViewById(R.id.tvTieuDeChiTiet);
        tvGiaBan = findViewById(R.id.tvGiaChiTiet);
        tvDiaChi = findViewById(R.id.tvDiaChiChiTiet);
        tvNoiDung = findViewById(R.id.tvNoiDungChiTiet);

        Call<DuLieuTraVe> call = ApiService.apiService.xemChiTietMatHang(idMatHangChiTiet);
        call.enqueue(new Callback<DuLieuTraVe>() {
            @Override
            public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
                MatHang matHang = response.body().getMatHang();
                Glide.with(getApplicationContext()).load(matHang.getLinkAnh().get(0)).into(imgHinhAnh);
                tvTieuDe.setText(matHang.getTieuDe());
                String test = "da nang - cam le - hoa an";

                String thanhPho = test.replaceAll( "(?=\\-)(.*?)$", "");
                String quan = test.replaceAll( "(?<=\\-)(.*?)(?=\\-)", "");

                tvDiaChi.setText(thanhPho);
                tvGiaBan.setText(matHang.getGiaBan() + "");
                tvNoiDung.setText(matHang.getNoiDung());
            }

            @Override
            public void onFailure(Call<DuLieuTraVe> call, Throwable t) {

            }
        });
    }
}