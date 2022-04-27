package com.example.bctn.fragment.QuanAn;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bctn.R;
import com.example.bctn.activity.QuanAnAct;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class QAThongTinFrag extends Fragment implements OnMapReadyCallback {
    private View mView;
    private SupportMapFragment mapFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_qa_thong_tin, container, false);
        mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.googlemap_qa);
        
        mapFragment.getMapAsync(this);
        return mView;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng latLng = new LatLng(QuanAnAct.quanan.getVitriQA().getVido(), QuanAnAct.quanan.getVitriQA().getKinhdo());
        googleMap.addMarker(new MarkerOptions().position(latLng).title(QuanAnAct.quanan.getTenQA()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
    }
}
