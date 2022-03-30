package com.example.bctn.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bctn.fragment.GanBanYTFrag;
import com.example.bctn.fragment.MoiLuuYTFrag;

public class TabYeuThichAdap extends FragmentStateAdapter {
    public TabYeuThichAdap(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return new GanBanYTFrag();
        }
        return new MoiLuuYTFrag();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
