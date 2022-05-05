package com.example.bctn.adapter.RecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.activity.QuanAnAct;
import com.example.bctn.domain.key;
import com.example.bctn.domain.quanan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class QuanAn1Adap extends RecyclerView.Adapter<QuanAn1Adap.QuanAn1AdapViewHolder> {
    private final Context mContext;
    private final List<quanan> mList;

    public QuanAn1Adap(Context mContext, List<quanan> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public QuanAn1AdapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_quan_an1, parent, false);
        return new QuanAn1AdapViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuanAn1AdapViewHolder holder, int position) {
        quanan quanan = mList.get(position);

        holder.hinhQA_1.setImageBitmap(key.Byte2Bitmap(quanan.getHinhQA()));
        holder.tenQA_1.setText(quanan.getTenQA());
        holder.vitriQA_1.setText(quanan.getVitriQA().getVitri());
        holder.saoQA_1.setText(String.format("%.01f",quanan.getSaoQA()));

        holder.relative_ma.setOnClickListener(view -> {
            Intent mIntent = new Intent(mContext, QuanAnAct.class);
            Log.e("IDQA", quanan.getIdQA() + "");
            mIntent.putExtra(key.key_IDQA, quanan.getIdQA());
            mContext.startActivity(mIntent);
        });

        key.setTextViewDrawableColor(holder.khcachQA_qa1, R.color.location);
//        String url = key.url1 + quanan.getVitriQA().getKinhdo() + "%2C" +
//                quanan.getVitriQA().getVido() + "%3B" +
//                MyAppication.mTaiKhoan.getCurVitri().getKinhdo() + "%2C" +
//                MyAppication.mTaiKhoan.getCurVitri().getVido() + key.url2 + key.token_mapbox;
//
//        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONArray jsonItems = response.getJSONArray("routes");
//                    double Time = 0;
//                    double Distance = 0;
//
//                    // 0  là thời gian ngắn nhất  1 là quảng đường ngắn nhất
//                    JSONObject jsonItem = jsonItems.getJSONObject(1);
//                    Time = jsonItem.getDouble("duration");
//                    Distance = jsonItem.getDouble("distance");
//                    //Toast.makeText(mContext, String.valueOf(Time), Toast.LENGTH_SHORT).show();
//                    holder.khcachQA_qa1.setText(key.Km2Met(Distance));
//                    holder.tgianQA_qa1.setText(key.Second2Min(Time));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, error -> Toast.makeText(mContext, "Lỗi", Toast.LENGTH_SHORT).show()
//        );
//        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public class QuanAn1AdapViewHolder extends RecyclerView.ViewHolder {

        TextView tenQA_1, saoQA_1, vitriQA_1, tgianQA_qa1, khcachQA_qa1;
        ImageView hinhQA_1;
        RelativeLayout relative_ma;

        public QuanAn1AdapViewHolder(@NonNull View itemView) {
            super(itemView);

            tenQA_1 = itemView.findViewById(R.id.tenQA_1);
            saoQA_1 = itemView.findViewById(R.id.saoQA_1);
            vitriQA_1 = itemView.findViewById(R.id.vitriQA_1);
            tgianQA_qa1 = itemView.findViewById(R.id.tgianQA_qa1);
            khcachQA_qa1 = itemView.findViewById(R.id.khcachQA_qa1);

            hinhQA_1 = itemView.findViewById(R.id.hinhQA_1);
            relative_ma = itemView.findViewById(R.id.relative_ma);
        }
    }
}
