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

public class MonAn1Adap extends RecyclerView.Adapter<MonAn1Adap.MonAn1AdapViewHolder> {
    private Context mContext;
    private List<monan> mListMA;

    public MonAn1Adap(Context mContext, List<monan> mListMA) {
        this.mContext = mContext;
        this.mListMA = mListMA;
    }

    @NonNull
    @Override
    public MonAn1AdapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_mon_an1, parent, false);
        return new MonAn1AdapViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MonAn1AdapViewHolder holder, int position) {
        monan monan = mListMA.get(position);

        holder.hMA_1.setImageResource(monan.getHMA());
        holder.tenMA_1.setText(monan.getTenMA());
    }

    @Override
    public int getItemCount() {
        if (mListMA.size() != 0 ){
            return mListMA.size();
        }
        return 0;
    }

    public class MonAn1AdapViewHolder extends RecyclerView.ViewHolder{

        TextView tenMA_1;
        ImageView hMA_1;

        public MonAn1AdapViewHolder(@NonNull View itemView) {
            super(itemView);

            tenMA_1 = itemView.findViewById(R.id.tenMA_1);
            hMA_1 = itemView.findViewById(R.id.hMA_1);
        }
    }
}
