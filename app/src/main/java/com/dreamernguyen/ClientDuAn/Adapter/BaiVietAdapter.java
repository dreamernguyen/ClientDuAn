package com.dreamernguyen.ClientDuAn.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.dreamernguyen.ClientDuAn.Models.Anh;
import com.dreamernguyen.ClientDuAn.Models.BaiViet;
import com.dreamernguyen.ClientDuAn.R;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

public class BaiVietAdapter extends RecyclerView.Adapter<BaiVietAdapter.BaiVietViewHolder> {
    private AnhAdapter anhAdapter;
    private Context context;
    private List<BaiViet> listBaiViet;

    public BaiVietAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<BaiViet> list) {
        this.listBaiViet = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaiVietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bai_viet, parent, false);
        return new BaiVietViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaiVietViewHolder holder, int position) {
        Collections.shuffle(listBaiViet);
        BaiViet baiViet = listBaiViet.get(position);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        Log.d("now", "onBindViewHolder: " + now);
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
        format.setTimeZone(TimeZone.getTimeZone("UTC+7"));
        List<Anh> anhList = new ArrayList<>();
        for (int i = 0; i < baiViet.getLinkAnh().size(); i++){
            Anh anh = new Anh(baiViet.getLinkAnh().get(i));
            anhList.add(anh);
        }
        Boolean trangThai = true;
        if (listBaiViet == null){
            return;
        }
        if (anhList.size() < 1){
            holder.vpgAnh.setVisibility(View.GONE);
        } else {
            anhAdapter  = new AnhAdapter(context, anhList);
            holder.vpgAnh.setAdapter(anhAdapter);
            holder.vpgAnh.setVisibility(View.VISIBLE);
        }
        try {
            Date date = format.parse(baiViet.getThoiGianTao());
            long diff = now.getTime() - date.getTime();
            long giay =  (diff / 1000);
            long phut =  (diff / (1000 * 60));
            long gio =  (diff / (1000 * 60 * 60));
            long ngay =  (diff / (1000 * 60 * 60 * 24));
            if(ngay > 3){
                holder.tvThoiGian.setText(format2.format(date));
            }
            if (ngay < 3){
                holder.tvThoiGian.setText(ngay + "ngày trước");
            }
            if( gio < 24 && gio > 0){
                holder.tvThoiGian.setText(gio + " giờ trước");
            }if(phut < 60 && phut > 0){
                holder.tvThoiGian.setText(phut+ " phút trước");
            }if(giay < 60 && giay > 0){
                holder.tvThoiGian.setText("vừa xong");
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (trangThai ==  false){
            holder.tvTrangThai.setText("Chỉ mình tôi");
        } else {
            holder.tvTrangThai.setText("Công khai");
        }
        holder.tvTenNguoiDung.setText(baiViet.getIdNguoiDung().getHoTen());
        holder.tvNoiDung.setText(baiViet.getNoiDung());
        holder.tvLuotTim.setText(baiViet.getLuotThich().size()+ " Lượt tim");
    }

    @Override
    public int getItemCount() {
        if (listBaiViet != null) {
            return listBaiViet.size();
        }
        return 0;
    }

    public class BaiVietViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenNguoiDung, tvThoiGian, tvNoiDung, tvTrangThai, tvLuotTim;
        ViewPager vpgAnh;
        public BaiVietViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenNguoiDung = itemView.findViewById(R.id.tvTenNguoiDung);
            tvThoiGian = itemView.findViewById(R.id.tvThoiGian);
            tvNoiDung = itemView.findViewById(R.id.tvNoiDung);
            tvLuotTim = itemView.findViewById(R.id.tvTim);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            vpgAnh = itemView.findViewById(R.id.vpgImage);
        }
    }
}
