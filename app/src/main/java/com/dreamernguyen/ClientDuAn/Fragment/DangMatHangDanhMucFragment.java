package com.dreamernguyen.ClientDuAn.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dreamernguyen.ClientDuAn.Activity.DangMatHangActivity;
import com.dreamernguyen.ClientDuAn.R;

public class DangMatHangDanhMucFragment extends Fragment {

    TextView tv, tvDanhMuc, tvDanhMucCon;
    Button btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_mat_hang_danh_muc, container, false);

        tv = view.findViewById(R.id.tv);
        tvDanhMuc = view.findViewById(R.id.tvDanhMuc);
        tvDanhMucCon = view.findViewById(R.id.tvDanhMucCon);
        btn = view.findViewById(R.id.btn);

        tvDanhMuc.setText(DangMatHangActivity.DanhMuc);
        tvDanhMucCon.setText(DangMatHangActivity.DanhMucCon);

        if (DangMatHangActivity.DanhMuc.isEmpty()) {
            btn.setVisibility(View.GONE);

        } else {
            btn.setVisibility(View.VISIBLE);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangMatHangActivity.vp2DangMatHang.setCurrentItem(1,false);
                DangMatHangActivity.viTri=1;
                
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                denThongTin();
            }
        });

        tvDanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                denThongTin();
                DangMatHangActivity.DanhMuc="";
                DangMatHangActivity.DanhMucCon="";
            }
        });

        return view;
    }

    @Override
    public void onResume() {


        tvDanhMuc.setText(DangMatHangActivity.DanhMuc);
        tvDanhMucCon.setText(DangMatHangActivity.DanhMucCon);
        super.onResume();
    }

    public void denThongTin() {
        DangMatHangActivity.vp2DangMatHang.setCurrentItem(5,false);
        DangMatHangActivity.viTri = 0;
    }
}
