package com.dreamernguyen.ClientDuAn.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.dreamernguyen.ClientDuAn.Models.BaiViet;
import com.dreamernguyen.ClientDuAn.Models.MatHang;
import com.dreamernguyen.ClientDuAn.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MatHangAdapter extends RecyclerView.Adapter<MatHangAdapter.MatHangViewHolder> {
    private Context context;
    private List<MatHang> listMatHang;

    public MatHangAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<MatHang> list) {
        this.listMatHang = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MatHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mat_hang, parent, false);
        return new MatHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatHangViewHolder holder, int position) {
        MatHang matHang = listMatHang.get(position);

        holder.tvTieuDe.setText(matHang.getTieuDe());

        holder.tvGiaBan.setText(matHang.getGiaBan());

    }

    @Override
    public int getItemCount() {
        if (listMatHang != null) {
            return listMatHang.size();
        }
        return 0;
    }

    public class MatHangViewHolder extends RecyclerView.ViewHolder {
        TextView tvTieuDe, tvGiaBan;
        ViewPager vpAnh;


        public MatHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTieuDe = itemView.findViewById(R.id.tvTieuDe);
            tvGiaBan = itemView.findViewById(R.id.tvGiaBan);
        }
    }
}

