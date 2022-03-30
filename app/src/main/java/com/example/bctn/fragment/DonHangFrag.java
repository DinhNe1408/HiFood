package com.example.bctn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.R;
import com.example.bctn.activity.ThanhToanAct;
import com.example.bctn.adapter.DsMA_dh_Adap;
import com.example.bctn.domain.monan;

import java.util.ArrayList;
import java.util.List;

public class DonHangFrag extends Fragment {

    private View mView;

    private Button btn_DonHang_dh;

    private List<monan> mListMA;
    private DsMA_dh_Adap dsMA_dh_adap;
    private RecyclerView recV_DsMA_dh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag,container,false);
        AnhXa();

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
//        btn_DonHang_dh = mView.findViewById(R.id.btn_DonHang_dh);
//        recV_DsMA_dh = mView.findViewById(R.id.recV_DsMA_dh);
    }
}
