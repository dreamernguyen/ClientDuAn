package com.dreamernguyen.ClientDuAn.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamernguyen.ClientDuAn.ApiService;
import com.dreamernguyen.ClientDuAn.LocalDataManager;
import com.dreamernguyen.ClientDuAn.MainActivity;
import com.dreamernguyen.ClientDuAn.Models.DuLieuTraVe;
import com.dreamernguyen.ClientDuAn.Models.NguoiDung;
import com.dreamernguyen.ClientDuAn.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapActivity extends AppCompatActivity {
    TextView btnDangNhap, btnDangKy;
    EditText edSdt, edMatKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edSdt = findViewById(R.id.edtSdt);
        edMatKhau = findViewById(R.id.edtMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.tvDangKy);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(i);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkForm()){
                    DangNhap();
                }
            }
        });
    }
    public void DangNhap(){
        Toast.makeText(this, "hihiih", Toast.LENGTH_SHORT).show();
        String mMatKhau = edMatKhau.getText().toString();
        int mSdt = Integer.parseInt(edSdt.getText().toString());
        NguoiDung nguoiDung = new NguoiDung(mSdt,mMatKhau);
        Call<DuLieuTraVe> call = ApiService.apiService.dangNhap(nguoiDung);
        call.enqueue(new Callback<DuLieuTraVe>() {
            @Override
            public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
                String thongBao = response.body().getThongBao();
                NguoiDung nguoiDung = response.body().getNguoiDung();
                if (thongBao.equals("Tài khoản không tồn tại !") ){
                    edSdt.setBackground(getDrawable(R.drawable.edit_text_fail));
                    edSdt.setPadding(25, 0,25,0);
                    edSdt.setError(thongBao);
                }if (thongBao.equals("Sai mật khẩu !")){
                    edMatKhau.setBackground(getDrawable(R.drawable.edit_text_fail));
                    edMatKhau.setPadding(25, 0,25,0);
                    edMatKhau.setError(thongBao);
                } else {
                    Toast.makeText(DangNhapActivity.this, "vvvvvvv"+thongBao, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    LocalDataManager.setIdNguoiDung(nguoiDung.getId());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<DuLieuTraVe> call, Throwable t) {
                Toast.makeText(DangNhapActivity.this, "Error vvvvv", Toast.LENGTH_SHORT).show();
                Log.d("ddđ", "onFailure: "+t.getMessage());
            }
        });
    }

    public Boolean checkForm(){
        if (edSdt.getText().toString().trim().length() == 0){
            edSdt.setHint("Chưa nhập số điện thoại !");
            edSdt.setBackground(getDrawable(R.drawable.edit_text_fail));
            edSdt.setPadding(25, 0,25,0);
            edSdt.setError("!");
            return false;
        } if (edSdt.getText().toString().trim().length() < 10 || edSdt.getText().toString().trim().length() > 10){
            edSdt.setBackground(getDrawable(R.drawable.edit_text_fail));
            edSdt.setPadding(25, 0,25,0);
            edSdt.setError("Yêu cầu nhập đúng định dạng !");
            return false;
        } if (edMatKhau.getText().toString().trim().length() == 0){
            edMatKhau.setHint("Yêu cầu nhập mật khẩu");
            edMatKhau.setBackground(getDrawable(R.drawable.edit_text_fail));
            edMatKhau.setPadding(25, 0,25,0);
            edMatKhau.setError("!");
            return false;
        } if (edMatKhau.getText().toString().trim().length() < 8){
            edMatKhau.setBackground(getDrawable(R.drawable.edit_text_fail));
            edMatKhau.setPadding(25, 0,25,0);
            edMatKhau.setError("Mật khẩu phải lớn hơn 8 ký tự !");
            return false;
        }else {
            edSdt.setBackground(getDrawable(R.drawable.edit_text_success));
            edMatKhau.setBackground(getDrawable(R.drawable.edit_text_success));
            return true;
        }
    }

}