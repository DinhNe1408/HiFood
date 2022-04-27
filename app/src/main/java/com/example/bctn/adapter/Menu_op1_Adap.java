package com.example.bctn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.R;
import com.example.bctn.domain.key;
import com.example.bctn.domain.menu_option;
import java.util.List;

public class Menu_op1_Adap extends RecyclerView.Adapter<Menu_op1_Adap.Menu_op1_AdapViewHolder> {

    private Context mContext;
    private List<menu_option> mListOp;

    public Menu_op1_Adap(Context mContext, List<menu_option> mListOp) {
        this.mContext = mContext;
        this.mListOp = mListOp;
    }

    @NonNull
    @Override
    public Menu_op1_AdapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_menu_option1, parent, false);
        return new Menu_op1_AdapViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull Menu_op1_AdapViewHolder holder, int position) {
        menu_option mKey = mListOp.get(position);

        holder.txtV_NoiDungMenu_op1.setText(mKey.getNoidungMenu());
        holder.imgV_HinhMenu_op1.setImageResource(mKey.getHinhMenu());

        holder.relativeMenu_op1.setOnClickListener(view -> {
            switch (mKey.getIdMenu()) {
                case key.key_KHUYENMAI:
                    Toast.makeText(mContext, "Khuyến mãi", Toast.LENGTH_SHORT).show();
                case key.key_PHUONGTHUCTHANHTOAN:
                    Toast.makeText(mContext, "Thanh toán", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mListOp != null){
            return mListOp.size();
        }
        return 0;
    }

    public class Menu_op1_AdapViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout relativeMenu_op1;
        ImageView imgV_HinhMenu_op1;
        TextView txtV_NoiDungMenu_op1;

        public Menu_op1_AdapViewHolder(@NonNull View itemView) {
            super(itemView);

            relativeMenu_op1 = itemView.findViewById(R.id.relativeMenu_op1);
            imgV_HinhMenu_op1 = itemView.findViewById(R.id.imgV_HinhMenu_op1);
            txtV_NoiDungMenu_op1 = itemView.findViewById(R.id.txtV_NoiDungMenu_op1);
        }
    }
}
