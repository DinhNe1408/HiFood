package com.example.bctn.adapter.RecyclerAdapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.bctn.domain.quanan;

import java.util.List;

public class QuanAn1Adap extends RecyclerView.Adapter<QuanAn1Adap.QuanAn1AdapViewHolder> {
    private final Context mContext;
    private final List<quanan> mList;

    public QuanAn1Adap(Context mContext, List<quanan> mList) {
        this.mContext = mContext;
        this.mList = mList;

    }

    @NonNull
    @Override
    public QuanAn1AdapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_quan_an1, parent, false);
        return new QuanAn1AdapViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuanAn1AdapViewHolder holder, int position) {
        quanan quanan = mList.get(position);

        holder.hinhQA_1.setImageResource(R.drawable.w42419);
        holder.tenQA_1.setText(quanan.getTenQA());
        holder.vitriQA_1.setText(quanan.getVitriQA().getVitri());
        //holder.saoQA_1.setText();

        holder.relative_ma.setOnClickListener(view -> {
            Intent mIntent = new Intent(mContext, QuanAnAct.class);
            mIntent.putExtra("IDQA",quanan.getIdQA());
            mContext.startActivity(mIntent);
        });
    }

    @Override
    public int getItemCount() {
        if (mList.size() != 0){
            return mList.size();
        }
        return 0;
    }

    public class QuanAn1AdapViewHolder extends RecyclerView.ViewHolder{

        TextView tenQA_1, saoQA_1,vitriQA_1;
        ImageView hinhQA_1;
        RelativeLayout relative_ma;

        public QuanAn1AdapViewHolder(@NonNull View itemView) {
            super(itemView);

            tenQA_1 = itemView.findViewById(R.id.tenQA_1);
            saoQA_1 = itemView.findViewById(R.id.saoQA_1);
            vitriQA_1 = itemView.findViewById(R.id.vitriQA_1);

            hinhQA_1 = itemView.findViewById(R.id.hinhQA_1);
            relative_ma = itemView.findViewById(R.id.relative_ma);
        }
    }
}
