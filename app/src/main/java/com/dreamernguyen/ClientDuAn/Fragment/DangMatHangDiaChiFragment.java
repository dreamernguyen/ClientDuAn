package com.dreamernguyen.ClientDuAn.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dreamernguyen.ClientDuAn.Activity.DangMatHangActivity;
import com.dreamernguyen.ClientDuAn.R;

public class DangMatHangDiaChiFragment extends Fragment {

    TextView tvThanhPho,tvQuan,tvPhuongXa;
    Button btnToiKhac;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_mat_hang_dia_chi,container,false);

        tvThanhPho=view.findViewById(R.id.tvThanhPho);
        tvQuan=view.findViewById(R.id.tvQuan);
        tvPhuongXa=view.findViewById(R.id.tvPhuongXa);
        btnToiKhac=view.findViewById(R.id.btnToiKhac);

        btnToiKhac.setVisibility(View.GONE);


        tvThanhPho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                denThongTin();
                DangMatHangActivity.ThanhPho="";
                DangMatHangActivity.QuanHuyen="";
                DangMatHangActivity.PhuongXa="";
            }
        });
        tvQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnToiKhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangMatHangActivity.vp2DangMatHang.setCurrentItem(3,false);
                DangMatHangActivity.viTri=3;

            }
        });
        return view;
    }

    @Override
    public void onResume()
    {

        tvThanhPho.setText(DangMatHangActivity.ThanhPho);
        tvQuan.setText(DangMatHangActivity.QuanHuyen);
        tvPhuongXa.setText(DangMatHangActivity.PhuongXa);

        if (!DangMatHangActivity.ThanhPho.isEmpty()&&!DangMatHangActivity.QuanHuyen.isEmpty()){
            btnToiKhac.setVisibility(View.VISIBLE);
        }
        super.onResume();
    }


    public void denThongTin() {
        DangMatHangActivity.vp2DangMatHang.setCurrentItem(5,false);
        DangMatHangActivity.viTri = 2;
    }
}
