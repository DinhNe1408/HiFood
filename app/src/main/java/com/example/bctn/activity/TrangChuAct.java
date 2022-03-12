package com.example.bctn.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bctn.R;
import com.example.bctn.fragment.DonHangFrag;
import com.example.bctn.fragment.HomeFrag;
import com.example.bctn.fragment.TaiKhoanFrag;
import com.example.bctn.fragment.ThongBaoFrag;
import com.example.bctn.fragment.YeuThichFrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TrangChuAct extends AppCompatActivity {

    //private ViewPager2 viewPager2;
    private  BottomNavigationView bottomNavigationView;
    private  long  backPressedTime;
    private Toast mToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_trang_chu);

        AnhXa();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_contrainer,new HomeFrag()).commit();

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selFragment = null;
                switch (item.getItemId()){
                    case R.id.TrangChu:
                        selFragment = new HomeFrag();
                        break;
                    case  R.id.DonHang:
                        selFragment = new DonHangFrag();
                        break;
                    case R.id.YeuThich:
                        selFragment = new YeuThichFrag();
                        break;
                    case R.id.ThongBao:
                        selFragment = new ThongBaoFrag();
                        break;
                    case R.id.ThongTin:
                        selFragment = new TaiKhoanFrag();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_contrainer,selFragment).commit();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            mToast.cancel();
            super.onBackPressed();
            return;
        } else {
            mToast = Toast.makeText(this, "Nhấn back thêm 1 lần nữa để thoát", Toast.LENGTH_SHORT);
            mToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    private void AnhXa() {
        bottomNavigationView = findViewById(R.id.navigation_menu);
    }
}