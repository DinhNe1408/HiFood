package com.example.bctn.adapter.RecyclerAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.R;
import com.example.bctn.activity.QuanAnAct;
import com.example.bctn.domain.danhgia;
import com.example.bctn.domain.key;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DanhGiaAdap extends RecyclerView.Adapter<DanhGiaAdap.DanhGiaViewHolder> {

    private Context mContext;
    private List<danhgia> danhgiaList;

    public DanhGiaAdap(Context mContext, List<danhgia> danhgiaList) {
        this.mContext = mContext;
        this.danhgiaList = danhgiaList;
    }

    @NonNull
    @Override
    public DanhGiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_danh_gia, parent, false);
        return new DanhGiaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhGiaViewHolder holder, int position) {
        danhgia danhgia = danhgiaList.get(position);

        holder.imgV_HinhNDG.setImageBitmap(key.Byte2Bitmap(danhgia.getHinhNDG()));
        holder.txtV_TenNDG.setText(danhgia.getTenNDG());
        holder.txtV_NoiDungDG.setText(danhgia.getNoiDungDG());
        holder.rate3_SaoDG.setRating((float) danhgia.getSaoDG());
        holder.txtV_ThoiGianDG.setText(danhgia.getThoiGianDG());

    }

    @Override
    public int getItemCount() {
        if (danhgiaList != null) {
            return danhgiaList.size();
        }
        return 0;
    }

    public class DanhGiaViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView imgV_HinhNDG;
        private TextView txtV_TenNDG, txtV_ThoiGianDG, txtV_NoiDungDG;
        private RatingBar rate3_SaoDG;

        public DanhGiaViewHolder(@NonNull View itemView) {
            super(itemView);

            imgV_HinhNDG = itemView.findViewById(R.id.imgV_HinhNDG);

            txtV_TenNDG = itemView.findViewById(R.id.txtV_TenNDG);
            txtV_ThoiGianDG = itemView.findViewById(R.id.txtV_ThoiGianDG);
            txtV_NoiDungDG = itemView.findViewById(R.id.txtV_NoiDungDG);
            rate3_SaoDG = itemView.findViewById(R.id.rate3_SaoDG);
        }
    }
}
