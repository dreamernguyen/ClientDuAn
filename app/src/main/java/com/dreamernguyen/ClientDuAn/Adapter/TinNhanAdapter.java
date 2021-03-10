package com.dreamernguyen.ClientDuAn.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dreamernguyen.ClientDuAn.Models.TinNhan;
import com.dreamernguyen.ClientDuAn.R;

import java.util.List;

public class TinNhanAdapter extends  RecyclerView.Adapter<TinNhanAdapter.TinNhanViewHolder> {
    private Context context;
    private List<TinNhan> listTinNhan;

    public TinNhanAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<TinNhan> list) {
        this.listTinNhan = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TinNhanAdapter.TinNhanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tin_nhan, parent, false);
        return new TinNhanAdapter.TinNhanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TinNhanAdapter.TinNhanViewHolder holder, int position) {
        TinNhan tinNhan = listTinNhan.get(position);
        if (tinNhan == null){
            return;
        }
        if(tinNhan.getIdNguoiNhan().getId().equals("600688a68df31f0984c97de7")){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.START;
            holder.layoutChat.setLayoutParams(params);
            holder.layoutChat.setBackground(context.getDrawable(R.drawable.bg_chat2));
        }else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.END;
            holder.layoutChat.setLayoutParams(params);
            holder.layoutChat.setBackground(context.getDrawable(R.drawable.bg_chat));
        }
        Log.d("fff", "onBindViewHolder: "+ tinNhan.getNoiDung());
        holder.tvChat.setText(tinNhan.getNoiDung());
        holder.imageView.setVisibility(View.GONE);
//        if(tinNhan.getLinkAnh()){
//            holder.tvChat.setText(tinNhan.getNoiDung());
//            holder.imageView.setVisibility(View.GONE);
//        }
//        if(!tinNhan.getLinkAnh().equals("")){
//            holder.imageView.setVisibility(View.VISIBLE);
//            Log.d("cccc", "onBindViewHolder: "+tinNhan.getLinkAnh());
//            Uri uri = Uri.parse(tinNhan.getLinkAnh());
//            Glide.with(context).load(uri).into(holder.imageView);
//        }

    }

    @Override
    public int getItemCount() {
        if (listTinNhan != null) {
            return listTinNhan.size();
        }
        return 0;
    }

    public class TinNhanViewHolder extends RecyclerView.ViewHolder {
        TextView tvChat;
        LinearLayout layoutChat;
        ImageView imageView;


        public TinNhanViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChat = (TextView) itemView.findViewById(R.id.tvChat);
            layoutChat = (LinearLayout) itemView.findViewById(R.id.layoutChat);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
