package com.dreamernguyen.ClientDuAn.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.dreamernguyen.ClientDuAn.Models.Anh;
import com.dreamernguyen.ClientDuAn.R;

import java.util.List;

public class AnhAdapter extends PagerAdapter {
    BaiVietAdapter context;
    List<Anh> mlistanh;

    public AnhAdapter(BaiVietAdapter context, List<Anh> mlistanh) {
        this.context = context;
        this.mlistanh = mlistanh;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_anh, container, false);
        ImageView imgBaiViet = view.findViewById(R.id.imBaiViet);
        Anh anh = mlistanh.get(position);
        if (anh!= null ){
            Glide.with(container).load(anh.getLinkAnh()).into(imgBaiViet);
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if (mlistanh != null){
            return mlistanh.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
