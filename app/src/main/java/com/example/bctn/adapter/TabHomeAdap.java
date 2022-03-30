package com.example.bctn.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bctn.fragment.BanChayFrag;
import com.example.bctn.fragment.GanBanFrag;
import com.example.bctn.fragment.KhuyenMaiFrag;
import com.example.bctn.fragment.PhoBienFrag;

public class TabHomeAdap extends FragmentStateAdapter {
    public TabHomeAdap(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new BanChayFrag();
            case 2:
                return new PhoBienFrag();
            case 3:
                return new KhuyenMaiFrag();
            default:
                return new GanBanFrag();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
