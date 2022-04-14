package com.example.bctn.adapter.RecyclerAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.R;
import com.example.bctn.activity.admin.UpTaiKhoan;
import com.example.bctn.domain.key;
import com.example.bctn.domain.taikhoan;

import java.util.ArrayList;
import java.util.List;

public class DsTK_qltk_Adap extends RecyclerView.Adapter<DsTK_qltk_Adap.DsTK_qltk_Viewholder> implements Filterable {

    private Context mContext;
    private List<taikhoan> taikhoanList;
    private final List<taikhoan> OldtaikhoanList;


    public DsTK_qltk_Adap(Context mContext, List<taikhoan> taikhoanList) {
        this.mContext = mContext;
        this.taikhoanList = taikhoanList;
        this.OldtaikhoanList = taikhoanList;
    }

    @NonNull
    @Override
    public DsTK_qltk_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_dstk_qltk, parent, false);

        return new DsTK_qltk_Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DsTK_qltk_Viewholder holder, int position) {
        taikhoan taikhoan = taikhoanList.get(position);

        if (taikhoan == null)
            return;

        holder.txtV_IDTK_qltk.setText("Mã tài khoản : " + taikhoan.getIdTK());
        holder.txtV_SDT_qltk.setText(taikhoan.getSdtTK());
        holder.txtV_TenTK_qltk.setText(taikhoan.getTenTK());

        if (taikhoan.getHinhTK() != null) {
            holder.imgV_HinhTK_qltk.setImageBitmap(key.Byte2Bitmap(taikhoan.getHinhTK()));
        } else {
            holder.imgV_HinhTK_qltk.setImageBitmap(key.Drawable2Bitmap(mContext, R.mipmap.hifood));
        }

        holder.cardView_qltk.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, UpTaiKhoan.class);
            intent.putExtra("LoaiCS", key.key_Sua);
            intent.putExtra(key.key_IDTK, taikhoan.getIdTK());
            mContext.startActivity(intent);
        });


    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search_key = charSequence.toString();
                if(search_key.isEmpty()){
                    taikhoanList = OldtaikhoanList;
                } else {
                    ArrayList<taikhoan> list = new ArrayList<>();
                    for(taikhoan taiKhoan:OldtaikhoanList){
                        if(taiKhoan.getSdtTK().toLowerCase().contains(search_key.toLowerCase())){
                            list.add(taiKhoan);
                        }
                    }
                    taikhoanList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = taikhoanList;
                return filterResults;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                taikhoanList = (ArrayList<taikhoan>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        if (taikhoanList != null) {
            return taikhoanList.size();
        }
        return 0;
    }

    public class DsTK_qltk_Viewholder extends RecyclerView.ViewHolder {

        private CardView cardView_qltk;
        private ImageView imgV_HinhTK_qltk;
        private TextView txtV_IDTK_qltk, txtV_TenTK_qltk, txtV_SDT_qltk;

        public DsTK_qltk_Viewholder(@NonNull View itemView) {
            super(itemView);

            cardView_qltk = itemView.findViewById(R.id.cardView_qltk);
            imgV_HinhTK_qltk = itemView.findViewById(R.id.imgV_HinhTK_qltk);
            txtV_IDTK_qltk = itemView.findViewById(R.id.txtV_IDTK_qltk);
            txtV_TenTK_qltk = itemView.findViewById(R.id.txtV_TenTK_qltk);
            txtV_SDT_qltk = itemView.findViewById(R.id.txtV_SDT_qltk);
        }
    }
}
