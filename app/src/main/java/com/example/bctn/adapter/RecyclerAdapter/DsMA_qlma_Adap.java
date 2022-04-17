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
import com.example.bctn.activity.admin.UpMonAn;
import com.example.bctn.domain.key;
import com.example.bctn.domain.monan;

import java.util.ArrayList;
import java.util.List;

public class DsMA_qlma_Adap extends RecyclerView.Adapter<DsMA_qlma_Adap.DsMA_qlma_ViewHolder> implements Filterable {

    private Context mContext;
    private List<monan> monanList, OldmonanList;
    private int IDQA;

    public DsMA_qlma_Adap(Context mContext, List<monan> monanList, int IDQA) {
        this.mContext = mContext;
        this.monanList = monanList;
        this.OldmonanList = monanList;
        this.IDQA = IDQA;
    }

    @NonNull
    @Override
    public DsMA_qlma_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_dsma_qlma_adap, parent, false);
        return new DsMA_qlma_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DsMA_qlma_ViewHolder holder, int position) {
        monan monan = monanList.get(position);

        holder.imgV_HinhMA_ma2.setImageBitmap(key.Byte2Bitmap(monan.getHinhMA()));
        holder.txtV_TenMA_ma2.setText(monan.getTenMA());
        holder.txtV_GiaMa_ma2.setText(key.Dou2Money(monan.getGiaMA()));

        holder.cardView_qlma.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, UpMonAn.class);

            intent.putExtra(key.key_LoaiCS, key.key_Sua);
            intent.putExtra(key.key_IDQA, IDQA);
            intent.putExtra(key.key_IDMA,monan.getIdMA());
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
                    monanList = OldmonanList;
                } else {
                    ArrayList<monan> list = new ArrayList<>();
                    for (monan monan : OldmonanList) {
                        if (monan.getTenMA().toLowerCase().contains(search_key.toLowerCase())) {
                            list.add(monan);
                        }
                    }
                    monanList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = monanList;
                return filterResults;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                monanList = (ArrayList<monan>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        if (monanList != null) {
            return monanList.size();
        }
        return 0;
    }

    public class DsMA_qlma_ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView_qlma;
        private ImageView imgV_HinhMA_ma2;
        private TextView txtV_TenMA_ma2, txtV_GiaMa_ma2;

        public DsMA_qlma_ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgV_HinhMA_ma2 = itemView.findViewById(R.id.imgV_HinhMA_ma2);
            txtV_TenMA_ma2 = itemView.findViewById(R.id.txtV_TenMA_ma2);
            txtV_GiaMa_ma2 = itemView.findViewById(R.id.txtV_GiaMa_ma2);

            cardView_qlma = itemView.findViewById(R.id.cardView_qlma);
        }
    }
}
