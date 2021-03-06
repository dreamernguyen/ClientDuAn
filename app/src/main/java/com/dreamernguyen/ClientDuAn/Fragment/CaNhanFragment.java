package com.dreamernguyen.ClientDuAn.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dreamernguyen.ClientDuAn.Adapter.BaiVietAdapter;
import com.dreamernguyen.ClientDuAn.ApiService;
import com.dreamernguyen.ClientDuAn.LocalDataManager;
import com.dreamernguyen.ClientDuAn.Models.BaiViet;
import com.dreamernguyen.ClientDuAn.Models.DuLieuTraVe;
import com.dreamernguyen.ClientDuAn.Models.NguoiDung;
import com.dreamernguyen.ClientDuAn.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaNhanFragment extends Fragment {
    TextView tvTenNguoiDung, tvDangTheoDoi, tvTheoDoi, btnDangBai;
    RecyclerView rcvBaiViet;
    BaiVietAdapter baiVietAdapter;
    CircleImageView imgAvatar;
    ImageView imQR;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ca_nhan, container, false);
        tvTenNguoiDung = view.findViewById(R.id.tvTenNguoiDung);
        tvDangTheoDoi = view.findViewById(R.id.tvDangTheoGioi);
        tvTheoDoi = view.findViewById(R.id.tvTheoGioi);
        rcvBaiViet = view.findViewById(R.id.rcvBaiViet);
        btnDangBai = view.findViewById(R.id.btnDangBai);
        imgAvatar = view.findViewById(R.id.imgAvatar);
        imQR = view.findViewById(R.id.imQR);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcvBaiViet.setLayoutManager(linearLayoutManager);
        baiVietAdapter = new BaiVietAdapter(getContext());
        rcvBaiViet.setAdapter(baiVietAdapter);
        loadTrangCaNhan();
        taoQR();
        return view;
    }
    private void loadTrangCaNhan(){
        Call<DuLieuTraVe> call = ApiService.apiService.xemTrangCaNhan(LocalDataManager.getIdNguoiDung());
        call.enqueue(new Callback<DuLieuTraVe>() {
            @Override
            public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
                NguoiDung nguoiDung = response.body().getNguoiDung();
                NguoiDung nguoiDung1 = LocalDataManager.getNguoiDung();
                Log.d("dddd", "onResponse: "+nguoiDung1);
                tvTenNguoiDung.setText(nguoiDung1.getId());
                tvDangTheoDoi.setText(nguoiDung.getDangTheoDoi().size()+"");
                tvTheoDoi.setText(nguoiDung.getDuocTheoDoi().size()+"");
                Log.d("ddddd", "onResponse: "+nguoiDung.getAvatar());
                if(nguoiDung.getAvatar() != null && !nguoiDung.getAvatar().isEmpty() ){
                    Glide.with(getContext()).load(nguoiDung.getAvatar()).into(imgAvatar);
                }
                baiVietAdapter.setData(response.body().getDanhSachBaiViet());

            }

            @Override
            public void onFailure(Call<DuLieuTraVe> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void taoQR(){
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = writer.encode(LocalDataManager.getIdNguoiDung(), BarcodeFormat.QR_CODE,250 ,250 );
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(bitMatrix);
            imQR.setImageBitmap(bitmap);



        } catch (WriterException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}