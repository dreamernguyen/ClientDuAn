package com.dreamernguyen.ClientDuAn.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.dreamernguyen.ClientDuAn.Activity.MatHangChiTietActivity;
import com.dreamernguyen.ClientDuAn.ApiService;
import com.dreamernguyen.ClientDuAn.Models.BaiViet;
import com.dreamernguyen.ClientDuAn.Models.DuLieuTraVe;
import com.dreamernguyen.ClientDuAn.Models.MatHang;
import com.dreamernguyen.ClientDuAn.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        View view = LayoutInflater.from(context).inflate(R.layout.item_doc, parent, false);
        return new MatHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatHangViewHolder holder, int position) {
        MatHang matHang = listMatHang.get(position);

<<<<<<< Updated upstream
        holder.tvTieuDe.setText(matHang.getTieuDe());
        holder.tvDiaChi.setText(matHang.getDiaChi());
        holder.tvGiaBan.setText(matHang.getGiaBan()+"");
        Glide.with(context).load(matHang.getLinkAnh().get(0)).into(holder.img);
        Log.d("TAG", "onBindViewHolder: "+matHang.getLinkAnh());
        holder.imgTuyChinh.setOnClickListener(v -> {
            View viewDialog =((FragmentActivity) context).getLayoutInflater().inflate(R.layout.layout_bottom_sheet,null);
            BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(context);
            bottomSheetDialog.setContentView(viewDialog);
            bottomSheetDialog.show();


            TextView btnXoa = viewDialog.findViewById(R.id.btnXoa);
            TextView btnChinhSua = viewDialog.findViewById(R.id.btnChinhSua);
            TextView btnAn = viewDialog.findViewById(R.id.btnAn);

            if (matHang.getIdNguoiDung().getId().equals("6006875981484b2c7c2176c5")) {
                btnXoa.setVisibility(View.VISIBLE);
                btnChinhSua.setVisibility(View.VISIBLE);
                btnAn.setVisibility(View.GONE);
            } else {
                btnXoa.setVisibility(View.GONE);
                btnChinhSua.setVisibility(View.GONE);
                btnAn.setVisibility(View.GONE);

            }
            btnXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Call<DuLieuTraVe> call = ApiService.apiService.xoaMatHang(matHang.getId());
                    call.enqueue(new Callback<DuLieuTraVe>() {
                        @Override
                        public void onResponse(Call<DuLieuTraVe> call, Response<DuLieuTraVe> response) {
                            notifyDataSetChanged();
                            bottomSheetDialog.dismiss();
                            Toast.makeText(context, "aa", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<DuLieuTraVe> call, Throwable t) {

                        }
                    });
                }
            });
        });

=======
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        Log.d("now", "onBindViewHolder: " + now);
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
        format.setTimeZone(TimeZone.getTimeZone("UTC+7"));


//        try {
//            Date date = format.parse(matHang.getThoiGianTao());
//            long diff = now.getTime() - date.getTime();
//            long giay = (diff / 1000);
//            long phut = (diff / (1000 * 60));
//            long gio = (diff / (1000 * 60 * 60));
//            long ngay = (diff / (1000 * 60 * 60 * 24));
//            if (ngay > 3) {
//                holder.tvThoiGian.setText(format2.format(date));
//            }
//            if (ngay < 3) {
//                holder.tvThoiGian.setText(ngay + "ngày trước");
//            }
//            if (gio < 24 && gio > 0) {
//                holder.tvThoiGian.setText(gio + " giờ trước");
//            }
//            if (phut < 60 && phut > 0) {
//                holder.tvThoiGian.setText(phut + " phút trước");
//            }
//            if (giay < 60 && giay > 0) {
//                holder.tvThoiGian.setText("vừa xong");
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        holder.lnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatHangChiTietActivity.idMatHangChiTiet=matHang.getId();
                context.startActivity(new Intent(context, MatHangChiTietActivity.class));
            }
        });
        holder.tvTieuDe.setText(matHang.getTieuDe());
        holder.tvGiaBan.setText(matHang.getGiaBan() + " VND");
        holder.tvDiaChi.setText(matHang.getDiaChi());
        Glide.with(context).load(matHang.getLinkAnh().get(0)).into(holder.imgHinhAnh);
//        holder.tvSoAnh.setText(matHang.getLinkAnh().size() + "");


>>>>>>> Stashed changes
    }

    @Override
    public int getItemCount() {
        if (listMatHang != null) {
            return listMatHang.size();
        }
        return 0;
    }

    public class MatHangViewHolder extends RecyclerView.ViewHolder {
<<<<<<< Updated upstream
        TextView tvTieuDe, tvGiaBan, tvDiaChi;
        ImageView imgTuyChinh;
        CircleImageView img;
=======
        TextView tvTieuDe, tvGiaBan, tvDiaChi, tvThoiGian, tvSoAnh;
        LinearLayout    lnLayout;
        ImageView imgHinhAnh;
>>>>>>> Stashed changes


        public MatHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTieuDe = itemView.findViewById(R.id.tvTieuDe);
            tvGiaBan = itemView.findViewById(R.id.tvGiaBan);
            tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
<<<<<<< Updated upstream
            img = itemView.findViewById(R.id.img);
            imgTuyChinh = itemView.findViewById(R.id.imgTuyChinh);
=======
            tvThoiGian = itemView.findViewById(R.id.tvThoiGian);
            tvSoAnh = itemView.findViewById(R.id.tvSoAnh);
            lnLayout=itemView.findViewById(R.id.lnLayout);
            imgHinhAnh = itemView.findViewById(R.id.imgHinhAnh);
>>>>>>> Stashed changes
        }
    }
}

