package com.example.bctn.adapter.RecyclerAdapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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
import com.example.bctn.fragment.QuanAn.QAThucDonFrag;

import java.util.List;

public class MonAn1Adap extends RecyclerView.Adapter<MonAn1Adap.MonAn1AdapViewHolder> {

    private final Context mContext;
    private List<monan> mListMA;
    private final int IDQA;

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
        int SL = 0;


        if (QAThucDonFrag.mDonhang.getCthdMap().size() != 0) {
            ctdh ctdh = QAThucDonFrag.mDonhang.getCthdMap().get(monan.getIdMA());
            if (ctdh != null) {
                SL = ctdh.getSLMA();
            }
        }

        if (monan.getHinhMA() != null){
            holder.imgV_HinhMA_ma1.setImageBitmap(key.Byte2Bitmap(monan.getHinhMA()));

        }
        holder.txtV_TenMA_ma1.setText(monan.getTenMA());
        holder.txtv_GiaMA_ma1.setText(key.Dou2Money(monan.getGiaMA()));
        holder.txtV_SoL_ma1.setText(String.valueOf(SL));

        if (SL < 1) {
            show(holder, View.INVISIBLE);
        }

        holder.imgB_Giam_ma1.setOnClickListener(view -> {
            int Sl = Integer.parseInt(holder.txtV_SoL_ma1.getText().toString()) - 1;

            if (Sl <= 0) {
                show(holder, View.INVISIBLE);
            }
            QAThucDonFrag.CapNhapMonAn(monan.getIdMA(), Sl);
            holder.txtV_SoL_ma1.setText(String.valueOf(Sl));
        });

        holder.imgB_Tang_ma1.setOnClickListener(view -> {
            int Sl = Integer.parseInt(holder.txtV_SoL_ma1.getText().toString()) + 1;
            show(holder, View.VISIBLE);
            holder.txtV_SoL_ma1.setText(String.valueOf(Sl));
            if (Sl == 1) {
                QAThucDonFrag.TaoMonAn(monan.getIdMA(), monan.getHinhMA(), monan.getTenMA(), monan.getGiaMA(), Sl);
            } else {
                QAThucDonFrag.CapNhapMonAn(monan.getIdMA(), Sl);
            }
        });

        holder.relative1_ma1.setOnClickListener(view -> {

        });
        holder.txtV_GhiChu_ma1.setOnClickListener(view -> {
            openDialog(monan.getIdMA());
        });
    }

    private void openDialog(int IDMA) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_quanan);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setGravity(Gravity.CENTER);

        EditText txtV_GhiChu_dialog = dialog.findViewById(R.id.txtV_GhiChu_dialog);
        ImageButton imgB_Dong_dialog = dialog.findViewById(R.id.imgB_Dong_dialog);
        Button btn_LuuGhiChu_dialog = dialog.findViewById(R.id.btn_LuuGhiChu_dialog);

        imgB_Dong_dialog.setOnClickListener(view -> {
            dialog.dismiss();
        });

        btn_LuuGhiChu_dialog.setOnClickListener(view -> {
            if (txtV_GhiChu_dialog.getText().length() != 0) {
                QAThucDonFrag.CapNhapGhiChuMonAn(IDMA, txtV_GhiChu_dialog.getText().toString());
                dialog.dismiss();
                Toast.makeText(mContext, "Ghi chú đã được cập nhật", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    private void show(MonAn1AdapViewHolder holder, int HIDE) {
        holder.txtV_SoL_ma1.setVisibility(HIDE);
        holder.imgB_Giam_ma1.setVisibility(HIDE);
        holder.txtV_GhiChu_ma1.setVisibility(HIDE);
    }

    @Override
    public int getItemCount() {
        return mListMA == null ? 0 : mListMA.size();
    }

    public class MonAn1AdapViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgV_HinhMA_ma1;
        private TextView txtV_TenMA_ma1, txtV_SoL_ma1, txtv_GiaMA_ma1, txtV_GhiChu_ma1;
        private ImageButton imgB_Giam_ma1, imgB_Tang_ma1;
        private RelativeLayout relative1_ma1;

        public MonAn1AdapViewHolder(@NonNull View itemView) {
            super(itemView);

            imgV_HinhMA_ma1 = itemView.findViewById(R.id.imgV_HinhMA_ma1);

            txtV_TenMA_ma1 = itemView.findViewById(R.id.txtV_TenMA_ma1);
            txtV_SoL_ma1 = itemView.findViewById(R.id.txtV_SoL_ma1);
            txtv_GiaMA_ma1 = itemView.findViewById(R.id.txtv_GiaMA_ma1);
            txtV_GhiChu_ma1 = itemView.findViewById(R.id.txtV_GhiChu_ma1);

            imgB_Giam_ma1 = itemView.findViewById(R.id.imgB_Giam_ma1);
            imgB_Tang_ma1 = itemView.findViewById(R.id.imgB_Tang_ma1);

            relative1_ma1 = itemView.findViewById(R.id.relative1_ma1);
        }
    }
}
