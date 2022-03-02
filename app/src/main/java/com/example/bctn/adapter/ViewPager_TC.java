package com.example.bctn.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bctn.fragment.DonHangFragment;
import com.example.bctn.fragment.HomeFragment;
import com.example.bctn.fragment.TaiKhoanFragment;
import com.example.bctn.fragment.ThongBaoFragment;
import com.example.bctn.fragment.YeuThichFragment;

public class ViewPager_TC extends FragmentStateAdapter {
    public ViewPager_TC(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new DonHangFragment();
            case 2:
                return new YeuThichFragment();
            case 3:
                return new ThongBaoFragment();
            case 4:
                return new TaiKhoanFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
