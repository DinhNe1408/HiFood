package com.example.bctn.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.target.ThumbnailImageViewTarget;
import com.example.bctn.R;
import com.example.bctn.activity.admin.UpQuanAn;
import com.example.bctn.domain.key;
import com.example.bctn.domain.quanan;
import com.example.bctn.domain.taikhoan;

import java.util.ArrayList;
import java.util.List;

public class ChonTKAdap extends RecyclerView.Adapter<ChonTKAdap.ChonTK_ViewHolder> implements Filterable {

    private Context mContext;
    private List<taikhoan> taikhoanList, OldtaikhoanList;
    private int IDTK;

    public ChonTKAdap(Context mContext, List<taikhoan> taikhoanList) {
        this.mContext = mContext;
        this.taikhoanList = taikhoanList;
        this.OldtaikhoanList = taikhoanList;
    }

    public int getIDTK() {
        return IDTK;
    }

    public void setIDTK(int IDTK) {
        this.IDTK = IDTK;
    }

    @NonNull
    @Override
    public ChonTK_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_chontk, parent, false);
        return new ChonTK_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChonTK_ViewHolder holder, int position) {
        taikhoan taikhoan = taikhoanList.get(position);
        if (taikhoan == null)
            return;

        holder.txtV_IDTK_ctk.setText("Mã tài khoản : " + taikhoan.getIdTK());
        holder.txtV_SDT_ctk.setText(taikhoan.getSdtTK());
        holder.txtV_TenTK_ctk.setText(taikhoan.getTenTK());

        if (taikhoan.getHinhTK() != null) {
            holder.imgV_HinhTK_ctk.setImageBitmap(key.Byte2Bitmap(taikhoan.getHinhTK()));
        } else {
            holder.imgV_HinhTK_ctk.setImageBitmap(key.Drawable2Bitmap(mContext, R.mipmap.hifood));
        }

        holder.itemView.setOnClickListener(view -> {
            setIDTK(taikhoan.getIdTK());
            UpQuanAn.dismiss();
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

    public class ChonTK_ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgV_HinhTK_ctk;
        private TextView txtV_IDTK_ctk, txtV_TenTK_ctk, txtV_SDT_ctk;

        public ChonTK_ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgV_HinhTK_ctk = itemView.findViewById(R.id.imgV_HinhTK_ctk);
            txtV_IDTK_ctk = itemView.findViewById(R.id.txtV_IDTK_ctk);
            txtV_TenTK_ctk = itemView.findViewById(R.id.txtV_TenTK_ctk);
            txtV_SDT_ctk = itemView.findViewById(R.id.txtV_SDT_ctk);
        }
    }
}
