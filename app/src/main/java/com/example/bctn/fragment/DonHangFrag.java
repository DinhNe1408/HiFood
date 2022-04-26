package com.example.bctn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.TablayoutAdapter.TabDonHangAdap;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class DonHangFrag extends Fragment {

    private View mView;
    private TabLayout tab_DonHang;
    private ViewPager2 viewPage2_DonHang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (MyAppication.mTaiKhoan.getIdTK() == -1) {
            mView = inflater.inflate(R.layout.layout_ban_chua_dang_nhap, container, false);

            return mView;
        }
        mView = inflater.inflate(R.layout.frag_don_hang, container, false);
        AnhXa();

        TabDonHangAdap tabDonHangAdap = new TabDonHangAdap(requireActivity());
        viewPage2_DonHang.setAdapter(tabDonHangAdap);

        new TabLayoutMediator(tab_DonHang, viewPage2_DonHang, (tab, position) -> {
            switch (position) {
                case 1:
                    tab.setText("Lịch sử");
                    break;
                case 2:
                    tab.setText("Đơn nháp");
                    break;
                default:
                    tab.setText("Đang đến");
            }
        }).attach();
        viewPage2_DonHang.setUserInputEnabled(false);
        return mView;
    }

    private void AnhXa() {
        tab_DonHang = mView.findViewById(R.id.tab_DonHang);
        viewPage2_DonHang = mView.findViewById(R.id.viewPage2_DonHang);
    }
}
