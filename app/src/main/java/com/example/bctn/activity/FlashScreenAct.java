package com.example.bctn.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.domain.vitri;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class FlashScreenAct extends AppCompatActivity {

    LottieAnimationView lottie_layer_name;
    TextView app_name;
    Animation bottom_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);

        lottie_layer_name = findViewById(R.id.lottie_layer_name);
        app_name = findViewById(R.id.app_name);

        bottom_up = AnimationUtils.loadAnimation(this, R.anim.bottom_up);
        app_name.setAnimation(bottom_up);

        FusedLocationProviderClient fusedLocationProviderClient;
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        MyAppication.mTaiKhoan.setCurVitri(new vitri("", 0, 0));
        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        try {
                            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                            List<Address> addresses = geocoder.getFromLocation(
                                    location.getLatitude(), location.getLongitude(), 1);
                            vitri vitri = new vitri(addresses.get(0).getAddressLine(0), addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
                            MyAppication.curViTri = vitri;
                            MyAppication.mTaiKhoan.setCurVitri(vitri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), TrangChuAct.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }
}