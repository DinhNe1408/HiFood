package com.example.bctn.fragment.DonHang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.RecyclerAdapter.DonHang1Adap;
import com.example.bctn.domain.ctdh;
import com.example.bctn.domain.donhang;
import com.example.bctn.domain.donhang_dhfrag;
import com.example.bctn.domain.key;
import com.example.bctn.domain.vitri;

import java.util.ArrayList;
import java.util.List;

public class DHDangDenFrag extends Fragment {

    private View mView;
    private RecyclerView recV_DHDangDen;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_dh_dang_den, container, false);
        AnhXa();

        if (MyAppication.mTaiKhoan.getIdTK() == -1)
            return mView;


        setData();
        return mView;
    }

    private void setData() {
        List<donhang_dhfrag> donhang_dhfrags = MyAppication.mDao.DHFrag(MyAppication.mTaiKhoan.getIdTK(),key.key_dh_DangGiao);
        DonHang1Adap donHang1Adap = new DonHang1Adap(mView.getContext(), donhang_dhfrags);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mView.getContext(),DividerItemDecoration.VERTICAL);

        recV_DHDangDen.addItemDecoration(itemDecoration);
        recV_DHDangDen.setLayoutManager(linearLayoutManager);
        recV_DHDangDen.setAdapter(donHang1Adap);

    }

    private void AnhXa() {
        recV_DHDangDen = mView.findViewById(R.id.recV_DHDangDen);
    }
}
