package com.example.bctn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.R;
import com.example.bctn.domain.key;

import java.util.List;

public class TaiKhoanAdap extends RecyclerView.Adapter<TaiKhoanAdap.TaiKhoanViewHolder> {

    private Context mContext;
    private List<Integer> mListKey;

    public TaiKhoanAdap(Context mContext, List<Integer> mListKey) {
        this.mContext = mContext;
        this.mListKey = mListKey;
    }

    @NonNull
    @Override
    public TaiKhoanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_menutaikhoan, parent, false);
        return new TaiKhoanAdap.TaiKhoanViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaiKhoanViewHolder holder, int position) {
        int mKey = mListKey.get(position);

        switch (mKey){
            case key.key_THONGTINCANHAN :
                holder.txtV_MenuTaiKhoan.setText("Thông tin tài khoản");
                break;
            case key.key_LICHSUDONHANG:
                holder.txtV_MenuTaiKhoan.setText("Lịch sử đơn hàng");
                break;
            case key.key_GOPY:
                holder.txtV_MenuTaiKhoan.setText("Góp ý");
                break;
            case key.key_CAIDAT:
                holder.txtV_MenuTaiKhoan.setText("Cài đặt");
                break;
            case key.key_GIOITHIEU:
                holder.txtV_MenuTaiKhoan.setText("Giới thiệu");
                break;
        }
    }

    @Override
    public int getItemCount() {
        if(mListKey != null){
            return mListKey.size();
        }
        return 0;
    }

    public class TaiKhoanViewHolder extends RecyclerView.ViewHolder{

        TextView txtV_MenuTaiKhoan;

        public TaiKhoanViewHolder(@NonNull View itemView) {
            super(itemView);

            txtV_MenuTaiKhoan = itemView.findViewById(R.id.txtV_MenuTaiKhoan);
        }
    }
}
