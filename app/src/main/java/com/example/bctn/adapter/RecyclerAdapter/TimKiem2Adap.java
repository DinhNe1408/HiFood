package com.example.bctn.adapter.RecyclerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.R;
import com.example.bctn.domain.key;
import com.example.bctn.domain.monan;
import com.example.bctn.domain.quanan;

import java.util.List;

public class TimKiem2Adap extends RecyclerView.Adapter<TimKiem2Adap.TimKiemViewHolder> {
    private Context mContext;
    private List<monan> monanList;

    public TimKiem2Adap(Context mContext, List<monan> monanList) {
        this.mContext = mContext;
        this.monanList = monanList;
    }

    @NonNull
    @Override
    public TimKiemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_mon_an2, parent, false);
        return new TimKiemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimKiemViewHolder holder, int position) {
        monan monan = monanList.get(position);

        holder.imgV_HinhMA_ma2.setImageBitmap(key.Byte2Bitmap(monan.getHinhMA()));
        holder.txtV_TenMA_ma2.setText(monan.getTenMA());
        holder.txtv_GiaMA_ma2.setText(key.Dou2Money(monan.getGiaMA()));
    }

    @Override
    public int getItemCount() {
        if (monanList != null) {
            return monanList.size();
        }
        return 0;
    }

    public class TimKiemViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgV_HinhMA_ma2;
        private TextView txtV_TenMA_ma2, txtv_GiaMA_ma2;

        public TimKiemViewHolder(@NonNull View itemView) {
            super(itemView);

            imgV_HinhMA_ma2 = itemView.findViewById(R.id.imgV_HinhMA_ma2);
            txtV_TenMA_ma2 = itemView.findViewById(R.id.txtV_TenMA_ma2);
            txtv_GiaMA_ma2 = itemView.findViewById(R.id.txtv_GiaMA_ma2);
        }
    }
}
