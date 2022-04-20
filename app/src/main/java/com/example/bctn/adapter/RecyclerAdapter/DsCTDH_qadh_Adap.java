package com.example.bctn.adapter.RecyclerAdapter;

import android.content.Context;
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

public class DsCTDH_qadh_Adap extends RecyclerView.Adapter<DsCTDH_qadh_Adap.DsCTDH_qadh_Viewholder> {

    private Context mContext;
    private List<ctdh> ctdhList;

    public DsCTDH_qadh_Adap(Context mContext, List<ctdh> ctdhList) {
        this.mContext = mContext;
        this.ctdhList = ctdhList;
    }

    @NonNull
    @Override
    public DsCTDH_qadh_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_dsctdh_qadh, parent, false);
        return new DsCTDH_qadh_Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DsCTDH_qadh_Viewholder holder, int position) {
        ctdh ctdh = ctdhList.get(position);

        holder.txtV_TenMA_dsctdh_qadh.setText(ctdh.getTenMA());
        holder.txtV_SoL_dsctdh_qadh.setText("x" + ctdh.getSLMA());
        holder.txtV_GiaMA_dsctdh_qadh.setText(key.Dou2Money(ctdh.getGiaMA()));
        holder.txtV_TongTienMA_dsctdh_qadh.setText(key.Dou2Money(ctdh.getGiaMA() * ctdh.getSLMA()));

        if (ctdh.getGhiChu() == null) {
            holder.txtV_GhiChu_dsctdh_qadh.setHeight(0);
            holder.txtV_GhiChu_dsctdh_qadh.setWidth(0);
        } else {
            holder.txtV_GhiChu_dsctdh_qadh.setText(ctdh.getGhiChu());
        }

    }

    @Override
    public int getItemCount() {
        if (ctdhList != null) {
            return ctdhList.size();
        }
        return 0;
    }

    public class DsCTDH_qadh_Viewholder extends RecyclerView.ViewHolder {

        TextView txtV_TenMA_dsctdh_qadh, txtV_GiaMA_dsctdh_qadh, txtV_SoL_dsctdh_qadh, txtV_TongTienMA_dsctdh_qadh, txtV_GhiChu_dsctdh_qadh;

        public DsCTDH_qadh_Viewholder(@NonNull View itemView) {
            super(itemView);

            txtV_TenMA_dsctdh_qadh = itemView.findViewById(R.id.txtV_TenMA_dsctdh_qadh);
            txtV_GiaMA_dsctdh_qadh = itemView.findViewById(R.id.txtV_GiaMA_dsctdh_qadh);
            txtV_SoL_dsctdh_qadh = itemView.findViewById(R.id.txtV_SoL_dsctdh_qadh);
            txtV_TongTienMA_dsctdh_qadh = itemView.findViewById(R.id.txtV_TongTienMA_dsctdh_qadh);
            txtV_GhiChu_dsctdh_qadh = itemView.findViewById(R.id.txtV_GhiChu_dsctdh_qadh);
        }
    }
}
