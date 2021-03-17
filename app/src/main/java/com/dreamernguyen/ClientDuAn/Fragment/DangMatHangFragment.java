package com.dreamernguyen.ClientDuAn.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dreamernguyen.ClientDuAn.Activity.DangMatHangActivity;
import com.dreamernguyen.ClientDuAn.Adapter.MatHangHinhAnhAdapter;
import com.dreamernguyen.ClientDuAn.ApiService;
import com.dreamernguyen.ClientDuAn.LocalDataManager;
import com.dreamernguyen.ClientDuAn.Models.DuLieuTraVe;
import com.dreamernguyen.ClientDuAn.Models.MatHang;
import com.dreamernguyen.ClientDuAn.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangMatHangFragment extends Fragment {

    TextView tvTieuDe, tvGiaBan, tvNoiDung, tvHangMuc, tvDiaChi;
    RecyclerView rv;
    Button btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_mat_hang, container, false);

        tvTieuDe = view.findViewById(R.id.tvTieuDe);
        tvGiaBan = view.findViewById(R.id.tvGiaBan);
        tvNoiDung = view.findViewById(R.id.tvNoiDung);
        tvHangMuc = view.findViewById(R.id.tvHangMuc);
        tvDiaChi = view.findViewById(R.id.tvDiaChi);
        rv = view.findViewById(R.id.rv);
        btn = view.findViewById(R.id.btn);


        MatHangHinhAnhAdapter matHangHinhAnhAdapter= new MatHangHinhAnhAdapter(getActivity());
        matHangHinhAnhAdapter.setListString(DangMatHangActivity.linkAnh);
        rv.setAdapter(matHangHinhAnhAdapter);
        rv.setLayoutManager(new GridLayoutManager(getActivity(),3));

        return view;
    }

    @Override
    public void onResume() {

        tvTieuDe.setText(DangMatHangActivity.tieuDe);
        tvDiaChi.setText(DangMatHangActivity.ThanhPho+" - "+DangMatHangActivity.QuanHuyen+" - "+DangMatHangActivity.PhuongXa);
        tvGiaBan.setText(DangMatHangActivity.giaBan+"");
        tvHangMuc.setText(DangMatHangActivity.DanhMuc+" - "+DangMatHangActivity.DanhMucCon);
        tvNoiDung.setText(DangMatHangActivity.noiDung);
        super.onResume();
    }
    public void aa(){

    }
}
