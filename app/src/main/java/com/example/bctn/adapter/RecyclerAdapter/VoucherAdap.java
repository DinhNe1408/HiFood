package com.example.bctn.adapter.RecyclerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.PluralsRes;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.R;
import com.example.bctn.domain.voucher;

import java.util.List;

public class VoucherAdap extends RecyclerView.Adapter<VoucherAdap.VoucherViewHolder> {

    private Context mContext;
    private List<voucher> voucherList;

    public VoucherAdap(Context mContext, List<voucher> voucherList) {
        this.mContext = mContext;
        this.voucherList = voucherList;
    }

    @NonNull
    @Override
    public VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_voucher, parent, false);
        return new VoucherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (voucherList != null) {
            return voucherList.size();
        }
        return 0;
    }

    public class VoucherViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgV_HinhVoucher;
        private TextView txtV_TenVoucher, txtV_ThoiGianVoucher, txtV_TienGiamVoucher;

        public VoucherViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
