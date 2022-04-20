package com.example.bctn.activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bctn.R;

public class QLDonHang extends AppCompatActivity {

    private RecyclerView recV_DSDonHang;
    private Toolbar tool3_QLDonHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qldon_hang);
        AnhXa();
    }

    private void AnhXa() {
        recV_DSDonHang = findViewById(R.id.recV_DSDonHang);
        tool3_QLDonHang = findViewById(R.id.tool3_QLDonHang);
    }
}