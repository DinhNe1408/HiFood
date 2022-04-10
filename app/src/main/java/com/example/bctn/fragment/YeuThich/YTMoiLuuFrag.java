package com.example.bctn.fragment.YeuThich;

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
import com.example.bctn.adapter.RecyclerAdapter.QuanAn1Adap;

public class YTMoiLuuFrag extends Fragment {

    private View mView;
    private RecyclerView rec_YT_MoiLuu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_yt_moi_luu, container, false);
        AnhXa();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        QuanAn1Adap quanAn1Adap = new QuanAn1Adap(getContext(), MyAppication.mDao.ListQAYT(MyAppication.mTaiKhoan.getIdTK()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mView.getContext(),DividerItemDecoration.VERTICAL);

        rec_YT_MoiLuu.addItemDecoration(itemDecoration);
        rec_YT_MoiLuu.setLayoutManager(linearLayoutManager);
        rec_YT_MoiLuu.setAdapter(quanAn1Adap);

        return mView;
    }

    private void AnhXa() {
        rec_YT_MoiLuu = mView.findViewById(R.id.rec_YT_MoiLuu);
    }
}
