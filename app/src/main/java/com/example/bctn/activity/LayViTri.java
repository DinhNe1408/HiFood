package com.example.bctn.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.activity.admin.QuanTri;
import com.example.bctn.domain.key;
import com.example.bctn.domain.taikhoan;
import com.example.bctn.domain.vitri;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LayViTri extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private SearchView searchV_lvt;
    private ImageButton imgB_Mylocation_lvt;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Button btn_lvt;
    vitri newVitri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lay_vi_tri);
        AnhXa();

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googlemap_lvt);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        searchV_lvt.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchV_lvt.getQuery().toString();

                if (location != null || !location.equals("")) {

                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            mMap = googleMap;
                            mMap.clear();
                            List<Address> addressList = null;
                            Geocoder geocoder = new Geocoder(LayViTri.this);
                            try {
                                addressList = geocoder.getFromLocationName(location, 1);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (addressList == null || addressList.size() == 0) {
                                Toast.makeText(LayViTri.this, "Không tìm thấy địa điểm này", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            Address address = addressList.get(0);

                            newVitri = new vitri(address.getAddressLine(0), address.getLatitude(), address.getLongitude());

                            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                            MarkerOptions options = new MarkerOptions().position(latLng);

                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
                            googleMap.addMarker(options);
                        }
                    });
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        imgB_Mylocation_lvt.setOnClickListener(view -> {
            GetLocation();
        });

        mapFragment.getMapAsync(this);

        btn_lvt.setOnClickListener(view -> {
            new AlertDialog.Builder(LayViTri.this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Thông báo")
                    .setMessage("Bạn có muốn lấy địa chỉ : " + newVitri.getVitri() + " không?")
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyAppication.mViTri = newVitri;
                            onBackPressed();
                        }
                    })
                    .setNegativeButton("Không", null)
                    .show();
        });
        if (ActivityCompat.checkSelfPermission(LayViTri.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        mapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(@NonNull GoogleMap googleMap) {
                                mMap = googleMap;
                                mMap.clear();
                                LatLng latLng;
                                if (MyAppication.mViTri != null) {
                                    latLng = new LatLng(MyAppication.mViTri.getVido(), MyAppication.mViTri.getKinhdo());
                                    Log.e("Load", MyAppication.mViTri.getVitri() +
                                            MyAppication.mViTri.getVido() + " = " + MyAppication.mViTri.getKinhdo());
                                } else {
                                    latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                }

                                GetPoint(latLng);

                                MarkerOptions options = new MarkerOptions().position(latLng);
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
                                googleMap.addMarker(options);
                            }
                        });
                    }
                }
            });
        } else {
            ActivityCompat.requestPermissions(LayViTri.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    private void AnhXa() {
        btn_lvt = findViewById(R.id.btn_lvt);
        searchV_lvt = findViewById(R.id.searchV_lvt);
        imgB_Mylocation_lvt = findViewById(R.id.imgB_Mylocation_lvt);
    }

    private void GetLocation() {
        if (ActivityCompat.checkSelfPermission(LayViTri.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        mapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(@NonNull GoogleMap googleMap) {
                                mMap = googleMap;
                                mMap.clear();
                                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                GetPoint(latLng);

                                MarkerOptions options = new MarkerOptions().position(latLng);
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
                                googleMap.addMarker(options);
                            }
                        });
                    }
                }
            });
        } else {
            ActivityCompat.requestPermissions(LayViTri.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    private void GetPoint(LatLng latLng) {
        Geocoder geocoder = new Geocoder(LayViTri.this,
                Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(
                    latLng.latitude, latLng.longitude, 1);

            newVitri = new vitri(addresses.get(0).getAddressLine(0), addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                GetPoint(latLng);

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                mMap.clear();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
                mMap.addMarker(markerOptions);
            }
        });
    }
}