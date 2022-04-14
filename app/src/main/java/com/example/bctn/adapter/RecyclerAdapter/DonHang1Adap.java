package com.example.bctn.adapter.RecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.bctn.activity.TheoDoiDonHang;
import com.example.bctn.domain.donhang;
import com.example.bctn.domain.donhang_dhfrag;
import com.example.bctn.domain.key;

import java.text.DecimalFormat;
import java.util.List;

public class DonHang1Adap extends RecyclerView.Adapter<DonHang1Adap.DonHang1AdapViewHolder> {

    private Context mContext;
    private List<donhang_dhfrag> mListDH;

    public DonHang1Adap(Context mContext, List<donhang_dhfrag> mListDH) {
        this.mContext = mContext;
        this.mListDH = mListDH;
    }

    @NonNull
    @Override
    public DonHang1AdapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_don_hang1, parent, false);
        return new DonHang1AdapViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHang1AdapViewHolder holder, int position) {
        donhang_dhfrag donhang_dhfrag = mListDH.get(position);

        if (donhang_dhfrag == null)
            return;

        holder.txtV_TenQA_dh1.setText(donhang_dhfrag.getTenQA());
        holder.txtV_SL_dh1.setText(key.TienvaSL(donhang_dhfrag.getTongTienMA(), donhang_dhfrag.getSoPhan()));
        holder.txtV_ViTri_dh1.setText(donhang_dhfrag.getViTriQA());

        if (donhang_dhfrag.getHinhQA() == null) {
            holder.imgV_HinhQA_dh1.setImageResource(R.drawable.w42419);
        } else {
            holder.imgV_HinhQA_dh1.setImageBitmap(key.Byte2Bitmap(donhang_dhfrag.getHinhQA()));
        }

        holder.relative1_dh1.setOnClickListener(view -> {
            Intent mIntent;
            if (key.key_dh_Nhap.equals(donhang_dhfrag.getTTGiao())) {
                mIntent = new Intent(mContext, QuanAnAct.class);
                mIntent.putExtra(key.key_IDQA, donhang_dhfrag.getIDQA());
            } else {
                mIntent = new Intent(mContext, TheoDoiDonHang.class);
                mIntent.putExtra(key.key_IDDH, donhang_dhfrag.getIDDH());
                mIntent.putExtra(key.key_TTGiao, donhang_dhfrag.getTTGiao());
            }
            mContext.startActivity(mIntent);
        });
    }

    @Override
    public int getItemCount() {
        if (mListDH != null) {
            return mListDH.size();
        }
        return 0;
    }

    public class DonHang1AdapViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout relative1_dh1;
        private ImageView imgV_HinhQA_dh1;
        private TextView txtV_TenQA_dh1, txtV_ViTri_dh1, txtV_SL_dh1;

        public DonHang1AdapViewHolder(@NonNull View itemView) {
            super(itemView);

            imgV_HinhQA_dh1 = itemView.findViewById(R.id.imgV_HinhQA_dh1);

            txtV_TenQA_dh1 = itemView.findViewById(R.id.txtV_TenQA_dh1);
            txtV_ViTri_dh1 = itemView.findViewById(R.id.txtV_ViTri_dh1);
            txtV_SL_dh1 = itemView.findViewById(R.id.txtV_SL_dh1);

            relative1_dh1 = itemView.findViewById(R.id.relative1_dh1);
        }
    }
}
