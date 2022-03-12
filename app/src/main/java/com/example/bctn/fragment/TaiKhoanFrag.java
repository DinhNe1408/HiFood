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
import com.example.bctn.adapter.TaiKhoanAdap;
import com.example.bctn.domain.key;

import java.util.ArrayList;
import java.util.List;

public class TaiKhoanFrag extends Fragment {

    private View mView;
    List<Integer> mListKey;
    RecyclerView recV_MenuTaiKhoan_tk;
    TaiKhoanAdap taiKhoanAdap;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_tai_khoan,container,false);
        AnhXa();

        mListKey = new ArrayList<>();
        mListKey.add(key.key_THONGTINCANHAN);
        mListKey.add(key.key_LICHSUDONHANG);
        mListKey.add(key.key_GOPY);
        mListKey.add(key.key_GIOITHIEU);
        mListKey.add(key.key_CAIDAT);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        taiKhoanAdap = new TaiKhoanAdap(getContext(), mListKey);
        recV_MenuTaiKhoan_tk.setLayoutManager(linearLayoutManager);
        recV_MenuTaiKhoan_tk.setAdapter(taiKhoanAdap);

        return mView;
    }

    private void AnhXa() {
        recV_MenuTaiKhoan_tk = mView.findViewById(R.id.recV_MenuTaiKhoan_tk);
    }
}
