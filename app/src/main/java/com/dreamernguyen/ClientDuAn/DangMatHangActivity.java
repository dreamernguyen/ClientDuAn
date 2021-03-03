package com.dreamernguyen.ClientDuAn;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.dreamernguyen.ClientDuAn.Adapter.AnhAdapter;
import com.dreamernguyen.ClientDuAn.Models.DuLieuTraVe;
import com.dreamernguyen.ClientDuAn.Models.MatHang;

import java.util.ArrayList;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangMatHangActivity extends AppCompatActivity {

    EditText edTieuDe, edGiaBan, edDiaChi, edNoiDung;
    Spinner spnHangMuc;
    AnhAdapter anhAdapter;
    List<String> listAnh = new ArrayList<>();
    RecyclerView rvAnh;
    ImageView imgChonAnh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_mat_hang);

        KhaiBao();


        rvAnh=findViewById(R.id.rvAnh);
        imgChonAnh = findViewById(R.id.imaChonAnh);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        window.setStatusBarColor(Color.TRANSPARENT);
        anhAdapter = new AnhAdapter(getApplicationContext());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3, RecyclerView.VERTICAL,false );
        rvAnh.setLayoutManager(gridLayoutManager);


        rvAnh.setAdapter(anhAdapter);
        rvAnh.setClickable(false);
        imgChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgChonAnh.setVisibility(View.INVISIBLE);
                chonAnh();
            }
        });

    }

    public void KhaiBao() {
        edTieuDe = findViewById(R.id.edTieude);
        edNoiDung = findViewById(R.id.edNoidungMatHang);
        edGiaBan = findViewById(R.id.edGiaban);
        edDiaChi = findViewById(R.id.edDiaChi);
        spnHangMuc = findViewById(R.id.spnHangMuc);
    }

    public void troVe(View view) {
        finish();
    }

    public void dangMatHang( View view) {

        Integer giaBan = Integer.parseInt(edGiaBan.getText().toString());

        String tieuDe = edTieuDe.getText().toString();
        String noiDung=edNoiDung.getText().toString();
        String diaChi =edDiaChi.getText().toString();

        List<String> linkAnh = new ArrayList<>();
        linkAnh.add("https://techland.com.vn/wp-content/uploads/2019/09/dien-thoai-iphone-11-pro-max-3a.jpg");
        MatHang matHang = new MatHang(tieuDe, noiDung, spnHangMuc.getSelectedItem().toString(), giaBan, linkAnh, diaChi);

        Call<DuLieuTraVe> call = ApiService.apiService.dangMatHang("6006875981484b2c7c2176c5", matHang);
        call.enqueue(new Callback<DuLieuTraVe>() {
            @Override
            public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
                finish();
            }

            @Override
            public void onFailure(Call<DuLieuTraVe> call, Throwable t) {

            }
        });

    }
    private void chonAnh() {
        TedBottomPicker.with(DangMatHangActivity.this)
                .setPeekHeight(1600)
                .showTitle(false)
                .setCompleteButtonText("Xác nhận")
                .setEmptySelectionText("Không ảnh nào được chọn")
                .showMultiImage(new TedBottomSheetDialogFragment.OnMultiImageSelectedListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onImagesSelected(List<Uri> uriList) {
                        if(uriList != null && !uriList.isEmpty()){
                            uriList.forEach(uri -> listAnh.add(uri.toString()));
                            anhAdapter.loadAnhCu(listAnh);
//                            listPath = uriList;
                            Log.d("cccc", "onImagesSelected: "+listAnh);

                        }
                    }
                });
    }
}




