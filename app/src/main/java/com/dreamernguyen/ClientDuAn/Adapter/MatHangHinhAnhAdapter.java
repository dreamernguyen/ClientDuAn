package com.dreamernguyen.ClientDuAn.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dreamernguyen.ClientDuAn.R;

import java.util.List;

public class MatHangHinhAnhAdapter extends RecyclerView.Adapter<MatHangHinhAnhAdapter.HinhAnhViewHolder> {

    Context context;
    List<Uri> listURI;
    List<String> listString;

    public MatHangHinhAnhAdapter(Context context) {
        this.context = context;
    }

    public List<Uri> getListURI() {
        return listURI;
    }

    public void setListURI(List<Uri> listURI) {
        this.listURI = listURI;
    }

    public void setListString(List<String> listString) {
        this.listString = listString;
    }

    public List<String> getListString() {
        return listString;
    }

    @NonNull
    @Override
    public HinhAnhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_anh, null);
        return new HinhAnhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HinhAnhViewHolder holder, int position) {

        if (listURI != null) {
            Glide.with(context).load(listURI.get(position)).into(holder.imgBaiViet);
            holder.xoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listURI.remove(position);
                    notifyItemRemoved(position);
                    notifyDataSetChanged();
                }
            });
        }
        if (listString != null) {
            Glide.with(context).load(listString.get(position)).into(holder.imgBaiViet);
            holder.xoa.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (listURI != null) {
            return listURI.size();
        }
        if (listString != null) {
            return listString.size();
        }
        return 0;
    }

    public class HinhAnhViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBaiViet, xoa;

        public HinhAnhViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBaiViet = itemView.findViewById(R.id.imBaiViet);
            xoa = itemView.findViewById(R.id.xoa);
        }
    }
}
