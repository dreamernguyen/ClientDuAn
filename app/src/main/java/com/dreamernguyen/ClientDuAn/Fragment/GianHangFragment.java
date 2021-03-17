package com.dreamernguyen.ClientDuAn.Fragment;

import android.net.Uri;
import android.os.Bundle;
<<<<<<< Updated upstream
=======
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
>>>>>>> Stashed changes

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< Updated upstream
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.dreamernguyen.ClientDuAn.Adapter.BaiVietAdapter;
=======
import com.dreamernguyen.ClientDuAn.Activity.TimKiemMatHangActivity;
>>>>>>> Stashed changes
import com.dreamernguyen.ClientDuAn.Adapter.MatHangAdapter;
import com.dreamernguyen.ClientDuAn.ApiService;
import com.dreamernguyen.ClientDuAn.Models.DuLieuTraVe;
import com.dreamernguyen.ClientDuAn.Models.MatHang;
import com.dreamernguyen.ClientDuAn.R;

import java.util.ArrayList;
import java.util.List;
<<<<<<< Updated upstream
import java.util.Map;

import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;
=======

>>>>>>> Stashed changes
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GianHangFragment extends Fragment {
<<<<<<< Updated upstream
=======

    TextView textView;
    Button btnDangMatHang;
    RecyclerView rvMatHangMoiNhat;
    MatHangAdapter matHangAdapter;
    List<MatHang> danhSachHienTai;
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
        rv.setAdapter(matHangAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));


=======
        textView=view.findViewById(R.id.tvtest1);
        btnDangMatHang = view.findViewById(R.id.btnDangMatHang);
        rvMatHangMoiNhat = view.findViewById(R.id.rvMatHangMoiNhat);

        matHangAdapter = new MatHangAdapter(getActivity());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimKiemMatHangActivity.tieuDe="o";
                startActivity(new Intent(getActivity(), TimKiemMatHangActivity.class));
            }
        });

        btnDangMatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DangMatHangActivity.class);
                intent.putExtra("chucNang", "Đăng hàng");
                startActivity(intent);
            }
        });

        loadMatHang();
        rvMatHangMoiNhat.setAdapter(matHangAdapter);
        rvMatHangMoiNhat.setLayoutManager(new GridLayoutManager(getActivity(), 2));
>>>>>>> Stashed changes
        return view;
    }

    public void loadMatHang() {

        Call<DuLieuTraVe> call = ApiService.apiService.danhSachMatHang();
<<<<<<< Updated upstream
       call.enqueue(new Callback<DuLieuTraVe>() {
           @Override
           public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
               matHangAdapter.setData(response.body().getDanhSachMatHang());
           }
=======
        call.enqueue(new Callback<DuLieuTraVe>() {
            @Override
            public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
                danhSachHienTai = response.body().getDanhSachMatHang();
                matHangAdapter.setData(danhSachHienTai);
            }
>>>>>>> Stashed changes

           @Override
           public void onFailure(Call<DuLieuTraVe> call, Throwable t) {

           }
       });

<<<<<<< Updated upstream

=======
    @Override
    public void onResume() {
        Toast.makeText(getActivity(), "a", Toast.LENGTH_SHORT).show();
        matHangAdapter.setData(danhSachHienTai);
        super.onResume();
>>>>>>> Stashed changes
    }
}