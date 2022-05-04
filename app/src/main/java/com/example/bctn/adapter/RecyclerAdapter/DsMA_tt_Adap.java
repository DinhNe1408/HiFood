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
import com.example.bctn.domain.ctdh;
import com.example.bctn.domain.key;

import java.util.List;

public class DsMA_tt_Adap extends RecyclerView.Adapter<DsMA_tt_Adap.DsMA_tt_AdapViewHolder> {

    private Context mContext;
    private List<ctdh> mListMA;

    public DsMA_tt_Adap(Context mContext, List<ctdh> mListMA) {
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
        ctdh ctdh = mListMA.get(position);
        if (ctdh == null){
            return;
        }


        holder.imgV_HinhMA_tt.setImageBitmap(key.Byte2Bitmap(ctdh.getHinhMA()));
        holder.txtV_TenMA_tt.setText(ctdh.getTenMA());
        holder.GiaMA_tt.setText(key.Dou2Money(ctdh.getGiaMA()));
        holder.txtV_SLMA_tt.setText(String.format("x%d", ctdh.getSLMA()));
    }

    @Override
    public int getItemCount() {
        if (mListMA != null){
            return mListMA.size();
        }
        return 0;
    }

    public class DsMA_tt_AdapViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgV_HinhMA_tt;
        private TextView txtV_TenMA_tt, GiaMA_tt, txtV_SLMA_tt;
        public DsMA_tt_AdapViewHolder(@NonNull View itemView) {
            super(itemView);

            imgV_HinhMA_tt = itemView.findViewById(R.id.imgV_HinhMA_tt);

            txtV_TenMA_tt = itemView.findViewById(R.id.txtV_TenMA_tt);
            GiaMA_tt = itemView.findViewById(R.id.GiaMA_tt);
            txtV_SLMA_tt = itemView.findViewById(R.id.txtV_SLMA_tt);
        }
    }
}
