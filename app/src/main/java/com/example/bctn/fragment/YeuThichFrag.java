package com.example.bctn.fragment;

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
import com.example.bctn.adapter.TablayoutAdapter.TabYeuThichAdap;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class YeuThichFrag extends Fragment {

    private View mView;
    private TabLayout tab_YeuThich;
    private ViewPager2 viewPage2_YeuThich;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (MyAppication.mTaiKhoan.getIdTK() == -1){
            mView = inflater.inflate(R.layout.layout_ban_chua_dang_nhap,container,false);

            return mView;
        }

        mView = inflater.inflate(R.layout.frag_yeu_thich,container,false);
        AnhXa();

        TabYeuThichAdap tabYeuThichAdap = new TabYeuThichAdap(requireActivity());
        viewPage2_YeuThich.setAdapter(tabYeuThichAdap);

        new TabLayoutMediator(tab_YeuThich, viewPage2_YeuThich, (tab, position) -> {
            if (position == 1) {
                tab.setText("Gần bạn");
            } else {
                tab.setText("Mới lưu");
            }
        }).attach();
        viewPage2_YeuThich.setUserInputEnabled(false);
        return mView;
    }

    private void AnhXa() {
        tab_YeuThich = mView.findViewById(R.id.tab_YeuThich);
        viewPage2_YeuThich = mView.findViewById(R.id.viewPage2_YeuThich);
    }
}
