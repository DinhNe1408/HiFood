package com.example.bctn.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.example.bctn.activity.admin.QLMonAn;
import com.example.bctn.activity.admin.QLQuanAn;
import com.example.bctn.activity.admin.QLTaiKhoan;
import com.example.bctn.activity.admin.ThongKe;
import com.example.bctn.activity.quanan.QADonHang;
import com.example.bctn.activity.quanan.QuanAn;
import com.example.bctn.activity.quanan.ThongKeQA;
import com.example.bctn.activity.quanan.ThongTinQA;
import com.example.bctn.domain.key;
import com.example.bctn.domain.menu_option;
import com.example.bctn.domain.taikhoan;
import com.example.bctn.domain.vitri;

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

        holder.txtV_NoiDung_op2.setText(mKey.getNoidungMenu());
        if (mKey.getHinhMenu() != -1) {
            holder.imgV_Hinh1_op2.setImageResource(mKey.getHinhMenu());
        }

        holder.CardView_1.setOnClickListener(view -> {
            switch (mKey.getIdMenu()) {
                // Người dùng
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
                    MyAppication.mViTri = null;
                    MyAppication.mTaiKhoan = new taikhoan();
                    MyAppication.mTaiKhoan.setCurVitri(MyAppication.curViTri);
                    Intent intent = new Intent(mContext, DangNhapAct.class);
                    mContext.startActivity(intent);
                    break;

                // Quản trị viên
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

                case key.key_ThongKe:
                    Intent intent8 = new Intent(mContext, ThongKe.class);
                    mContext.startActivity(intent8);
                    break;
                // Quán ăn
                case key.key_QLMonAn:
                    Intent intent4 = new Intent(mContext, QLMonAn.class);
                    intent4.putExtra(key.key_IDQA, MyAppication.mTaiKhoan.getIDQA());
                    mContext.startActivity(intent4);
                    break;

                case key.key_DonHangQA:
                    Intent intent5 = new Intent(mContext, QADonHang.class);
                    mContext.startActivity(intent5);
                    break;

                    case key.key_ThongTinQA:
                    Intent intent6 = new Intent(mContext, ThongTinQA.class);
                    intent6.putExtra(key.key_IDQA, MyAppication.mTaiKhoan.getIDQA());
                    mContext.startActivity(intent6);
                    break;

                case key.key_ThongKeQA:
                    Intent intent7 = new Intent(mContext, ThongKeQA.class);
                    intent7.putExtra(key.key_IDQA, MyAppication.mTaiKhoan.getIDQA());
                    mContext.startActivity(intent7);
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

        ImageView imgV_Hinh1_op2;
        TextView txtV_NoiDung_op2;
        CardView CardView_1;

        public Menu_op2_AdapViewHolder(@NonNull View itemView) {
            super(itemView);

            imgV_Hinh1_op2 = itemView.findViewById(R.id.imgV_Hinh1_op2);
            txtV_NoiDung_op2 = itemView.findViewById(R.id.txtV_NoiDung_op2);
            CardView_1 = itemView.findViewById(R.id.CardView_1);
        }
    }
}
