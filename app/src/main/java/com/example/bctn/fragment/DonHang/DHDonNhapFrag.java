package com.example.bctn.fragment.DonHang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.RecyclerAdapter.DonHang1Adap;

public class DHDonNhapFrag extends Fragment {

    private View mView;
    private RecyclerView recV_DHDonNhap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_dh_don_nhap, container, false);
        AnhXa();

        getData_Rec();
        return mView;
    }

    private void getData_Rec() {

        DonHang1Adap donHang1Adap = new DonHang1Adap(mView.getContext(), MyAppication.mDao.ListDHDonNhap(MyAppication.mTaiKhoan.getIdTK()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext(), LinearLayoutManager.VERTICAL, false);
        recV_DHDonNhap.setLayoutManager(linearLayoutManager);
        recV_DHDonNhap.setAdapter(donHang1Adap);

    }

    private void AnhXa() {
        recV_DHDonNhap = mView.findViewById(R.id.recV_DHDonNhap);
    }
}
