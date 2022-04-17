package com.example.bctn.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.DataLocalManager;
import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.activity.DangNhapAct;
import com.example.bctn.activity.ThongTinNguoiDung;
import com.example.bctn.activity.admin.QLDonHang;
import com.example.bctn.activity.admin.QLQuanAn;
import com.example.bctn.activity.admin.QLTaiKhoan;
import com.example.bctn.domain.key;
import com.example.bctn.domain.menu_option;

import java.util.List;

public class Menu_op2_Adap extends RecyclerView.Adapter<Menu_op2_Adap.Menu_op2_AdapViewHolder> {

    private Context mContext;
    private List<menu_option> mListKey;

    public Menu_op2_Adap(Context mContext, List<menu_option> mListKey) {
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
        menu_option mKey = mListKey.get(position);

        holder.txtV_MenuTaiKhoan.setText(mKey.getNoidungMenu());

        holder.CardView_1.setOnClickListener(view -> {
            switch (mKey.getIdMenu()) {
                case key.key_ThongTinNguoiDung:
                    if (MyAppication.mTaiKhoan.getIdTK() != -1) {
                        Intent intent = new Intent(mContext, ThongTinNguoiDung.class);
                        mContext.startActivity(intent);
                    } else {
                        Toast.makeText(mContext, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case key.key_DANGXUAT:
                    DataLocalManager.setTaiKhoan("", "");
                    Intent intent = new Intent(mContext, DangNhapAct.class);
                    mContext.startActivity(intent);

                    break;

                case key.key_QLDonHang:
                    Intent intent1 = new Intent(mContext, QLDonHang.class);
                    mContext.startActivity(intent1);
                    break;

                case key.key_QLTaiKhoan:
                    Intent intent2 = new Intent(mContext, QLTaiKhoan.class);
                    mContext.startActivity(intent2);
                    break;

                case key.key_QLQuanAn:
                    Intent intent3 = new Intent(mContext, QLQuanAn.class);
                    mContext.startActivity(intent3);
                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListKey != null) {
            return mListKey.size();
        }
        return 0;
    }

    public class Menu_op2_AdapViewHolder extends RecyclerView.ViewHolder {

        TextView txtV_MenuTaiKhoan;
        CardView CardView_1;

        public Menu_op2_AdapViewHolder(@NonNull View itemView) {
            super(itemView);

            txtV_MenuTaiKhoan = itemView.findViewById(R.id.txtV_MenuTaiKhoan);
            CardView_1 = itemView.findViewById(R.id.CardView_1);
        }
    }
}
