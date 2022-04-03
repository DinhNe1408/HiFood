package com.example.bctn.fragment.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.DAO;
import com.example.bctn.R;
import com.example.bctn.adapter.RecyclerAdapter.QuanAn1Adap;
import com.example.bctn.domain.quanan;

import java.util.List;

public class HBanChayFrag extends Fragment {

    private View mView;
    private DAO mDao;
    private RecyclerView recV_HBanChay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_h_ban_chay, container, false);
        AnhXa();
        mDao = new DAO(getContext());

        getData_RecV();
        return mView;
    }

    private void getData_RecV() {
        List<quanan> mListQA = mDao.ListQAGanBan();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        QuanAn1Adap quanAn1Adap = new QuanAn1Adap(getContext(), mListQA);
        recV_HBanChay.setLayoutManager(linearLayoutManager);
        recV_HBanChay.setAdapter(quanAn1Adap);
    }

    private void AnhXa() {
        recV_HBanChay = mView.findViewById(R.id.recV_HBanChay);
    }

}
