package com.example.bctn.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.bctn.R;
import com.example.bctn.domain.donhang;
import com.example.bctn.domain.key;
import com.kofigyan.stateprogressbar.StateProgressBar;

public class TheoDoiDonHang extends AppCompatActivity {
    String[] descriptionData = {"Chuẩn bị", "Tài xế\nnhận hàng", "Đang giao", "Hoàn thành"};

    private Toolbar tool3_TheoDoiDonHang;
    private StateProgressBar stateProgressBar;
    private TextView txtV_toolbar_title;
    private CountDownTimer count;
    private donhang donhang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theo_doi_don_hang);
        AnhXa();
        KhoiTao();
        SuKien();

        Intent intent = getIntent();
        donhang = (donhang) intent.getSerializableExtra(key.key_DonHang_);
        if (donhang == null)
            return;

        switch (donhang.getTTDH()){
            case key.key_dh_DangGiao:
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                break;
            case key.key_dh_HoanThanh:
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
                break;
            default:
                count.start();
        }

    }

    private void SuKien() {
        tool3_TheoDoiDonHang.setNavigationOnClickListener(view -> onBackPressed());

    }

    private void KhoiTao() {
        count = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
            }
        };

        stateProgressBar.setStateDescriptionData(descriptionData);
        txtV_toolbar_title = tool3_TheoDoiDonHang.findViewById(R.id.txtV_toolbar_title);
        txtV_toolbar_title.setText("Theo dõi đơn hàng");
    }

    private void AnhXa() {
        stateProgressBar = (StateProgressBar) findViewById(R.id.progress3_bar);

        tool3_TheoDoiDonHang = findViewById(R.id.tool3_TheoDoiDonHang);
    }
}