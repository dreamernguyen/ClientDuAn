package com.dreamernguyen.ClientDuAn.Fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dreamernguyen.ClientDuAn.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class KhamPhaFragment extends Fragment {
    CircleImageView circleImageView;
    TextView tvTen,tvDiaChi;
    ImageView imgLike,imgBaiViet,imgBinhLuan,imgChiaSe;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_kham_pha, container, false);
        return view;
    }
}