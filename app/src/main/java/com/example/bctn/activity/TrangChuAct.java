package com.example.bctn.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bctn.R;
import com.example.bctn.adapter.ViewPager_TC;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TrangChuAct extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private  BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_trang_chu);

        AnhXa();

        ViewPager_TC viewPager_tc = new ViewPager_TC(this);
        viewPager2.setAdapter(viewPager_tc);
        bottomNavigationView.setSelectedItemId(R.id.TrangChu);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.TrangChu:
                        viewPager2.setCurrentItem(0);
                        break;
                    case  R.id.DonHang:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.YeuThich:
                        viewPager2.setCurrentItem(2);
                        break;
                    case R.id.ThongBao:
                        viewPager2.setCurrentItem(3);
                        break;
                    case R.id.ThongTin:
                        viewPager2.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.TrangChu).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.DonHang).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.YeuThich).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.ThongBao).setChecked(true);
                        break;
                    case 4:
                        bottomNavigationView.getMenu().findItem(R.id.ThongTin).setChecked(true);
                        break;

                }
            }
        });
    }

    private void AnhXa() {
        bottomNavigationView = findViewById(R.id.navigation_menu);
        viewPager2 = findViewById(R.id.viewpager_TC);
    }
}