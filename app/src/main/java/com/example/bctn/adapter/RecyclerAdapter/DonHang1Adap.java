package com.example.bctn.adapter.RecyclerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.R;
import com.example.bctn.domain.donhang;

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
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_don_hang1,parent,false);
        return new DonHang1AdapViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHang1AdapViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (mListDH.size()!=0){
            return mListDH.size();
        }
        return 0;
    }

    public class DonHang1AdapViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgV_HinhQA_dh1;
        private TextView txtV_TenQA_dh1, txtV_ViTri_dh1, txtV_SL_dh1;

        public DonHang1AdapViewHolder(@NonNull View itemView) {
            super(itemView);

            imgV_HinhQA_dh1 = itemView.findViewById(R.id.imgV_HinhQA_dh1);

            txtV_TenQA_dh1 = itemView.findViewById(R.id.txtV_TenQA_dh1);
            txtV_ViTri_dh1 = itemView.findViewById(R.id.txtV_ViTri_dh1);
            txtV_SL_dh1 = itemView.findViewById(R.id.txtV_SL_dh1);
        }
    }
}
