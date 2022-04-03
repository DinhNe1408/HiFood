package com.example.bctn.adapter.TablayoutAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bctn.fragment.DonHang.DHDangDenFrag;
import com.example.bctn.fragment.DonHang.DHDonNhapFrag;
import com.example.bctn.fragment.DonHang.DHLichSuFrag;

public class TabDonHangAdap extends FragmentStateAdapter {
    public TabDonHangAdap(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new DHLichSuFrag();
            case 2:
                return new DHDonNhapFrag();
            default:
                return new DHDangDenFrag();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
