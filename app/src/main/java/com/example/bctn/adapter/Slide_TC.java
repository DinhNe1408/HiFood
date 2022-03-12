package com.example.bctn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bctn.R;
import com.example.bctn.domain.slide_tc;

import java.util.List;

public class Slide_TC extends RecyclerView.Adapter<Slide_TC.Slide_TCViewHolder> {

    private Context mContext;
    private List<slide_tc> mListSlide;

    public Slide_TC(Context mContext, List<slide_tc> mListSlide) {
        this.mContext = mContext;
        this.mListSlide = mListSlide;
    }

    @NonNull
    @Override
    public Slide_TCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate( R.layout.slide_tc, parent, false);
        return new Slide_TCViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull Slide_TCViewHolder holder, int position) {

        slide_tc mSlide = mListSlide.get(position);
        if (mSlide == null){
            return;
        }
        holder.imgVSlide.setClipToOutline(true);
        holder.imgVSlide.setImageResource(mSlide.getResourceId());
    }

    @Override
    public int getItemCount() {
        if(mListSlide != null){
            return mListSlide.size();
        }
        return 0;
    }


    public class Slide_TCViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgVSlide;
        public Slide_TCViewHolder(@NonNull View itemView) {
            super(itemView);

            imgVSlide = itemView.findViewById(R.id.imgVSlide);
        }
    }
}
