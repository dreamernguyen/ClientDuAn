package com.dreamernguyen.ClientDuAn.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dreamernguyen.ClientDuAn.Adapter.BaiVietAdapter;
import com.dreamernguyen.ClientDuAn.ApiService;
import com.dreamernguyen.ClientDuAn.Models.BaiViet;
import com.dreamernguyen.ClientDuAn.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TheoDoiFragment extends Fragment {
    RecyclerView rvBaiViet;
    BaiVietAdapter baiVietAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_theo_doi, container, false);
        rvBaiViet =  view.findViewById(R.id.rvBaiViet);
        baiVietAdapter = new BaiVietAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        rvBaiViet.setLayoutManager(linearLayoutManager);
        rvBaiViet.setAdapter(baiVietAdapter);
//        loadBaiViet();
        return view;
    }

    public void loadBaiViet(){
        Call<List<BaiViet>> call = ApiService.apiService.danhSachBaiViet();
        call.enqueue(new Callback<List<BaiViet>>() {
            @Override
            public void onResponse(Call<List<BaiViet>> call, Response<List<BaiViet>> response) {
                BaiViet baiViet = response.body().get(0);
                Log.d("fff", "onResponse: "+baiViet);
                Log.d("fff", "onResponse: "+baiViet.getIdNguoiDung());
                Log.d("fff", "onResponse: "+baiViet.getIdNguoiDung().getHoTen());
                baiVietAdapter.setData(response.body());
            }

            @Override
            public void onFailure(Call<List<BaiViet>> call, Throwable t) {
                Log.d("fff", "onFailure: "+t.getMessage());
            }
        });
    }
}