package com.example.bctn.adapter.TablayoutAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bctn.fragment.Home.HBanChayFrag;
import com.example.bctn.fragment.Home.HGanBanFrag;
import com.example.bctn.fragment.Home.HKhuyenMaiFrag;
import com.example.bctn.fragment.Home.HPhoBienFrag;

public class TabHomeAdap extends FragmentStateAdapter {
    public TabHomeAdap(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new HBanChayFrag();
            case 2:
                return new HPhoBienFrag();
            case 3:
                return new HKhuyenMaiFrag();
            default:
                return new HGanBanFrag();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
