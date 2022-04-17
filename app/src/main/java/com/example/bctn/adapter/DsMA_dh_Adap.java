package com.example.bctn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.R;
import com.example.bctn.domain.key;
import com.example.bctn.domain.monan;

import java.util.List;

public class DsMA_dh_Adap extends RecyclerView.Adapter<DsMA_dh_Adap.DsMA_dh_AdapViewHolder> {

    private Context mContext;
    private List<monan> mListMA;


    public DsMA_dh_Adap(Context mContext, List<monan> mListMA) {
        this.mContext = mContext;
        this.mListMA = mListMA;
    }

    @NonNull
    @Override
    public DsMA_dh_AdapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_dsma_dh,parent,false);
        return new DsMA_dh_AdapViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull DsMA_dh_AdapViewHolder holder, int position) {
        monan monan = mListMA.get(position);

        if (monan == null){
            return;
        }

        holder.imgV_HinhMA_dh.setImageBitmap(key.Byte2Bitmap(monan.getHinhMA()));
    }

    @Override
    public int getItemCount() {
        if(mListMA.size() > 0){
            return mListMA.size();
        }
        return 0;
    }

    public class DsMA_dh_AdapViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgV_HinhMA_dh;
        public DsMA_dh_AdapViewHolder(@NonNull View itemView) {
            super(itemView);
            imgV_HinhMA_dh = itemView.findViewById(R.id.imgV_HinhMA_dh);
        }
    }
}
