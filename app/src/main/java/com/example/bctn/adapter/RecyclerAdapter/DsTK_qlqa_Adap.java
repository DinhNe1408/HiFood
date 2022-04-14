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
import com.example.bctn.activity.admin.UpQuanAn;
import com.example.bctn.activity.admin.UpTaiKhoan;
import com.example.bctn.domain.key;
import com.example.bctn.domain.quanan;

import java.util.ArrayList;
import java.util.List;

public class DsTK_qlqa_Adap extends RecyclerView.Adapter<DsTK_qlqa_Adap.DsTK_qlqa_Viewholder> implements Filterable {

    private Context mContext;
    private List<quanan> quananList;
    private final List<quanan> OldquananList;


    public DsTK_qlqa_Adap(Context mContext, List<quanan> quananList) {
        this.mContext = mContext;
        this.quananList = quananList;
        this.OldquananList = quananList;
    }

    @NonNull
    @Override
    public DsTK_qlqa_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_dstk_qltk, parent, false);

        return new DsTK_qlqa_Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DsTK_qlqa_Viewholder holder, int position) {
        quanan quanan = quananList.get(position);

        if (quanan == null)
            return;

        holder.txtV_IDQA_qlqa.setText("Mã số : " + quanan.getIdQA());
        holder.txtV_TenQA_qlqa.setText(quanan.getTenQA());
        holder.txtV_Vitri_qlqa.setText(quanan.getVitriQA().getVitri());

        if (quanan.getHinhQA() != null) {
            holder.imgV_HinhQA_qlqa.setImageBitmap(key.Byte2Bitmap(quanan.getHinhQA()));
        } else {
            holder.imgV_HinhQA_qlqa.setImageBitmap(key.Drawable2Bitmap(mContext, R.mipmap.hifood));
        }

        holder.cardView_qlqa.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, UpQuanAn.class);
            intent.putExtra("LoaiCS", key.key_Sua);
            intent.putExtra(key.key_IDQA, quanan.getIdQA());
            mContext.startActivity(intent);
        });
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search_key = charSequence.toString();
                if (search_key.isEmpty()) {
                    quananList = OldquananList;
                } else {
                    ArrayList<quanan> list = new ArrayList<>();
                    for (quanan quanan : OldquananList) {
                        if (quanan.getTenQA().toLowerCase().contains(search_key.toLowerCase())) {
                            list.add(quanan);
                        }
                    }
                    quananList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = quananList;
                return filterResults;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                quananList = (ArrayList<quanan>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        if (quananList != null) {
            return quananList.size();
        }
        return 0;
    }

    public class DsTK_qlqa_Viewholder extends RecyclerView.ViewHolder {

        private CardView cardView_qlqa;
        private ImageView imgV_HinhQA_qlqa;
        private TextView txtV_IDQA_qlqa, txtV_TenQA_qlqa, txtV_Vitri_qlqa;

        public DsTK_qlqa_Viewholder(@NonNull View itemView) {
            super(itemView);

            cardView_qlqa = itemView.findViewById(R.id.cardView_qltk);
            imgV_HinhQA_qlqa = itemView.findViewById(R.id.imgV_HinhTK_qltk);
            txtV_IDQA_qlqa = itemView.findViewById(R.id.txtV_IDTK_qltk);
            txtV_TenQA_qlqa = itemView.findViewById(R.id.txtV_TenTK_qltk);
            txtV_Vitri_qlqa = itemView.findViewById(R.id.txtV_SDT_qltk);
        }
    }
}
