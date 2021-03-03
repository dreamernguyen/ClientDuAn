package com.dreamernguyen.ClientDuAn.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dreamernguyen.ClientDuAn.Adapter.MatHangAdapter;
import com.dreamernguyen.ClientDuAn.ApiService;
import com.dreamernguyen.ClientDuAn.DangMatHangActivity;
import com.dreamernguyen.ClientDuAn.Models.DuLieuTraVe;
import com.dreamernguyen.ClientDuAn.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GianHangFragment extends Fragment {
    LinearLayout LayoutThemMatHang;
    RecyclerView rvDanhSach;
    MatHangAdapter matHangAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gian_hang, container, false);

        LayoutThemMatHang = view.findViewById(R.id.LayoutThemMatHang);
        rvDanhSach = view.findViewById(R.id.rvDanhSach);
        matHangAdapter = new MatHangAdapter(getActivity());

        LayoutThemMatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DangMatHangActivity.class);
                intent.putExtra("chucNang","Đăng hàng");
                startActivity(intent);
            }
        });

        loadMatHang();
        rvDanhSach.setAdapter(matHangAdapter);
        rvDanhSach.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return view;
    }

    public void loadMatHang() {
        Call<DuLieuTraVe> call = ApiService.apiService.danhSachMatHang();
        call.enqueue(new Callback<DuLieuTraVe>() {
            @Override
            public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
                matHangAdapter.setData(response.body().getDanhSachMatHang());
            }

            @Override
            public void onFailure(Call<DuLieuTraVe> call, Throwable t) {

            }
        });
    }

}