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
//        circleImageView = (CircleImageView) view.findViewById(R.id.imgAvatar);
//        tvTen = (TextView) view.findViewById(R.id.tvTenNguoiDung);
//        imgLike = view.findViewById(R.id.imgLike);
//        imgBaiViet = view.findViewById(R.id.imgAnhBaiViet);
//        imgBinhLuan = view.findViewById(R.id.imgComment);
//        imgChiaSe = view.findViewById(R.id.imgShare);
//        tvDiaChi = view.findViewById(R.id.tvDiaChi);
//
//        Glide.with(getContext()).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZ5Emk7PICWNB49pHxQWN0Ma1xLKIMqmznYA&usqp=CAU").into(circleImageView);
//        Glide.with(getContext()).load("https://i.pinimg.com/736x/e5/75/6d/e5756d6fcd099ce79ad560ecc3ecfb59.jpg").into(imgBaiViet);
//
//        imgLike.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(),"Like thành công",Toast.LENGTH_SHORT).show();
//
//            }
//        });

        return view;
    }
}