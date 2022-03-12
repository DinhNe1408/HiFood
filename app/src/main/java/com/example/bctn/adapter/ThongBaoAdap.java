package com.example.bctn.adapter;

import com.example.bctn.R;
import com.example.bctn.domain.key;
import com.example.bctn.domain.thongbao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ThongBaoAdap extends RecyclerView.Adapter<ThongBaoAdap.ThongBaoViewHolder>{

    private Context mContext;
    private List<thongbao> listTB;

    public ThongBaoAdap(Context mContext, List<thongbao> listTB){
        this.mContext = mContext;
        this.listTB = listTB;
    }

    public void setData(List<thongbao> listTB){
        this.listTB = listTB;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ThongBaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.viewholder_thongbao,parent,false);
        return new ThongBaoAdap.ThongBaoViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongBaoViewHolder holder, int position) {
        Toast.makeText(mContext, String.valueOf(listTB.size()), Toast.LENGTH_SHORT).show();
        thongbao mthongbao = listTB.get(position);
        if(mthongbao == null){
            return;
        }
        holder.txtV_NoiDung_tb.setText(mthongbao.getNoidungTB());
        holder.img_Hinh_tb.setImageResource(mthongbao.getHinhTB());
        holder.txtV_ThoiGian_tb.setText(key.TinhThoiGian(mthongbao.getThoigianTB()));

    }

    @Override
    public int getItemCount() {
        if(listTB != null){
            return listTB.size();
        }
        return 0;
    }

    public class  ThongBaoViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_Hinh_tb;
        private TextView txtV_NoiDung_tb,txtV_ThoiGian_tb;

        public ThongBaoViewHolder(@NonNull View itemView) {
            super(itemView);

            img_Hinh_tb = itemView.findViewById(R.id.img_Hinh_tb);
            txtV_NoiDung_tb = itemView.findViewById(R.id.txtV_NoiDung_tb);
            txtV_ThoiGian_tb = itemView.findViewById(R.id.txtV_ThoiGian_tb);
        }
    }
}
