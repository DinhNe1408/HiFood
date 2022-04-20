package com.example.bctn.adapter.TablayoutAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bctn.fragment.QADonHang.QADHDaHoanThanhFrag;
import com.example.bctn.fragment.QADonHang.QADHDangGiaoFrag;
import com.example.bctn.fragment.YeuThich.YTGanBanFrag;
import com.example.bctn.fragment.YeuThich.YTMoiLuuFrag;

public class TabQADonHangAdap extends FragmentStateAdapter {
    public TabQADonHangAdap(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return new QADHDaHoanThanhFrag();
        }
        return new QADHDangGiaoFrag();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
