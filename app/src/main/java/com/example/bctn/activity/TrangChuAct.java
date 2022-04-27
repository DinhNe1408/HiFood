package com.example.bctn.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.check_internet.NetworkChangeListener;
import com.example.bctn.domain.vitri;
import com.example.bctn.fragment.DonHangFrag;
import com.example.bctn.fragment.HomeFrag;
import com.example.bctn.fragment.TaiKhoanFrag;
import com.example.bctn.fragment.ThongBaoFrag;
import com.example.bctn.fragment.YeuThichFrag;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.security.auth.login.LoginException;

public class TrangChuAct extends AppCompatActivity {

    //private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;
    private long backPressedTime;
    private Toast mToast;

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        AnhXa();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_contrainer, new HomeFrag()).commit();
        //key.sendNotification(this,"Khuyến mãi cực sốc","Bữa trưa tràn đầy năng lượng");


        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selFragment;
                switch (item.getItemId()) {
                    case R.id.DonHang:
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
                    default:
                        selFragment = new HomeFrag();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_contrainer, selFragment).commit();
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
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

    @Override
    protected void onStart() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        //registerReceiver(networkChangeListener,intentFilter);

        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //unregisterReceiver(networkChangeListener);
    }
}