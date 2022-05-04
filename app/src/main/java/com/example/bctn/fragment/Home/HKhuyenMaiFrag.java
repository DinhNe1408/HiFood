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

public class HKhuyenMaiFrag extends Fragment {

    private View mView;
    private DAO mDao;
    private RecyclerView recV_HKhuyenMai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=  inflater.inflate(R.layout.frag_h_khuyen_mai, container, false);

        AnhXa();
        mDao = new DAO(getContext());
        //getData_RecV();
        return mView;
    }

    private void getData_RecV() {
        List<quanan> mListQA = mDao.ListQAGanBan();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        QuanAn1Adap quanAn1Adap = new QuanAn1Adap(getContext(), mListQA);
        recV_HKhuyenMai.setLayoutManager(linearLayoutManager);
        recV_HKhuyenMai.setAdapter(quanAn1Adap);
    }

    private void AnhXa() {
        recV_HKhuyenMai = mView.findViewById(R.id.recV_HKhuyenMai);
    }
}
