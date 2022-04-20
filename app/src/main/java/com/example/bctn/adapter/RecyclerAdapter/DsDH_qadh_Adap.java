package com.example.bctn.adapter.RecyclerAdapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.MyAppication;
import com.example.bctn.R;

import com.example.bctn.domain.ctdh;
import com.example.bctn.domain.donhang;
import com.example.bctn.domain.key;

import java.util.List;

public class DsDH_qadh_Adap extends RecyclerView.Adapter<DsDH_qadh_Adap.DsDH_qadh_Viewholder> {

    private List<donhang> donhangList;
    private Context mContext;

    public DsDH_qadh_Adap(Context mContext, List<donhang> donhangList) {
        this.donhangList = donhangList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public DsDH_qadh_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_qa_dh, parent, false);
        return new DsDH_qadh_Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DsDH_qadh_Viewholder holder, int position) {
        donhang donhang = donhangList.get(position);

        holder.txtV_IDDH.setText("Mã đơn hàng: " + donhang.getIDDH());
        holder.txtV_TenVaSDT.setText(donhang.getTenNN() + " - " + donhang.getSDTNN());
        holder.txtV_ViTri.setText(donhang.getVitriDH());
        holder.txtV_TongTien.setText(key.Dou2Money(donhang.getTongDH()));
        holder.txtV_TGGiao.setText(donhang.getTGGiao().toString());

        holder.itemView.setOnClickListener(view -> {
            openDialog(position);
        });

        if (!donhang.getTTDH().equals(key.key_dh_HoanThanh)) {
            holder.txtV_HoanThanh.setOnClickListener(view -> {
                new AlertDialog.Builder(mContext)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Thông báo")
                        .setMessage("Đơn hàng này đã hoàn thành")
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MyAppication.mDao.CapNhatTTGiao(donhang.getIDDH(), key.key_dh_HoanThanh);
                                Toast.makeText(mContext, "Đơn hàng đã được cập nhật", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Hủy", null)
                        .show();
            });
        } else {
            holder.txtV_HoanThanh.setWidth(0);
            holder.txtV_HoanThanh.setHeight(0);
        }


    }

    private void openDialog(int position) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_qa_don_hang);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setGravity(Gravity.CENTER);

        RecyclerView recV_DSQADonHang = dialog.findViewById(R.id.recV_DSQADonHang);
        ImageButton imgB_Dong_dialog = dialog.findViewById(R.id.imgB_Dong_dialog);

        imgB_Dong_dialog.setOnClickListener(view -> dialog.dismiss());

        List<ctdh> ctdhList = MyAppication.mDao.ListQACTDH(donhangList.get(position).getIDDH());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        DsCTDH_qadh_Adap dsCTDH_qadh_adap = new DsCTDH_qadh_Adap(mContext, ctdhList);

        recV_DSQADonHang.setLayoutManager(linearLayoutManager);
        recV_DSQADonHang.setAdapter(dsCTDH_qadh_adap);

        dialog.show();
    }

    @Override
    public int getItemCount() {
        if (donhangList != null) {
            return donhangList.size();
        }
        return 0;
    }

    public class DsDH_qadh_Viewholder extends RecyclerView.ViewHolder {

        private TextView txtV_IDDH, txtV_TenVaSDT, txtV_ViTri, txtV_TGGiao, txtV_TongTien, txtV_HoanThanh;

        public DsDH_qadh_Viewholder(@NonNull View itemView) {
            super(itemView);

            txtV_IDDH = itemView.findViewById(R.id.txtV_IDDH);
            txtV_TenVaSDT = itemView.findViewById(R.id.txtV_TenVaSDT);
            txtV_ViTri = itemView.findViewById(R.id.txtV_ViTri);
            txtV_TGGiao = itemView.findViewById(R.id.txtV_TGGiao);
            txtV_TongTien = itemView.findViewById(R.id.txtV_TongTien);
            txtV_HoanThanh = itemView.findViewById(R.id.txtV_HoanThanh);
        }
    }
}
