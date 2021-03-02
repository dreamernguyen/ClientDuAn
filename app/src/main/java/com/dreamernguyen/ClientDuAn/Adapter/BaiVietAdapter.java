package com.dreamernguyen.ClientDuAn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.dreamernguyen.ClientDuAn.ApiService;
import com.dreamernguyen.ClientDuAn.BaiVietChiTietActivity;
import com.dreamernguyen.ClientDuAn.DangBaiActivity;
import com.dreamernguyen.ClientDuAn.Models.BaiViet;
import com.dreamernguyen.ClientDuAn.Models.DuLieuTraVe;
import com.dreamernguyen.ClientDuAn.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiVietAdapter extends RecyclerView.Adapter<BaiVietAdapter.BaiVietViewHolder> {
    private AnhBaiVietAdapter anhBaiVietAdapter;
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
//        Collections.shuffle(listBaiViet);
        BaiViet baiViet = listBaiViet.get(position);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        Log.d("now", "onBindViewHolder: " + now);
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
        format.setTimeZone(TimeZone.getTimeZone("UTC+7"));


        try {
            Date date = format.parse(baiViet.getThoiGianTao());
            long diff = now.getTime() - date.getTime();
            long giay = (diff / 1000);
            long phut = (diff / (1000 * 60));
            long gio = (diff / (1000 * 60 * 60));
            long ngay = (diff / (1000 * 60 * 60 * 24));
            if (ngay > 3) {
                holder.tvThoiGian.setText(format2.format(date));
            }
            if (ngay < 3) {
                holder.tvThoiGian.setText(ngay + "ngày trước");
            }
            if (gio < 24 && gio > 0) {
                holder.tvThoiGian.setText(gio + " giờ trước");
            }
            if (phut < 60 && phut > 0) {
                holder.tvThoiGian.setText(phut + " phút trước");
            }
            if (giay < 60 && giay > 0) {
                holder.tvThoiGian.setText("vừa xong");
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (baiViet.getTrangThai() == false) {
            holder.tvTrangThai.setText("Chỉ mình tôi");
        } else {
            holder.tvTrangThai.setText("Công khai");
        }

        if(baiViet.getLinkAnh().size() >0){
            anhBaiVietAdapter  = new AnhBaiVietAdapter(baiViet.getLinkAnh());
            holder.vpgAnh.setVisibility(View.VISIBLE);
            holder.vpgAnh.setAdapter(anhBaiVietAdapter);
        }else {
            holder.vpgAnh.setVisibility(View.GONE);
        }
        holder.tvTenNguoiDung.setText(baiViet.getIdNguoiDung().getHoTen());
        holder.tvNoiDung.setText(baiViet.getNoiDung());
        holder.tvLuotTim.setText(baiViet.getLuotThich().size() + " Lượt tim");

        holder.imTuyChinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View viewDailog = ((FragmentActivity) context).getLayoutInflater().inflate(R.layout.layout_bottom_sheet, null);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                bottomSheetDialog.setContentView(viewDailog);
                bottomSheetDialog.show();

                TextView btnXoa = viewDailog.findViewById(R.id.btnXoa);
                TextView btnChinhSua = viewDailog.findViewById(R.id.btnChinhSua);
                TextView btnAn = viewDailog.findViewById(R.id.btnAn);

                if (baiViet.getIdNguoiDung().getId().equals("600f0471e214d93278f7af7f")) {
                    btnXoa.setVisibility(View.VISIBLE);
                    btnChinhSua.setVisibility(View.VISIBLE);
                } else {
                    btnXoa.setVisibility(View.GONE);
                    btnChinhSua.setVisibility(View.GONE);
                }
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Call<DuLieuTraVe> call = ApiService.apiService.xoaBaiViet(baiViet.getId());
                        call.enqueue(new Callback<DuLieuTraVe>() {
                            @Override
                            public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
                                Toast.makeText(context, response.body().getThongBao(), Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                                bottomSheetDialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<DuLieuTraVe> call, Throwable t) {
                                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
                btnChinhSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DangBaiActivity.class);
                        intent.putExtra("chucNang","Cập nhật");
                        intent.putExtra("idBaiViet", baiViet.getId());
                        context.startActivity(intent);
                    }
                });

//
            }

        });
        holder.tvLuotTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "ddd" + baiViet.getId(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.tvBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BaiVietChiTietActivity.class);
                intent.putExtra("chucNang","Xem");
                intent.putExtra("idBaiViet", baiViet.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listBaiViet != null) {
            return listBaiViet.size();
        }
        return 0;
    }

    public class BaiVietViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenNguoiDung, tvThoiGian, tvNoiDung, tvTrangThai, tvLuotTim,tvBinhLuan;
        ViewPager vpgAnh;
        ImageView imTuyChinh;

        public BaiVietViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenNguoiDung = itemView.findViewById(R.id.tvTenNguoiDung);
            tvThoiGian = itemView.findViewById(R.id.tvThoiGian);
            tvNoiDung = itemView.findViewById(R.id.tvNoiDung);
            tvLuotTim = itemView.findViewById(R.id.tvTim);
            tvBinhLuan = itemView.findViewById(R.id.tvBinhLuan);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            vpgAnh = itemView.findViewById(R.id.vpgImage);
            imTuyChinh = itemView.findViewById(R.id.imTuyChinh);
        }
    }


}
