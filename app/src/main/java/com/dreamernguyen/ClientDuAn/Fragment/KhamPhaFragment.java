package com.dreamernguyen.ClientDuAn.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamernguyen.ClientDuAn.Adapter.BaiVietAdapter;
import com.dreamernguyen.ClientDuAn.ApiService;
import com.dreamernguyen.ClientDuAn.Activity.DangBaiActivity;
import com.dreamernguyen.ClientDuAn.Models.BaiViet;
import com.dreamernguyen.ClientDuAn.R;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KhamPhaFragment extends Fragment {
    RecyclerView rvBaiViet;
    BaiVietAdapter baiVietAdapter;
    SwipeRefreshLayout refreshLayout;
    TextView tv;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kham_pha, container, false);
        refreshLayout = view.findViewById(R.id.refreshLayout);

        tv = view.findViewById(R.id.tv1);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),DangBaiActivity.class);
                i.putExtra("chucNang","Đăng bài");
                startActivity(i);
            }
        });
        rvBaiViet =  view.findViewById(R.id.rvBaiViet);
        baiVietAdapter = new BaiVietAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        rvBaiViet.setLayoutManager(linearLayoutManager);
        rvBaiViet.setAdapter(baiVietAdapter);
        loadBaiViet();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadBaiViet();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });
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
//                Toast.makeText(getActivity(), ""+ response.body().size(), Toast.LENGTH_SHORT).show();
                baiVietAdapter.setData(response.body());
                baiVietAdapter.random();
            }
            @Override
            public void onFailure(Call<List<BaiViet>> call, Throwable t) {
                Log.d("fff", "onFailure: "+t.getMessage());
                Toast.makeText(getActivity(), "Không có internet !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}