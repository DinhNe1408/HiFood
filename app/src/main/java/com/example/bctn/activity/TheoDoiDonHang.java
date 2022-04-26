package com.example.bctn.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.RecyclerAdapter.DsMA_tddh_Adap;
import com.example.bctn.domain.ctdh;
import com.example.bctn.domain.donhang;
import com.example.bctn.domain.key;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.ArrayList;
import java.util.List;

public class TheoDoiDonHang extends AppCompatActivity {
    String[] descriptionData = {"Chuẩn bị", "Đang giao", "Hoàn thành"};

    private Toolbar tool3_TheoDoiDonHang;
    private StateProgressBar stateProgressBar;
    private TextView txtV_toolbar_title, tongDH_tddh, phiVC_tddh, tongTien_tddh, txtV_TenTK_tddh, txtV_DiaChiNhan_tddh, txtV_ThoiGianNhan_tddh, tienGiam_tddh;
    private CountDownTimer count;
    private donhang donhang;
    private Button btn_VeTrangChu;
    RecyclerView recV_DsMA_tddh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theo_doi_don_hang);
        AnhXa();

        Intent intent = getIntent();
        int IDDH = intent.getIntExtra(key.key_IDDH, -1);
        String TTGiao = intent.getStringExtra(key.key_TTGiao);
        if (IDDH == -1 || TTGiao == null)
            return;

        donhang = MyAppication.mDao.DH(IDDH, TTGiao);
        SuKien();
        setData();
    }

    @SuppressLint("SetTextI18n")
    private void setData() {
        count = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
            }
        };

        switch (donhang.getTTDH()) {
            case key.key_dh_DangGiao:
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                break;
            case key.key_dh_HoanThanh:
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                break;
            default:
                count.start();
        }

        stateProgressBar.setStateDescriptionData(descriptionData);
        txtV_toolbar_title = tool3_TheoDoiDonHang.findViewById(R.id.txtV_toolbar_title);
        txtV_toolbar_title.setText("Theo dõi đơn hàng");

        tongDH_tddh.setText(key.Dou2Money(donhang.getTongTienMA()));
        phiVC_tddh.setText(key.Dou2Money(donhang.getPhiVC()));
        tongTien_tddh.setText(key.Dou2Money(donhang.getTongDH()));
        tienGiam_tddh.setText(key.Dou2Money(donhang.getTienGiam()));

        txtV_TenTK_tddh.setText(donhang.getTenNN() + " - " + donhang.getSDTNN());
        txtV_DiaChiNhan_tddh.setText(donhang.getVitriDH());
        txtV_ThoiGianNhan_tddh.setText("Thời gian giao: " + key.DateTimeFormat(donhang.getTGGiao()));

        List<ctdh> ctdhList = new ArrayList<ctdh>(donhang.getCthdMap().values());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        DsMA_tddh_Adap dsMA_tddh_adap = new DsMA_tddh_Adap(this, ctdhList);

        recV_DsMA_tddh.setLayoutManager(linearLayoutManager);
        recV_DsMA_tddh.setAdapter(dsMA_tddh_adap);
    }

    private void SuKien() {
        tool3_TheoDoiDonHang.setNavigationOnClickListener(view -> onBackPressed());

        btn_VeTrangChu.setOnClickListener(view -> {
            Intent intent = new Intent(TheoDoiDonHang.this, TrangChuAct.class);
            startActivity(intent);
        });
    }

    private void AnhXa() {
        stateProgressBar = (StateProgressBar) findViewById(R.id.progress3_bar);
        recV_DsMA_tddh = findViewById(R.id.recV_DsMA_tddh);
        tool3_TheoDoiDonHang = findViewById(R.id.tool3_TheoDoiDonHang);

        tongDH_tddh = findViewById(R.id.tongDH_tddh);
        phiVC_tddh = findViewById(R.id.phiVC_tddh);
        tongTien_tddh = findViewById(R.id.tongTien_tddh);
        txtV_TenTK_tddh = findViewById(R.id.txtV_TenTK_tddh);
        txtV_DiaChiNhan_tddh = findViewById(R.id.txtV_DiaChiNhan_tddh);
        txtV_ThoiGianNhan_tddh = findViewById(R.id.txtV_ThoiGianNhan_tddh);
        tienGiam_tddh = findViewById(R.id.tienGiam_tddh);

        btn_VeTrangChu = findViewById(R.id.btn_VeTrangChu);
    }
}