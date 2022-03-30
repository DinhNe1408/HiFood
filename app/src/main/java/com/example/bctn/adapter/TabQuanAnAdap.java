package com.example.bctn.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bctn.fragment.ThongTinQAFrag;
import com.example.bctn.fragment.ThucDonQAFrag;

public class TabQuanAnAdap extends FragmentStateAdapter {

    public TabQuanAnAdap(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return new ThongTinQAFrag();
        }
        return new ThucDonQAFrag();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
