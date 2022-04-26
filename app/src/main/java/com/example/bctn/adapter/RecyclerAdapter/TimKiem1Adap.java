package com.example.bctn.adapter.RecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.R;
import com.example.bctn.activity.QuanAnAct;
import com.example.bctn.domain.key;
import com.example.bctn.domain.monan;
import com.example.bctn.domain.quanan;

import java.util.List;

public class TimKiem1Adap extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<quanan> quananList;
    private static final int type_item = 1;
    private static final int type_loading = 2;
    private boolean isLoadingadd;

    public TimKiem1Adap(Context mContext) {
        this.mContext = mContext;
        //this.quananList = quananList;
    }

    public void setData(List<quanan> quananList) {
        this.quananList = quananList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (quananList != null && position == quananList.size() - 1 && isLoadingadd) {
            return type_loading;
        }
        return type_item;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (type_item == viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_quan_an2, parent, false);
            return new TimKiemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_loading, parent, false);
            return new LoadingViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == type_item) {
            quanan quanan = quananList.get(position);

            TimKiemViewHolder timKiemViewHolder = (TimKiemViewHolder) holder;

            timKiemViewHolder.hinhQA_qa2.setImageBitmap(key.Byte2Bitmap(quanan.getHinhQA()));
            timKiemViewHolder.tenQA_qa2.setText(quanan.getTenQA());
            timKiemViewHolder.vitriQA_qa2.setText(quanan.getVitriQA().getVitri());

            timKiemViewHolder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, QuanAnAct.class);
                intent.putExtra(key.key_IDQA, quanan.getIdQA());
                mContext.startActivity(intent);
            });

            List<monan> monanList = quanan.getDsMA();

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            TimKiem2Adap timKiem2Adap = new TimKiem2Adap(mContext, monanList);

            timKiemViewHolder.recV_DSMonAn_qa2.setAdapter(timKiem2Adap);
            timKiemViewHolder.recV_DSMonAn_qa2.setLayoutManager(linearLayoutManager);
        }
    }

    @Override
    public int getItemCount() {
        if (quananList != null) {
            return quananList.size();
        }
        return 0;
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar pro3_Loading;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            pro3_Loading = itemView.findViewById(R.id.pro3_Loading);
        }
    }

    public class TimKiemViewHolder extends RecyclerView.ViewHolder {

        private ImageView hinhQA_qa2;
        private TextView tenQA_qa2, vitriQA_qa2;
        private RecyclerView recV_DSMonAn_qa2;

        public TimKiemViewHolder(@NonNull View itemView) {
            super(itemView);

            hinhQA_qa2 = itemView.findViewById(R.id.hinhQA_qa2);
            tenQA_qa2 = itemView.findViewById(R.id.tenQA_qa2);
            vitriQA_qa2 = itemView.findViewById(R.id.vitriQA_qa2);
            recV_DSMonAn_qa2 = itemView.findViewById(R.id.recV_DSMonAn_qa2);
        }
    }

    public void addLoading() {
        isLoadingadd = true;
        quananList.add(new quanan());
    }

    public void removeLoading() {
        isLoadingadd = false;
        int pos = quananList.size() - 1;
        quanan quanan = quananList.get(pos);
        if (quanan != null) {
            quananList.remove(pos);
            notifyItemRemoved(pos);
        }
    }
}
