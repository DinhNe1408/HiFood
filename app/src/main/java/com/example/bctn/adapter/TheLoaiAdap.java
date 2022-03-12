package com.example.bctn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.R;
import com.example.bctn.domain.theloai;

import java.util.List;

public class TheLoaiAdap extends RecyclerView.Adapter<TheLoaiAdap.TheLoaiViewHolder> {

    private Context mContext;
    private List<theloai> mListTL;

    public TheLoaiAdap(Context mContext, List<theloai> mListTL) {
        this.mContext = mContext;
        this.mListTL = mListTL;
    }

    @NonNull
    @Override
    public TheLoaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_theloai_home, parent, false);
        return new TheLoaiAdap.TheLoaiViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiViewHolder holder, int position) {
        theloai theloai = mListTL.get(position);

        holder.imgV_TheLoai_home.setImageResource(theloai.getHinhTL());
        holder.txtV_TheLoai_home.setText(theloai.getNoidungTL());
    }

    @Override
    public int getItemCount() {
        if( mListTL.size() != 0){
            return mListTL.size();
        }
        return 0;
    }

    public class TheLoaiViewHolder extends RecyclerView.ViewHolder{

        ImageView imgV_TheLoai_home;
        TextView txtV_TheLoai_home;
        public TheLoaiViewHolder(@NonNull View itemView) {
            super(itemView);

            imgV_TheLoai_home = itemView.findViewById(R.id.imgV_TheLoai_home);
            txtV_TheLoai_home = itemView.findViewById(R.id.txtV_TheLoai_home);
        }
    }
}
