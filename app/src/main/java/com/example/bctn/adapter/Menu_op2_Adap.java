package com.example.bctn.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.R;
import com.example.bctn.activity.DangNhapAct;
import com.example.bctn.activity.TheoDoiDonHang;
import com.example.bctn.domain.key;

import java.util.List;

public class Menu_op2_Adap extends RecyclerView.Adapter<Menu_op2_Adap.Menu_op2_AdapViewHolder> {

    private Context mContext;
    private List<Integer> mListKey;

    public Menu_op2_Adap(Context mContext, List<Integer> mListKey) {
        this.mContext = mContext;
        this.mListKey = mListKey;
    }

    @NonNull
    @Override
    public Menu_op2_AdapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_menu_option2, parent, false);
        return new Menu_op2_Adap.Menu_op2_AdapViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull Menu_op2_AdapViewHolder holder, int position) {
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

        holder.CardView_1.setOnClickListener(view -> {
            switch (mKey){
                case key.key_GIOITHIEU:
                    Intent intent = new Intent(mContext, DangNhapAct.class);
                    mContext.startActivity(intent);
                case key.key_CAIDAT:
                    Intent intent1 = new Intent(mContext, TheoDoiDonHang.class);
                    mContext.startActivity(intent1);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListKey != null){
            return mListKey.size();
        }
        return 0;
    }

    public class Menu_op2_AdapViewHolder extends RecyclerView.ViewHolder{

        TextView txtV_MenuTaiKhoan;
        CardView CardView_1;
        public Menu_op2_AdapViewHolder(@NonNull View itemView) {
            super(itemView);

            txtV_MenuTaiKhoan = itemView.findViewById(R.id.txtV_MenuTaiKhoan);
            CardView_1 = itemView.findViewById(R.id.CardView_1);
        }
    }
}
