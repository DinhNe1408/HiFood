package com.example.bctn.fragment.Home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.bctn.DAO;
import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.RecyclerAdapter.QuanAn1Adap;
import com.example.bctn.domain.quanan;

import java.util.ArrayList;
import java.util.List;

public class HDanhChoBanFrag extends Fragment {

    private View mView;
    private DAO mDao;
    private RecyclerView recV_HPhoBien;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_h_danh_cho_ban, container, false);
        AnhXa();
        mDao = new DAO(getContext());
        if (MyAppication.mTaiKhoan.getIdTK() == - 1){

        } else {
            List<quanan> quananList = new ArrayList<>();

            if (!Python.isStarted()) {
                Python.start(new AndroidPlatform(getContext()));
            }
            Python py = Python.getInstance();
            final PyObject pyobj = py.getModule("BCTN_HTGY");
            PyObject obj = pyobj.callAttr("BCTN_HTGY", MyAppication.mTaiKhoan.getIdTK());
            List<PyObject> list = obj.asList();
            for (int i = 0; i < list.size(); i++) {
                quananList.add(MyAppication.mDao.DanhChoBanQA(String.valueOf(list.get(i))));
            }
            getData_RecV(quananList);
        }

        //getData_RecV();
        return mView;
    }

    private void getData_RecV(List<quanan> quananList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        QuanAn1Adap quanAn1Adap = new QuanAn1Adap(getContext(), quananList);
        recV_HPhoBien.setLayoutManager(linearLayoutManager);
        recV_HPhoBien.setAdapter(quanAn1Adap);
    }

    private void AnhXa() {
        recV_HPhoBien = mView.findViewById(R.id.recV_HPhoBien);
    }
}
