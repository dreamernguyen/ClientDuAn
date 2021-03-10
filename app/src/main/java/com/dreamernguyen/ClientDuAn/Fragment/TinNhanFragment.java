package com.dreamernguyen.ClientDuAn.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dreamernguyen.ClientDuAn.Adapter.NguoiDungAdapter;
import com.dreamernguyen.ClientDuAn.ApiService;
import com.dreamernguyen.ClientDuAn.LocalDataManager;
import com.dreamernguyen.ClientDuAn.Models.DuLieuTraVe;
import com.dreamernguyen.ClientDuAn.Models.NguoiDung;
import com.dreamernguyen.ClientDuAn.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TinNhanFragment extends Fragment {
    RecyclerView rvLienHe;
    NguoiDungAdapter nguoiDungAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tin_nhan, container, false);
        rvLienHe = view.findViewById(R.id.rvLienHe);
        nguoiDungAdapter = new NguoiDungAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvLienHe.setLayoutManager(linearLayoutManager);
        rvLienHe.setAdapter(nguoiDungAdapter);
        loadLienHe();
        return view;
    }
    public void loadLienHe(){
        Call<List<NguoiDung>> call = ApiService.apiService.danhSachLienHe(LocalDataManager.getIdNguoiDung());
        call.enqueue(new Callback<List<NguoiDung>>() {
            @Override
            public void onResponse(Call<List<NguoiDung>> call, Response<List<NguoiDung>> response) {
                nguoiDungAdapter.setData(response.body());
                Log.d("---", "onResponse: "+response.body().get(0).getHoTen());
            }

            @Override
            public void onFailure(Call<List<NguoiDung>> call, Throwable t) {
                Log.d("---", "Lỗi: "+t.getMessage());
            }
        });
    }
}