package com.dreamernguyen.ClientDuAn.Fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.dreamernguyen.ClientDuAn.Adapter.BaiVietAdapter;
import com.dreamernguyen.ClientDuAn.Adapter.MatHangAdapter;
import com.dreamernguyen.ClientDuAn.ApiService;
import com.dreamernguyen.ClientDuAn.Models.DuLieuTraVe;
import com.dreamernguyen.ClientDuAn.Models.MatHang;
import com.dreamernguyen.ClientDuAn.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GianHangFragment extends Fragment {

    RecyclerView rv;
    MatHangAdapter matHangAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_gian_hang, container, false);
        rv = view.findViewById(R.id.rv);
        matHangAdapter = new MatHangAdapter(getContext());
        loadMatHang();

        rv.setAdapter(matHangAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));


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