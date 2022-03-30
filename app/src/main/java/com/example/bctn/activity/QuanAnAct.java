package com.example.bctn.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.TabQuanAnAdap;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class QuanAnAct extends AppCompatActivity {

    private TabLayout tab_qa;
    private ViewPager2 viewPage2_qa;
    private TabQuanAnAdap tabQuanAnAdap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_an);
        AnhXa();

        tabQuanAnAdap = new TabQuanAnAdap(this);

        viewPage2_qa.setAdapter(tabQuanAnAdap);
        new TabLayoutMediator(tab_qa, viewPage2_qa, (tab, position) -> {

            if (position == 1) {
                tab.setText("Thông tin");
            } else {
                tab.setText("Thực đơn");
            }
        }).attach();
    }

    private void AnhXa() {
        tab_qa = findViewById(R.id.tab_qa);
        viewPage2_qa = findViewById(R.id.viewPage2_qa);
    }
}