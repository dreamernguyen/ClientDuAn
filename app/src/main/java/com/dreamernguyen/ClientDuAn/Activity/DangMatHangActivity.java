package com.dreamernguyen.ClientDuAn.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.dreamernguyen.ClientDuAn.Adapter.AnhAdapter;
import com.dreamernguyen.ClientDuAn.ApiService;
import com.dreamernguyen.ClientDuAn.Models.DuLieuTraVe;
import com.dreamernguyen.ClientDuAn.Models.MatHang;
import com.dreamernguyen.ClientDuAn.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
    String idMatHang;
    List<Uri> listPath = new ArrayList<>();

    TextView tvTieuDe, tvDangtin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_mat_hang);

        KhaiBao();


        tvTieuDe = findViewById(R.id.tvTieuDe);
        tvDangtin = findViewById(R.id.tvDangtin);


        tvDangtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rvAnh = findViewById(R.id.rvAnh);
        imgChonAnh = findViewById(R.id.imaChonAnh);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        window.setStatusBarColor(Color.TRANSPARENT);
        anhAdapter = new AnhAdapter(getApplicationContext());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4, RecyclerView.VERTICAL, false);
        rvAnh.setLayoutManager(gridLayoutManager);


        rvAnh.setAdapter(anhAdapter);
        rvAnh.setClickable(false);
        imgChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonAnh();
            }
        });

        Intent i = getIntent();
        if (i.getStringExtra("chucNang").equals("Cập nhật")) {
            idMatHang = i.getStringExtra("idMatHang");
            loadChiTiet(idMatHang);
            tvTieuDe.setText("Chỉnh sửa bài viết");
            tvDangtin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    upAnhLenServer();
                }
            });
            tvDangtin.setText("Cập nhật");
        }

    }

    private void loadChiTiet(String idBaiViet) {
        Call<DuLieuTraVe> call = ApiService.apiService.xemChiTietMatHang(idBaiViet);
        call.enqueue(new Callback<DuLieuTraVe>() {
            @Override
            public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
                edNoiDung.setText(response.body().getMatHang().getNoiDung());
                edTieuDe.setText(response.body().getMatHang().getTieuDe());
                edGiaBan.setText(response.body().getMatHang().getGiaBan() + "");
                edDiaChi.setText(response.body().getMatHang().getDiaChi());
                if (response.body().getMatHang().getLinkAnh() != null) {
                    Toast.makeText(DangMatHangActivity.this, "Có ảnh nha", Toast.LENGTH_SHORT).show();
                    listAnh = response.body().getMatHang().getLinkAnh();
                    anhAdapter.loadAnhCu(listAnh);
                    Log.d("ndndn", "onResponse: " + response.body().getMatHang().getLinkAnh());

                }
                Toast.makeText(DangMatHangActivity.this, "Ok r" + response.body().getMatHang().getNoiDung(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DuLieuTraVe> call, Throwable t) {
                Toast.makeText(DangMatHangActivity.this, "Lỗi" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void upAnhLenServer() {

        String regex = "(http://|https://|www\\.)([^ '\"]*)";
        List<String> anhCu = new ArrayList<>();
        List<Uri> anhMoi = new ArrayList<>();
//        Pattern pattern = Pattern.compile(regex);
//        for (int i = 0; i < listAnhCu.size(); i ++){
//            if(!pattern.matcher(listAnhCu.get(i)).matches()){
//                listPath.add(Uri.parse(listAnhCu.get(i)));
//            }
//        }
        listPath.clear();
        for (String uri : listAnh){
            if(!Pattern.compile(regex).matcher(uri).matches()){
                anhMoi.add(Uri.parse(uri));
            }else {
                anhCu.add(uri);
            }

        }
        Log.d("list", "upAnhLenServer: "+listAnh);
        Log.d("anhCu", "upAnhLenServer: "+anhCu);
        Log.d("anhMoi", "upAnhLenServer: "+anhMoi);

        if(listAnh.size() > 0){
            Log.d("ccccc", "upAnhLenServer:  Có ảnh");

            Log.d("oooo", "onClick: "+listPath);
            List<String> listURL = new ArrayList<>();
            for(int i = 0 ;i < anhMoi.size();i++){
                Log.d("ooo", "onImagesSelected: "+anhMoi.get(i));
                MediaManager.get().upload(anhMoi.get(i))
                        .unsigned("gybczcnv").callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {

                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {

                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        Log.d("trave", "onSuccess: "+resultData.get("url"));

                        if(idMatHang == null){
                            listURL.add(resultData.get("url").toString());
                            Log.d("--", "onSuccess: "+listURL);
                            if(listURL.size() == anhMoi.size()){
                                Log.d("cuoicungroiuii", "loadAnh: "+listURL);


                            }
                            dangMatHang(listURL);
                            Log.d("Hihi", "onSuccess: Đăng bài viết");
                        }
                        else {
                            listURL.add(resultData.get("url").toString());
                            Log.d("--", "onSuccess: "+anhCu);
                            if(listURL.size() == anhMoi.size()){
                                Log.d("cuoicungroiuii", "loadAnh: "+listURL);
                                listURL.addAll(anhCu);

                            }
                            capNhatMatHang(listURL);
                            Log.d("Hihi", "onSuccess: Cập nhật bài viết");
                        }
                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {

                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {

                    }
                }).dispatch();

            }
        }
        else {
            Log.d("ddd", "upAnhLenServer: Không có ảnh");
            dangMatHang(null);
        }

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

    public void dangMatHang( List<String> listUrl) {

        Integer giaBan = Integer.parseInt(edGiaBan.getText().toString());

        String tieuDe = edTieuDe.getText().toString();
        String noiDung = edNoiDung.getText().toString();
        String diaChi = edDiaChi.getText().toString();

        List<String> linkAnh = new ArrayList<>();
        linkAnh.add("https://techland.com.vn/wp-content/uploads/2019/09/dien-thoai-iphone-11-pro-max-3a.jpg");
        MatHang matHang = new MatHang(tieuDe, noiDung, spnHangMuc.getSelectedItem().toString(), giaBan, listUrl, diaChi);

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
                        if (uriList != null && !uriList.isEmpty()) {
                            uriList.forEach(uri -> listAnh.add(uri.toString()));
                            anhAdapter.loadAnhCu(listAnh);
//                            listPath = uriList;
                            Log.d("cccc", "onImagesSelected: " + listAnh);

                        }
                    }
                });
    }

    private void capNhatMatHang(List<String> listURL) {

        Integer giaBan = Integer.parseInt(edGiaBan.getText().toString());

        String tieuDe = edTieuDe.getText().toString();
        String noiDung = edNoiDung.getText().toString();
        String diaChi = edDiaChi.getText().toString();

        List<String> linkAnh = new ArrayList<>();
        linkAnh.add("https://techland.com.vn/wp-content/uploads/2019/09/dien-thoai-iphone-11-pro-max-3a.jpg");
        MatHang matHang = new MatHang(tieuDe, noiDung, spnHangMuc.getSelectedItem().toString(), giaBan, listURL, diaChi);

        Call<DuLieuTraVe> call = ApiService.apiService.capNhapMatHang(idMatHang, matHang);
        call.enqueue(new Callback<DuLieuTraVe>() {
            @Override
            public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
                Toast.makeText(DangMatHangActivity.this, response.body().getThongBao(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DuLieuTraVe> call, Throwable t) {
                Toast.makeText(DangMatHangActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}




