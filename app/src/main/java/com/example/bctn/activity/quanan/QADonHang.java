package com.example.bctn.activity.quanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.RecyclerAdapter.DsDH_qadh_Adap;
import com.example.bctn.adapter.RecyclerAdapter.DsMA_tddh_Adap;
import com.example.bctn.adapter.TablayoutAdapter.TabQADonHangAdap;
import com.example.bctn.adapter.TablayoutAdapter.TabYeuThichAdap;
import com.example.bctn.domain.ctdh;
import com.example.bctn.domain.donhang;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class QADonHang extends AppCompatActivity {

    private Toolbar tool3_QA_DonHang;
    private TabLayout tab_QADonHang;
    private ViewPager2 viewPage2_QADonHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qadon_hang);
        onStart();
        AnhXa();

        tool3_QA_DonHang.setNavigationOnClickListener(view -> onBackPressed());
        TextView txtV_toolbar_title = tool3_QA_DonHang.findViewById(R.id.txtV_toolbar_title);
        txtV_toolbar_title.setText("Danh sách đơn hàng");

        TabQADonHangAdap qaDonHangAdap = new TabQADonHangAdap(this);
        viewPage2_QADonHang.setAdapter(qaDonHangAdap);

        new TabLayoutMediator(tab_QADonHang, viewPage2_QADonHang, (tab, position) -> {
            if (position == 1) {
                tab.setText("Đã hoàn thành");
            } else {
                tab.setText("Đang giao");
            }
        }).attach();
        viewPage2_QADonHang.setUserInputEnabled(false);
    }

    private void AnhXa() {
        tab_QADonHang = findViewById(R.id.tab_QADonHang);
        viewPage2_QADonHang = findViewById(R.id.viewPage2_QADonHang);
        tool3_QA_DonHang = findViewById(R.id.tool3_QA_DonHang);
    }


}