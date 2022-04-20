package com.example.bctn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.DsMA_dh_Adap;
import com.example.bctn.adapter.TablayoutAdapter.TabDonHangAdap;
import com.example.bctn.domain.monan;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class DonHangFrag extends Fragment {

    private View mView;
    private TabLayout tab_DonHang;
    private ViewPager2 viewPage2_DonHang;

    private Button btn_DonHang_dh;
    private List<monan> mListMA;
    private DsMA_dh_Adap dsMA_dh_adap;
    private RecyclerView recV_DsMA_dh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (MyAppication.mTaiKhoan.getIdTK() == -1){
            mView = inflater.inflate(R.layout.layout_ban_chua_dang_nhap,container,false);

            return mView;
        }
        mView = inflater.inflate(R.layout.frag_don_hang,container,false);
        AnhXa();

        TabDonHangAdap tabDonHangAdap = new TabDonHangAdap(requireActivity());
        viewPage2_DonHang.setAdapter(tabDonHangAdap);

        new TabLayoutMediator(tab_DonHang, viewPage2_DonHang, (tab, position) -> {
            switch (position){
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

//        btn_DonHang_dh.setOnClickListener(view -> {
//            Intent mIntent = new Intent(mView.getContext(), ThanhToanAct.class);
//            startActivity(mIntent);
//        });
//
//
//        mListMA = new ArrayList<>();
//        mListMA.add(new monan(R.drawable.w42419,"Banh"));
//        mListMA.add(new monan(R.drawable.w42419,"Banh"));
//        mListMA.add(new monan(R.drawable.w42419,"Banh"));
//        dsMA_dh_adap = new DsMA_dh_Adap(getContext(),mListMA);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
//        recV_DsMA_dh.setLayoutManager(linearLayoutManager);
//        recV_DsMA_dh.setAdapter(dsMA_dh_adap);


        return mView;
    }

    private void AnhXa() {
        tab_DonHang = mView.findViewById(R.id.tab_DonHang);
        viewPage2_DonHang = mView.findViewById(R.id.viewPage2_DonHang);
//        btn_DonHang_dh = mView.findViewById(R.id.btn_DonHang_dh);
//        recV_DsMA_dh = mView.findViewById(R.id.recV_DsMA_dh);
    }
}
