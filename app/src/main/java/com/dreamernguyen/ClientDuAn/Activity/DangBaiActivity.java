package com.dreamernguyen.ClientDuAn.Activity;

import androidx.annotation.Nullable;
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
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.dreamernguyen.ClientDuAn.Adapter.AnhAdapter;
import com.dreamernguyen.ClientDuAn.ApiService;
import com.dreamernguyen.ClientDuAn.LocalDataManager;
import com.dreamernguyen.ClientDuAn.Models.BaiViet;
import com.dreamernguyen.ClientDuAn.Models.DuLieuTraVe;
import com.dreamernguyen.ClientDuAn.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangBaiActivity extends AppCompatActivity {
    RecyclerView rvAnh;
    AnhAdapter anhAdapter;
    TextView btnThemAnh,btnDang,tvTieuDe;
    EditText edNoiDung;
    ImageView imgBack;

    List<Uri> listPath = new ArrayList<>();
    List<String> listAnh = new ArrayList<>();
    String idBaiViet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_bai);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        window.setStatusBarColor(Color.TRANSPARENT);

        rvAnh = findViewById(R.id.rvAnh);
        edNoiDung = findViewById(R.id.edNoiDung);
        btnThemAnh = findViewById(R.id.btnThemAnh);
        btnDang = findViewById(R.id.btnDang);
        tvTieuDe = findViewById(R.id.tvTieuDe);
        tvTieuDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quetQR();
            }
        });
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        anhAdapter = new AnhAdapter(getApplicationContext());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3,RecyclerView.VERTICAL,false );
        rvAnh.setLayoutManager(gridLayoutManager);
        rvAnh.setAdapter(anhAdapter);

        Intent i = getIntent();
        if(i.getStringExtra("chucNang").equals("Cập nhật")){
            idBaiViet = i.getStringExtra("idBaiViet");
            loadChiTiet(idBaiViet);
            tvTieuDe.setText("Chỉnh sửa bài viết");
            btnDang.setText("Cập nhật");
        }else {
            Toast.makeText(this, "Tạo bài mới", Toast.LENGTH_SHORT).show();
        }

        btnThemAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonAnh();

            }
        });

        btnDang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upAnhLenServer();
                btnDang.setClickable(false);
            }
        });

    }

    private void upAnhLenServer() {

        String regex = "(http://|https://|www\\.)([^ '\"]*)";
        List<String> anhCu = new ArrayList<>();
        List<Uri> anhMoi = new ArrayList<>();
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
            if(anhMoi.size() == 0){
                capNhatBaiViet(listAnh);
            }else {
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

                            if(idBaiViet == null){
                                listURL.add(resultData.get("url").toString());
                                Log.d("--", "onSuccess: "+listURL);
                                if(listURL.size() == anhMoi.size()){
                                    Log.d("cuoicungroiuii", "loadAnh: "+listURL);


                                }
                                dangBaiViet(listURL);
                                Log.d("Hihi", "onSuccess: Đăng bài viết");
                            }
                            else {
                                listURL.add(resultData.get("url").toString());
                                Log.d("--", "onSuccess: "+anhCu);
                                if(listURL.size() == anhMoi.size()){
                                    Log.d("cuoicungroiuii", "loadAnh: "+listURL);
                                    listURL.addAll(anhCu);

                                }
                                capNhatBaiViet(listURL);
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

        }
        else {
            Log.d("ddd", "upAnhLenServer: Không có ảnh");
            dangBaiViet(null);
        }

    }

    private void dangBaiViet(List<String> listURL) {
        BaiViet baiViet = new BaiViet(edNoiDung.getText().toString(),listURL);
        Call<BaiViet> call = ApiService.apiService.dangBai(LocalDataManager.getIdNguoiDung(),baiViet);
        call.enqueue(new Callback<BaiViet>() {
            @Override
            public void onResponse(Call<BaiViet> call, Response<BaiViet> response) {
                BaiViet baiViet = response.body();
                Log.d("fff", "onResponse: "+baiViet);
                Toast.makeText(getApplicationContext(), "Đăng bài thành công", Toast.LENGTH_SHORT).show();
                finish();

            }
            @Override
            public void onFailure(Call<BaiViet> call, Throwable t) {
                Log.d("fff", "onFailure: "+t.getMessage());
//                Toast.makeText(getActivity(), "Không có internet !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void capNhatBaiViet(List<String> listURL) {
        BaiViet baiViet = new BaiViet(edNoiDung.getText().toString(),listURL);
        Call<DuLieuTraVe> call = ApiService.apiService.capNhatBaiViet(idBaiViet,baiViet);
        call.enqueue(new Callback<DuLieuTraVe>() {
            @Override
            public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
                Toast.makeText(DangBaiActivity.this, response.body().getThongBao(), Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<DuLieuTraVe> call, Throwable t) {
                Toast.makeText(DangBaiActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void chonAnh() {
        TedBottomPicker.with(DangBaiActivity.this)
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

    private void loadChiTiet(String idBaiViet){
        Call<DuLieuTraVe> call = ApiService.apiService.xemChiTiet(idBaiViet);
        call.enqueue(new Callback<DuLieuTraVe>() {
            @Override
            public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
                edNoiDung.setText(response.body().getBaiViet().getNoiDung());
                if(response.body().getBaiViet().getLinkAnh() != null){
                    Toast.makeText(DangBaiActivity.this, "Có ảnh nha", Toast.LENGTH_SHORT).show();
                    listAnh = response.body().getBaiViet().getLinkAnh();
                    anhAdapter.loadAnhCu(listAnh);
                    Log.d("ndndn", "onResponse: "+response.body().getBaiViet().getLinkAnh());

                }
                Toast.makeText(DangBaiActivity.this, "Ok r"+response.body().getBaiViet().getNoiDung(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DuLieuTraVe> call, Throwable t) {
                Toast.makeText(DangBaiActivity.this, "Lỗi" +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void quetQR(){
        IntentIntegrator integrator = new IntentIntegrator(this);
//        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
//        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(true);
//        integrator.setBarcodeImageEnabled(true);
        integrator.setOrientationLocked(true);
        integrator.setCaptureActivity(QuetQR.class);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data );
        if(intentResult.getContents() != null){
            Toast.makeText(this, "Đọc thành công "+intentResult.getContents(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Đọc thất bại ", Toast.LENGTH_SHORT).show();
        }
    }
}