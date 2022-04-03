package com.example.bctn.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.bctn.R;
import com.kofigyan.stateprogressbar.StateProgressBar;

public class TheoDoiDonHang extends AppCompatActivity {
    String[] descriptionData = {"Chuẩn bị", "Tài xế\nnhận hàng", "Đang giao", "Hoàn thành"};

    private Toolbar tool3_TheoDoiDonHang;
    StateProgressBar stateProgressBar;
    TextView txtV_toolbar_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theo_doi_don_hang);
        AnhXa();

        stateProgressBar.setStateDescriptionData(descriptionData);

        tool3_TheoDoiDonHang.setNavigationOnClickListener(view -> onBackPressed());
        txtV_toolbar_title = tool3_TheoDoiDonHang.findViewById(R.id.txtV_toolbar_title);
        txtV_toolbar_title.setText("Theo dõi đơn hàng");

        CountDownTimer count = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
            }
        }.start();
    }

    private void AnhXa() {
        stateProgressBar = (StateProgressBar) findViewById(R.id.progress3_bar);

        tool3_TheoDoiDonHang = findViewById(R.id.tool3_TheoDoiDonHang);
    }
}