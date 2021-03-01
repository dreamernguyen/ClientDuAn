package com.dreamernguyen.ClientDuAn.Fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.dreamernguyen.ClientDuAn.Adapter.BaiVietAdapter;
import com.dreamernguyen.ClientDuAn.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;

public class GianHangFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_gian_hang, container, false);


        return view;
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
                            List<String> listURL = new ArrayList<>();
                            for(int i = 0 ;i < uriList.size();i++){
                                Log.d("ooo", "onImagesSelected: "+uriList.get(i));
                                MediaManager.get().upload(uriList.get(i))
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
                    }
                });
    }
}