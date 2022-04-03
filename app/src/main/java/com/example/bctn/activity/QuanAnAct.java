package com.example.bctn.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.FragmentTransitionSupport;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bctn.DAO;
import com.example.bctn.R;
import com.example.bctn.adapter.TablayoutAdapter.TabQuanAnAdap;
import com.example.bctn.domain.quanan;
import com.example.bctn.fragment.QuanAn.QAThucDonFrag;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class QuanAnAct extends AppCompatActivity {

    private TabLayout tab_qa;
    private ViewPager2 viewPage2_qa;
    private TextView txtV_Ten_qa,txtV_ViTri_qa,txtV_Sao_QA,txtV_TGian_QA;
    private ImageView imgV_Hinh_qa, imgV_Thich_qa;
    public static quanan quanan;
    private int IDQA;
    private DAO mDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_an);
        AnhXa();
        mDao = new DAO(this);

        Intent intent = getIntent();
        IDQA = intent.getIntExtra("IDQA",-1);
        if (IDQA == -1)
            return;


        TabQuanAnAdap tabQuanAnAdap = new TabQuanAnAdap(this);
        viewPage2_qa.setAdapter(tabQuanAnAdap);
        new TabLayoutMediator(tab_qa, viewPage2_qa, (tab, position) -> {

            if (position == 1) {
                tab.setText("Thông tin");
            } else {
                tab.setText("Thực đơn");
            }
        }).attach();

        quanan = mDao.QA(IDQA);

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
        txtV_TGian_QA = findViewById(R.id.txtV_TGian_qa);
        txtV_Sao_QA = findViewById(R.id.txtV_Sao_qa);
    }
}