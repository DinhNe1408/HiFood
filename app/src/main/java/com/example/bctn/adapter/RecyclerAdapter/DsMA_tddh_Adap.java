package com.example.bctn.adapter.RecyclerAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.R;
import com.example.bctn.domain.ctdh;
import com.example.bctn.domain.key;

import java.util.List;

public class DsMA_tddh_Adap extends RecyclerView.Adapter<DsMA_tddh_Adap.DsMA_tddhViewHolder> {

    private Context mContext;
    private List<ctdh> mListCTHD;

    public DsMA_tddh_Adap(Context mContext, List<ctdh> mListCTHD) {
        this.mContext = mContext;
        this.mListCTHD = mListCTHD;
    }

    @NonNull
    @Override
    public DsMA_tddhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.viewholder_dsma_tddh, parent, false);
        return new DsMA_tddh_Adap.DsMA_tddhViewHolder(mView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DsMA_tddhViewHolder holder, int position) {
        ctdh ctdh = mListCTHD.get(position);

        if (ctdh == null) {
            return;
        }
        holder.txtV_TenMA_dsma_tddh.setText(ctdh.getTenMA());
        holder.txtV_GiaMA_dsma_tddh.setText(key.Dou2Money(ctdh.getGiaMA()));
        holder.txtV_SoL_dsma_tddh.setText("x"+ ctdh.getSLMA());
    }

    @Override
    public int getItemCount() {
        if (mListCTHD != null) {
            return mListCTHD.size();
        }
        return 0;
    }

    public class DsMA_tddhViewHolder extends RecyclerView.ViewHolder {

        TextView txtV_TenMA_dsma_tddh, txtV_SoL_dsma_tddh, txtV_GiaMA_dsma_tddh;

        public DsMA_tddhViewHolder(@NonNull View itemView) {
            super(itemView);

            txtV_TenMA_dsma_tddh = itemView.findViewById(R.id.txtV_TenMA_dsma_tddh);
            txtV_SoL_dsma_tddh = itemView.findViewById(R.id.txtV_SoL_dsma_tddh);
            txtV_GiaMA_dsma_tddh = itemView.findViewById(R.id.txtV_GiaMA_dsma_tddh);
        }
    }
}
