package com.dreamernguyen.ClientDuAn.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.dreamernguyen.ClientDuAn.Activity.DangMatHangActivity;
import com.dreamernguyen.ClientDuAn.Adapter.MatHangHinhAnhAdapter;
import com.dreamernguyen.ClientDuAn.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;

import static android.content.ContentValues.TAG;

public class DangMatHangHinhAnhFragment extends Fragment {

    RecyclerView rvHinhAnh;
    MatHangHinhAnhAdapter matHangHinhAnhAdapter;
    List<Uri> listURI;
    List<String> listString;
    Button btnThemAnh, btnToiDiaChi;
    TextView tv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_mat_hang_hinh_anh, container, false);

        tv = view.findViewById(R.id.tv);
        btnThemAnh = view.findViewById(R.id.btnThemAnh);
        btnToiDiaChi = view.findViewById(R.id.btnToiDiaChi);
        rvHinhAnh = view.findViewById(R.id.rvHinhAnh);

        btnToiDiaChi.setVisibility(View.GONE);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                denThongTin();
            }
        });


        matHangHinhAnhAdapter = new MatHangHinhAnhAdapter(getActivity());
        matHangHinhAnhAdapter.setListString(DangMatHangActivity.linkAnh);

        if (DangMatHangActivity.linkAnh != null ) {
            btnToiDiaChi.setVisibility(View.VISIBLE);
        }

        btnThemAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TedBottomPicker.with(getActivity()).setSelectedUriList(listURI).showMultiImage(new TedBottomSheetDialogFragment.OnMultiImageSelectedListener() {
                    @Override
                    public void onImagesSelected(List<Uri> uriList) {
                        List<String> a = new ArrayList<>();
                        listURI = uriList;

                        for (int i = 0; i < uriList.size(); i++) {
                            a.add(listURI.get(i).toString());

                        }
                        DangMatHangActivity.linkAnh = a;
                        matHangHinhAnhAdapter = new MatHangHinhAnhAdapter(getActivity());
                        matHangHinhAnhAdapter.setListString(DangMatHangActivity.linkAnh);
                        rvHinhAnh.setAdapter(matHangHinhAnhAdapter);
                        rvHinhAnh.setLayoutManager(new GridLayoutManager(getContext(), 3));

                        if (!uriList.isEmpty()) {
                            btnToiDiaChi.setVisibility(View.VISIBLE);
                        }

                    }
                });
            }
        });

        btnToiDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangMatHangActivity.vp2DangMatHang.setCurrentItem(2,false);
                DangMatHangActivity.viTri = 2;
                listString = new ArrayList<>();
                if (listURI!=null  && DangMatHangActivity.linkAnh.isEmpty()){

                    for (int i = 0; i < listURI.size(); i++) {
                        MediaManager.get().upload(listURI.get(i)).unsigned("gybczcnv").callback(new UploadCallback() {
                            @Override
                            public void onStart(String requestId) {
                                Toast.makeText(getActivity(),
                                        "Bat dau up anh", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onProgress(String requestId, long bytes, long totalBytes) {

                            }

                            @Override
                            public void onSuccess(String requestId, Map resultData) {

                                listString.add(resultData.get("url").toString());

                                Log.d("TAGa cu", "onCreateView: " + listURI.size());
                                Log.d("TAGa moi", "onCreateView: " + listString.size());
                                if (listString.size() == listURI.size()) {
                                    DangMatHangActivity.linkAnh = listString;
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
        });
        rvHinhAnh.setAdapter(matHangHinhAnhAdapter);
        rvHinhAnh.setLayoutManager(new GridLayoutManager(getContext(), 3));

        return view;
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    public void denThongTin() {
        DangMatHangActivity.vp2DangMatHang.setCurrentItem(5,false);
        DangMatHangActivity.viTri = 1;
    }


}
