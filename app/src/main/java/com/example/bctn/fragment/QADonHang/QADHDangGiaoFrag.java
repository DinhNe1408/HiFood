package com.example.bctn.fragment.QADonHang;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.RecyclerAdapter.DsDH_qadh_Adap;
import com.example.bctn.domain.donhang;
import com.example.bctn.domain.key;

import java.util.List;

public class QADHDangGiaoFrag extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private View mView;
    private RecyclerView rec_FragQADonHang;
    private SwipeRefreshLayout SwipeRefesh_QADonHang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_qadh, container, false);
        AnhXa();

        SwipeRefesh_QADonHang.setOnRefreshListener(this);
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        setRecV();
    }

    @Override
    public void onRefresh() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            SwipeRefesh_QADonHang.setRefreshing(false);
            setRecV();
        }, 1000);
    }

    private void setRecV() {
        List<donhang> donhangList = MyAppication.mDao.ListQADH(MyAppication.mTaiKhoan.getIDQA(), key.key_dh_DangGiao);
        DsDH_qadh_Adap dsDH_qadh_adap = new DsDH_qadh_Adap(mView.getContext(), donhangList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext(), LinearLayoutManager.VERTICAL, false);

        rec_FragQADonHang.setLayoutManager(linearLayoutManager);
        rec_FragQADonHang.setAdapter(dsDH_qadh_adap);
    }

    private void AnhXa() {
        rec_FragQADonHang = mView.findViewById(R.id.rec_FragQADonHang);
        SwipeRefesh_QADonHang = mView.findViewById(R.id.SwipeRefesh_QADonHang);
    }


}
