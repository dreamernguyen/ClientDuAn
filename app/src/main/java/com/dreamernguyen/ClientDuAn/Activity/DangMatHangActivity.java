package com.dreamernguyen.ClientDuAn.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.dreamernguyen.ClientDuAn.Adapter.DangMatHangAdapter;
import com.dreamernguyen.ClientDuAn.R;

import java.util.List;

public class DangMatHangActivity extends AppCompatActivity {
    DangMatHangAdapter dangMatHangAdapter;

    public static ViewPager2 vp2DangMatHang;
    public static List<String> linkAnh;
    public static String HangMuc = "";
    public static String DanhMuc = "";
    public static String DanhMucCon = "";
    public static String tieuDe = "";
    public static String noiDung = "";
    public static String DiaChi = "";
    public static String ThanhPho = "";
    public static String QuanHuyen = "";
    public static String PhuongXa = "";

    public static int giaBan = 0;
    public static int viTri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_mat_hang);


        dangMatHangAdapter = new DangMatHangAdapter(this);
        vp2DangMatHang = findViewById(R.id.vp2DangMatHang);
        vp2DangMatHang.setAdapter(dangMatHangAdapter);
        vp2DangMatHang.setUserInputEnabled(false);

        if (DanhMuc.isEmpty()) {

        }

    }

    public void bak(View view) {
        if (vp2DangMatHang.getCurrentItem() == 0) {
            onBackPressed();
        } else if (vp2DangMatHang.getCurrentItem() ==5){
            vp2DangMatHang.setCurrentItem(viTri,false);
        }
        else {

            viTri = viTri - 1;
            vp2DangMatHang.setCurrentItem(viTri,false);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }
}




