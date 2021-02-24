package com.dreamernguyen.ClientDuAn.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.dreamernguyen.ClientDuAn.Models.Anh;
import com.dreamernguyen.ClientDuAn.R;

import java.util.List;

public class AnhAdapter extends RecyclerView.Adapter<AnhAdapter.AnhViewHolder> {
private Context context;
private List<Uri> listAnh;

public AnhAdapter(Context context) {
        this.context = context;
        }

public void setData(List<Uri> list) {
        this.listAnh = list;
        notifyDataSetChanged();
        }

@NonNull
@Override
public AnhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_anh, parent, false);
        return new AnhViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull AnhViewHolder holder, int position) {
        Glide.with(context).load(listAnh.get(position)).into(holder.imgBaiViet);

        }

@Override
public int getItemCount() {
        if (listAnh != null) {
        return listAnh.size();
        }
        return 0;
        }

public class AnhViewHolder extends RecyclerView.ViewHolder {
    ImageView imgBaiViet;


    public AnhViewHolder(@NonNull View itemView) {
        super(itemView);
        imgBaiViet = itemView.findViewById(R.id.imBaiViet);
    }
}
}
