package com.example.bctn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.R;
import com.example.bctn.domain.monan;

import java.util.List;

public class DsMA_tt_Adap extends RecyclerView.Adapter<DsMA_tt_Adap.DsMA_tt_AdapViewHolder> {

    private Context mContext;
    private List<monan> mListMA;


    public DsMA_tt_Adap(Context mContext, List<monan> mListMA) {
        this.mContext = mContext;
        this.mListMA = mListMA;
    }

    @NonNull
    @Override
    public DsMA_tt_AdapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.viewholder_dsma_tt,parent,false);
        return new DsMA_tt_AdapViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull DsMA_tt_AdapViewHolder holder, int position) {
        monan monan = mListMA.get(position);
        if (monan == null){
            return;
        }

        holder.imgV_HinhMA_tt.setImageResource(monan.getHMA());
        holder.txtV_TenMA_tt.setText(monan.getTenMA());
        //holder.GiaMA_tt.setText(monan.getGiaMA());

    }

    @Override
    public int getItemCount() {
        if (mListMA.size() > 0){
            return mListMA.size();
        }
        return 0;
    }

    public class DsMA_tt_AdapViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgV_HinhMA_tt;
        private TextView txtV_TenMA_tt, GiaMA_tt, SLMA_tt;
        public DsMA_tt_AdapViewHolder(@NonNull View itemView) {
            super(itemView);

            imgV_HinhMA_tt = itemView.findViewById(R.id.imgV_HinhMA_tt);

            txtV_TenMA_tt = itemView.findViewById(R.id.txtV_TenMA_tt);
            GiaMA_tt = itemView.findViewById(R.id.GiaMA_tt);
            SLMA_tt = itemView.findViewById(R.id.SLMA_tt);
        }
    }
}
