package com.example.bctn.adapter.TablayoutAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bctn.fragment.QuanAn.QADanhGiaFrag;
import com.example.bctn.fragment.QuanAn.QAThongTinFrag;
import com.example.bctn.fragment.QuanAn.QAThucDonFrag;

public class TabQuanAnAdap extends FragmentStateAdapter {

    public TabQuanAnAdap(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new QADanhGiaFrag();
            case 2:
                return new QAThongTinFrag();
            default:
                return new QAThucDonFrag();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
