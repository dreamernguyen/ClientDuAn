package com.dreamernguyen.ClientDuAn.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class AnhBaiVietAdapter extends PagerAdapter {
    Context context;
    List<String> mlistanh;

//    public AnhBaiVietAdapter(BaiVietAdapter context, List<String> mlistanh) {}
    public AnhBaiVietAdapter(List<String> mlistanh) {
            this.mlistanh = mlistanh;
        }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final Context context = container.getContext();
        final AppCompatImageView imageView = new AppCompatImageView(context);
        container.addView(imageView);
        // Load ảnh vào ImageView bằng Glide
        Glide.with(context).load(mlistanh.get(position)).into(imageView);
        return imageView;
    }

    @Override
    public int getCount() {
        return mlistanh.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // container chính là ViewPager, còn Object chính là return của instantiateItem ứng với position
        container.removeView((View) object);
    }
}
