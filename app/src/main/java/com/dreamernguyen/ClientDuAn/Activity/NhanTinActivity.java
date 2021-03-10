package com.dreamernguyen.ClientDuAn.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamernguyen.ClientDuAn.Adapter.TinNhanAdapter;
import com.dreamernguyen.ClientDuAn.ApiService;
import com.dreamernguyen.ClientDuAn.LocalDataManager;
import com.dreamernguyen.ClientDuAn.Models.DuLieuTraVe;
import com.dreamernguyen.ClientDuAn.Models.TinNhan;
import com.dreamernguyen.ClientDuAn.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NhanTinActivity extends AppCompatActivity {
    private EditText edMessage;
    private TextView tvTenNguoiDung;
    private Button btnSend;
    private ImageView btnUpload;
    private static RecyclerView rvChat;
    private static TinNhanAdapter tinNhanAdapter;
    public static List<TinNhan> listTinNhan = new ArrayList<>();
    private static String idLienHe,tenNguoiDung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_tin);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(getColor(R.color.SkyBlue));
        }
        Intent i = getIntent();
        idLienHe = i.getStringExtra("idNguoiDung");
        tenNguoiDung = i.getStringExtra("tenNguoiDung");
        tvTenNguoiDung = findViewById(R.id.tvTenNguoiDung);
        tvTenNguoiDung.setText(tenNguoiDung);
        edMessage = findViewById(R.id.edMessage);
        btnSend = findViewById(R.id.btnSend);
        btnUpload = findViewById(R.id.btnUpload);
        rvChat = findViewById(R.id.rvChat);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvChat.setLayoutManager(linearLayoutManager);

        tinNhanAdapter = new TinNhanAdapter(getApplicationContext());
        rvChat.setAdapter(tinNhanAdapter);
        loadTinNhan();
        edMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkKeyboard();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guiTinNhan(edMessage.getText().toString());
            }
        });
    }

    private void checkKeyboard(){
        final View activityRootView = findViewById(R.id.activityRoot);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                activityRootView.getWindowVisibleDisplayFrame(rect);

                int heightDiff = activityRootView.getRootView().getHeight() - rect.height();
                if ((heightDiff > 0.25 *activityRootView.getRootView().getHeight())){
                    if(listTinNhan.size() > 0){
                        rvChat.scrollToPosition(listTinNhan.size() - 1);
                        activityRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }
        });
    }

    public static void loadTinNhan(){
        Call<List<TinNhan>> call = ApiService.apiService.danhSachTinNhan(idLienHe, LocalDataManager.getIdNguoiDung());
        call.enqueue(new Callback<List<TinNhan>>() {
            @Override
            public void onResponse(Call<List<TinNhan>> call, Response<List<TinNhan>> response) {
                listTinNhan = response.body();
                tinNhanAdapter.setData(listTinNhan);
                if(listTinNhan.size() > 0){
                    rvChat.scrollToPosition(listTinNhan.size() - 1);
                }
                Log.d("iiii", "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<List<TinNhan>> call, Throwable t) {
                Log.d("oo", "onFailure: "+t.getMessage());
            }
        });
    }

    private void guiTinNhan(String noiDung){
        String idNguoiDung = LocalDataManager.getIdNguoiDung();
        Log.d("bbb", "guiTinNhan: "+idNguoiDung);
        Call<DuLieuTraVe> call = ApiService.apiService.chat(idNguoiDung,idLienHe,noiDung);
        call.enqueue(new Callback<DuLieuTraVe>() {
            @Override
            public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
                loadTinNhan();
                Log.d("ggg", "onResponse: "+response.body().getThongBao());
            }

            @Override
            public void onFailure(Call<DuLieuTraVe> call, Throwable t) {
                Log.d("ddg", "Loi: "+t.getMessage());
            }
        });
    }

}