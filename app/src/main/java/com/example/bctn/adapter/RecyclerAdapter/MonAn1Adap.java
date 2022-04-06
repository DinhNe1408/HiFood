package com.example.bctn.adapter.RecyclerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.domain.ctdh;
import com.example.bctn.domain.key;
import com.example.bctn.domain.monan;

import java.util.List;

public class MonAn1Adap extends RecyclerView.Adapter<MonAn1Adap.MonAn1AdapViewHolder> {

    private Context mContext;
    private List<monan> mListMA;
    private int IDQA;

    public MonAn1Adap(Context mContext, List<monan> mListMA, int IDQA) {
        this.mContext = mContext;
        this.mListMA = mListMA;
        this.IDQA = IDQA;
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
        final int[] SL = {0};

        if (!MyAppication.mTaiKhoan.isdonhangList()) {
            ctdh ctdh = MyAppication.mTaiKhoan.getListHDinTK(IDQA, monan.getIdMA());
            if (ctdh != null) {
                SL[0] = ctdh.getSLMA();
            }
        }


        holder.txtV_TenMA_ma1.setText(monan.getTenMA());
        holder.txtv_GiaMA_ma1.setText(key.Dou2Money(monan.getGiaMA()));
        holder.txtV_SoL_ma1.setText(String.valueOf(SL[0]));

        if (SL[0] <= 1) {
            holder.txtV_SoL_ma1.setVisibility(View.INVISIBLE);
            holder.imgB_Giam_ma1.setVisibility(View.INVISIBLE);
        }


        holder.imgB_Giam_ma1.setOnClickListener(view -> {
            SL[0] = Integer.parseInt(holder.txtV_SoL_ma1.getText().toString());
            if (SL[0] <= 1) {
                holder.txtV_SoL_ma1.setVisibility(View.INVISIBLE);
                holder.imgB_Giam_ma1.setVisibility(View.INVISIBLE);
            }
            SL[0] -= 1;
            holder.txtV_SoL_ma1.setText(String.valueOf(SL[0]));

        });
        EditText editText;

        holder.imgB_Tang_ma1.setOnClickListener(view -> {
            SL[0] = Integer.parseInt(holder.txtV_SoL_ma1.getText().toString());
            SL[0] += 1;
            holder.txtV_SoL_ma1.setVisibility(View.VISIBLE);
            holder.imgB_Giam_ma1.setVisibility(View.VISIBLE);
            holder.txtV_SoL_ma1.setText(String.valueOf(SL[0]));
        });

        holder.relative1_ma1.setOnClickListener(view -> {

        });
    }

    @Override
    public int getItemCount() {
        if (mListMA.size() != 0) {
            return mListMA.size();
        }
        return 0;
    }

    public class MonAn1AdapViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgV_HinhMA_ma1;
        private TextView txtV_TenMA_ma1, txtV_SoL_ma1, txtv_GiaMA_ma1;
        private ImageButton imgB_Giam_ma1, imgB_Tang_ma1;
        private RelativeLayout relative1_ma1;

        public MonAn1AdapViewHolder(@NonNull View itemView) {
            super(itemView);

            imgV_HinhMA_ma1 = itemView.findViewById(R.id.imgV_HinhMA_ma1);

            txtV_TenMA_ma1 = itemView.findViewById(R.id.txtV_TenMA_ma1);
            txtV_SoL_ma1 = itemView.findViewById(R.id.txtV_SoL_ma1);
            txtv_GiaMA_ma1 = itemView.findViewById(R.id.txtv_GiaMA_ma1);

            imgB_Giam_ma1 = itemView.findViewById(R.id.imgB_Giam_ma1);
            imgB_Tang_ma1 = itemView.findViewById(R.id.imgB_Tang_ma1);

            relative1_ma1 = itemView.findViewById(R.id.relative1_ma1);
        }
    }
}
