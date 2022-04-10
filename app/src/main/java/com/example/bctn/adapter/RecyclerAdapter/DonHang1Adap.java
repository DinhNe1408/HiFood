package com.example.bctn.adapter.RecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.R;
import com.example.bctn.activity.QuanAnAct;
import com.example.bctn.activity.TheoDoiDonHang;
import com.example.bctn.domain.donhang;
import com.example.bctn.domain.key;

import java.util.List;

public class DonHang1Adap extends RecyclerView.Adapter<DonHang1Adap.DonHang1AdapViewHolder> {

    private Context mContext;
    private List<donhang> mListDH;

    public DonHang1Adap(Context mContext, List<donhang> mListDH) {
        this.mContext = mContext;
        this.mListDH = mListDH;
    }

    @NonNull
    @Override
    public DonHang1AdapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_don_hang1, parent, false);
        return new DonHang1AdapViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHang1AdapViewHolder holder, int position) {
        donhang donhang = mListDH.get(position);

        if (donhang == null)
            return;

        holder.txtV_SL_dh1.setText(key.TienvaSL(donhang.getGiaHD(), donhang.getTongSoL()));
        holder.txtV_ViTri_dh1.setText(donhang.getVitriDH().getVitri());

        holder.relative1_dh1.setOnClickListener(view -> {
            Intent mIntent;
            if (key.key_dh_Nhap.equals(donhang.getTTDH())) {
                mIntent = new Intent(mContext, QuanAnAct.class);
                mIntent.putExtra(key.key_IDQA, donhang.getIDQA());
            } else {
                mIntent = new Intent(mContext, TheoDoiDonHang.class);
                mIntent.putExtra(key.key_DonHang_, donhang);
            }
            mContext.startActivity(mIntent);
        });


    }

    @Override
    public int getItemCount() {
        if (mListDH != null) {
            return mListDH.size();
        }
        return 0;
    }

    public class DonHang1AdapViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout relative1_dh1;
        private ImageView imgV_HinhQA_dh1;
        private TextView txtV_TenQA_dh1, txtV_ViTri_dh1, txtV_SL_dh1;

        public DonHang1AdapViewHolder(@NonNull View itemView) {
            super(itemView);

            imgV_HinhQA_dh1 = itemView.findViewById(R.id.imgV_HinhQA_dh1);

            txtV_TenQA_dh1 = itemView.findViewById(R.id.txtV_TenQA_dh1);
            txtV_ViTri_dh1 = itemView.findViewById(R.id.txtV_ViTri_dh1);
            txtV_SL_dh1 = itemView.findViewById(R.id.txtV_SL_dh1);

            relative1_dh1 = itemView.findViewById(R.id.relative1_dh1);
        }
    }
}
