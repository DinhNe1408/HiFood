package com.example.bctn.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.R;
import com.example.bctn.adapter.MonAn1Adap;
import com.example.bctn.domain.monan;
import com.example.bctn.domain.slide_tc;

import java.util.ArrayList;
import java.util.List;

public class GanBanFrag extends Fragment {

    private View mView;
    private MonAn1Adap monAn1Adap;
    private RecyclerView recV_GanBan;
    private List<monan> mListMA;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_gan_ban, container, false);

        AnhXa();
        mListMA = getListMA();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        monAn1Adap =  new MonAn1Adap(getContext(),mListMA);
        recV_GanBan.setLayoutManager(linearLayoutManager);
        recV_GanBan.setAdapter(monAn1Adap);
        return mView;
    }

    private List<monan> getListMA() {
        List<monan> mList = new ArrayList<>();
        mList.add(new monan(R.drawable.t1,"Banhs"));
        mList.add(new monan(R.drawable.t1,"Banhs"));
        mList.add(new monan(R.drawable.t1,"Banhs"));
        mList.add(new monan(R.drawable.t1,"Banhs"));

        mList.add(new monan(R.drawable.t1,"Keos"));
        mList.add(new monan(R.drawable.t1,"Keos"));
        mList.add(new monan(R.drawable.t1,"Keos"));
        mList.add(new monan(R.drawable.t1,"Keos"));

        mList.add(new monan(R.drawable.t1,"Banhs"));
        mList.add(new monan(R.drawable.t1,"Banhs"));
        mList.add(new monan(R.drawable.t1,"Banhs"));
        mList.add(new monan(R.drawable.t1,"Banhs"));
        return mList;
    }

    private void AnhXa() {
        recV_GanBan = mView.findViewById(R.id.recV_GanBan);
    }
}
