package com.example.bctn.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bctn.DAO;
import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.TablayoutAdapter.TabQuanAnAdap;
import com.example.bctn.domain.key;
import com.example.bctn.domain.quanan;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class QuanAnAct extends AppCompatActivity {

    private TabLayout tab_qa;
    private ViewPager2 viewPage2_qa;
    private TextView txtV_Ten_qa, txtV_ViTri_qa, txtV_Sao_qa, txtV_TGian_qa, tvtV_KhCach_qa;
    private ImageView imgV_Hinh_qa, imgV_Thich_qa;
    public static quanan quanan;
    private int IDQA;
    private boolean isThich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_an);
        AnhXa();

        Intent intent = getIntent();
        IDQA = intent.getIntExtra(key.key_IDQA, -1);
        if (IDQA == -1)
            return;
        quanan = MyAppication.mDao.QA(IDQA);

        TabQuanAnAdap tabQuanAnAdap = new TabQuanAnAdap(this);
        viewPage2_qa.setAdapter(tabQuanAnAdap);
        viewPage2_qa.setUserInputEnabled(false);
        new TabLayoutMediator(tab_qa, viewPage2_qa, (tab, position) -> {
            switch (position) {
                case 1:
                    tab.setText("Đánh giá");
                    break;
                case 2:
                    tab.setText("Thông tin");
                    break;
                default:
                    tab.setText("Thực đơn");
            }
        }).attach();

        if (MyAppication.mTaiKhoan.getIdTK() != -1) {
            isThich = MyAppication.mDao.isThich(MyAppication.mTaiKhoan.getIdTK(), IDQA);
            if (!isThich) {
                imgV_Thich_qa.setImageResource(R.drawable.ic_round_favorite_border_24);
            } else {
                imgV_Thich_qa.setImageResource(R.drawable.ic_round_favorite_24);
            }
        }

//        String url = key.url1 + quanan.getVitriQA().getKinhdo() + "%2C" +
//                quanan.getVitriQA().getVido() + "%3B" +
//                MyAppication.mTaiKhoan.getCurVitri().getKinhdo() + "%2C" +
//                MyAppication.mTaiKhoan.getCurVitri().getVido() + key.url2 + key.token_mapbox;

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
//                    tvtV_KhCach_qa.setText(key.Km2Met(Distance));
//                    txtV_TGian_QA.setText(key.Second2Min(Time));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, error -> Toast.makeText(mContext, "Lỗi", Toast.LENGTH_SHORT).show()
//        );
//        requestQueue.add(jsonObjectRequest);


        imgV_Thich_qa.setOnClickListener(view -> {
            if (MyAppication.mTaiKhoan.getIdTK() != -1) {
                if (isThich) {
                    MyAppication.mDao.XoaYT(MyAppication.mTaiKhoan.getIdTK(), IDQA);
                    imgV_Thich_qa.setImageResource(R.drawable.ic_round_favorite_border_24);
                    Toast.makeText(QuanAnAct.this, "Đã gỡ khỏi yêu thích", Toast.LENGTH_SHORT).show();
                } else {
                    MyAppication.mDao.ThemYT(MyAppication.mTaiKhoan.getIdTK(), IDQA);
                    imgV_Thich_qa.setImageResource(R.drawable.ic_round_favorite_24);
                    Toast.makeText(QuanAnAct.this, "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
                }
                isThich = !isThich;
            } else {
                Toast.makeText(this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
            }
        });
        key.setTextViewDrawableColor(tvtV_KhCach_qa, R.color.location);

        //imgV_Hinh_qa.setImageBitmap(key.Byte2Bitmap(quanan.getHinhQA()));
        txtV_Ten_qa.setText(quanan.getTenQA());
        txtV_ViTri_qa.setText(quanan.getVitriQA().getVitri());
    }



    private void AnhXa() {
        tab_qa = findViewById(R.id.tab_qa);
        viewPage2_qa = findViewById(R.id.viewPage2_qa);

        imgV_Hinh_qa = findViewById(R.id.imgV_Hinh_qa);
        imgV_Thich_qa = findViewById(R.id.imgV_Thich_qa);

        txtV_Ten_qa = findViewById(R.id.txtV_Ten_qa);
        txtV_ViTri_qa = findViewById(R.id.txtV_ViTri_qa);
        txtV_TGian_qa = findViewById(R.id.txtV_TGian_qa);
        txtV_Sao_qa = findViewById(R.id.txtV_Sao_qa);
        tvtV_KhCach_qa = findViewById(R.id.tvtV_KhCach_qa);
    }
}