package com.example.bctn.fragment.YeuThich;

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
import com.example.bctn.adapter.RecyclerAdapter.QuanAn1Adap;
import com.example.bctn.domain.quanan;
import com.example.bctn.domain.vitri;

import java.util.ArrayList;
import java.util.List;

public class YTMoiLuuFrag extends Fragment {

    private View mView;
    private RecyclerView rec_YT_MoiLuu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_yt_moi_luu,container,false);
        AnhXa();

        List<quanan> mListQA = getListQA();
        if (mListQA!=null)
            return mView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        QuanAn1Adap quanAn1Adap = new QuanAn1Adap(getContext(), mListQA);
        rec_YT_MoiLuu.setLayoutManager(linearLayoutManager);
        rec_YT_MoiLuu.setAdapter(quanAn1Adap);
        return mView;
    }
    private List<quanan> getListQA() {
        List<quanan> mList = new ArrayList<>();
        mList.add(new quanan(0,"Bánh mì chảo Bình Dương",null,new vitri("Bình Dương",10.0,100.0)));
        mList.add(new quanan(0,"Bánh mì chảo Bình Dương",null,new vitri("Bình Dương",10.0,100.0)));
        mList.add(new quanan(0,"Cơm chay Bình Dương",null,new vitri("Bình Dương",10.0,100.0)));
        mList.add(new quanan(0,"Cơm chay Bình Dương",null,new vitri("Bình Dương",10.0,100.0)));

        return mList;
    }
    private void AnhXa() {
        rec_YT_MoiLuu = mView.findViewById(R.id.rec_YT_MoiLuu);
    }
}
