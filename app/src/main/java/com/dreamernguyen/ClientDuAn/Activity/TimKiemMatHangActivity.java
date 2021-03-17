package com.dreamernguyen.ClientDuAn.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dreamernguyen.ClientDuAn.Adapter.MatHangAdapter;
import com.dreamernguyen.ClientDuAn.ApiService;
import com.dreamernguyen.ClientDuAn.Models.DuLieuTraVe;
import com.dreamernguyen.ClientDuAn.Models.MatHang;
import com.dreamernguyen.ClientDuAn.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimKiemMatHangActivity extends AppCompatActivity {

    public static String tieuDe;
    MatHangAdapter matHangAdapter;
    RecyclerView rv;
    List<MatHang> danhSachHienTai;
    List<MatHang> danhSachTimKiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem_mat_hang);

        rv = findViewById(R.id.rv);

        matHangAdapter = new MatHangAdapter(this);
        loadMatHang();
        loadTest(tieuDe);
        rv.setAdapter(matHangAdapter);
        rv.setLayoutManager(new GridLayoutManager(this, 2));

    }

    public void loadMatHang() {
        Call<DuLieuTraVe> call = ApiService.apiService.danhSachMatHang();
        call.enqueue(new Callback<DuLieuTraVe>() {
            @Override
            public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
                danhSachHienTai = response.body().getDanhSachMatHang();
                matHangAdapter.setData(danhSachHienTai);
            }

            @Override
            public void onFailure(Call<DuLieuTraVe> call, Throwable t) {

            }
        });
    }

    public void loadTest(String tieude) {
        Toast.makeText(this, "aa", Toast.LENGTH_SHORT).show();
        Call<DuLieuTraVe> call = ApiService.apiService.timTieuDeMatHang("o");
        call.enqueue(new Callback<DuLieuTraVe>() {
            @Override
            public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
                Toast.makeText(getApplicationContext(), "aaaa", Toast.LENGTH_SHORT).show();
                List<MatHang> c = new ArrayList<>();
                danhSachTimKiem = response.body().getDanhSachMatHang();

                Log.d("TAGa", "onResponse: hien tai " + danhSachHienTai.toString());
                Log.d("TAGa", "onResponse: tim kiem" + danhSachTimKiem.toString());
                Iterator<MatHang> itr = danhSachTimKiem.iterator();

                while (itr.hasNext()) {
                    MatHang element = itr.next();
                    if (danhSachHienTai.contains(element)) {
                        c.add(element);
                    }
                }
                danhSachHienTai = c;
                Log.d("TAGa", "onResponse: hien tai sau tim kiem " + danhSachHienTai.toString());

                matHangAdapter.setData(danhSachHienTai);

            }

            @Override
            public void onFailure(Call<DuLieuTraVe> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "aaa", Toast.LENGTH_SHORT).show();

            }
        });
    }
}