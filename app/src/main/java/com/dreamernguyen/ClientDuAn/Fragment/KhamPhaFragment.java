package com.dreamernguyen.ClientDuAn.Fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.dreamernguyen.ClientDuAn.Adapter.AnhAdapter;
import com.dreamernguyen.ClientDuAn.Adapter.BaiVietAdapter;
import com.dreamernguyen.ClientDuAn.ApiService;
import com.dreamernguyen.ClientDuAn.Models.BaiViet;
import com.dreamernguyen.ClientDuAn.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KhamPhaFragment extends Fragment {
    RecyclerView rvBaiViet,rvAnh;
    BaiVietAdapter baiVietAdapter;
    AnhAdapter anhAdapter;
    ImageView imChonAnh;
    Button btnDang;
    
    List<Uri> listPath = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_kham_pha, container, false);
//        rvBaiViet =  view.findViewById(R.id.rvBaiViet);
        rvAnh = view.findViewById(R.id.rvAnh);
        anhAdapter = new AnhAdapter(getContext());
        baiVietAdapter = new BaiVietAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false);
        rvAnh.setLayoutManager(linearLayoutManager);
        rvAnh.setAdapter(anhAdapter);

        imChonAnh = view.findViewById(R.id.imChonAnh);
        btnDang = view.findViewById(R.id.btnDang);
        imChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();




            }
        });
        btnDang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("oooo", "onClick: "+listPath);
                        List<String> listURL = new ArrayList<>();
                            for(int i = 0 ;i < listPath.size();i++){
                                Log.d("ooo", "onImagesSelected: "+listPath.get(i));
                                MediaManager.get().upload(listPath.get(i))
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
                                        listURL.add(resultData.get("url").toString());
//                                        photoAdapter.setData(listURL);//bỏ cái adapter ảnh vô đây
                                        Log.d("--", "onSuccess: "+listURL);

                                    }

                                    @Override
                                    public void onError(String requestId, ErrorInfo error) {

                                    }

                                    @Override
                                    public void onReschedule(String requestId, ErrorInfo error) {

                                    }
                                })
                                        .dispatch();


                            }

            }
        });

//        loadBaiViet();
        return view;
    }

    private void dangBaiViet() {
        Call<BaiViet> call = ApiService.apiService.dangBai("",);
        call.enqueue(new Callback<List<BaiViet>>() {
            @Override
            public void onResponse(Call<List<BaiViet>> call, Response<List<BaiViet>> response) {
                BaiViet baiViet = response.body().get(0);
                Log.d("fff", "onResponse: "+baiViet);
                Log.d("fff", "onResponse: "+baiViet.getIdNguoiDung());
                Log.d("fff", "onResponse: "+baiViet.getIdNguoiDung().getHoTen());
//                Toast.makeText(getActivity(), ""+ response.body().size(), Toast.LENGTH_SHORT).show();
                baiVietAdapter.setData(response.body());
            }
            @Override
            public void onFailure(Call<List<BaiViet>> call, Throwable t) {
                Log.d("fff", "onFailure: "+t.getMessage());
//                Toast.makeText(getActivity(), "Không có internet !", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void chooseImage(){
        TedBottomPicker.with(getActivity())
                .setPeekHeight(1600)
                .showTitle(false)
                .setCompleteButtonText("Xác nhận")
                .setEmptySelectionText("Không ảnh nào được chọn")
                .showMultiImage(new TedBottomSheetDialogFragment.OnMultiImageSelectedListener() {
                    @Override
                    public void onImagesSelected(List<Uri> uriList) {
                        if(uriList != null && !uriList.isEmpty()){
                            anhAdapter.setData(uriList);
                            listPath = uriList;
                            Log.d("cccc", "onImagesSelected: "+listPath);




                        }
                    }
                });

    }
}