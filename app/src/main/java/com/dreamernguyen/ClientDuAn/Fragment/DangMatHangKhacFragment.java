package com.dreamernguyen.ClientDuAn.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dreamernguyen.ClientDuAn.Activity.DangMatHangActivity;
import com.dreamernguyen.ClientDuAn.R;

public class DangMatHangKhacFragment extends Fragment {

    EditText edGia, edTieuDe, edNoiDung;
    Button btn,btn1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_mat_hang_khac, container, false);

        edGia = view.findViewById(R.id.edGia);
        edTieuDe = view.findViewById(R.id.edTieuDe);
        edNoiDung = view.findViewById(R.id.edNoiDung);
        btn = view.findViewById(R.id.btn);
        btn1=view.findViewById(R.id.btn1);

        btn.setVisibility(View.GONE);


        if (DangMatHangActivity.giaBan != 0 && !DangMatHangActivity.tieuDe.isEmpty()&&!DangMatHangActivity.tieuDe.isEmpty()){
            btn.setVisibility(View.VISIBLE);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangMatHangActivity.vp2DangMatHang.setCurrentItem(4,false);
                DangMatHangActivity.viTri=4;
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a();

                DangMatHangActivity.vp2DangMatHang.setCurrentItem(4,false);
                DangMatHangActivity.viTri=4;
            }
        });
        return view;
    }

    @Override
    public void onResume() {

        if (DangMatHangActivity.giaBan != 0 && !DangMatHangActivity.tieuDe.isEmpty()&&!DangMatHangActivity.tieuDe.isEmpty()){
            btn.setVisibility(View.VISIBLE);
        }
        super.onResume();
    }
    public void a(){

        DangMatHangActivity.giaBan = Integer.parseInt(edGia.getText().toString());
        DangMatHangActivity.tieuDe = edTieuDe.getText().toString();
        DangMatHangActivity.noiDung = edNoiDung.getText().toString();
    }
}
