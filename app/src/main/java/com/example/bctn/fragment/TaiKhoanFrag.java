package com.example.bctn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.activity.DangNhapAct;
import com.example.bctn.adapter.Menu_op2_Adap;
import com.example.bctn.domain.key;
import com.example.bctn.domain.menu_option;

import java.util.ArrayList;
import java.util.List;

public class TaiKhoanFrag extends Fragment {

    private View mView;
    private List<menu_option> mListKey;
    private RecyclerView recV_MenuTaiKhoan_tk;
    private Menu_op2_Adap menuop2Adap;
    private TextView txtV_TenTK_tk;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_tai_khoan,container,false);
        AnhXa();

        mListKey = new ArrayList<>();
        mListKey.add(new menu_option(key.key_THONGTINCANHAN,0,0,"Thông tin cá nhân"));
        mListKey.add(new menu_option(key.key_GOPY,0,0,"Góp ý"));
        mListKey.add(new menu_option(key.key_GIOITHIEU,0,0,"Giới thiệu"));
        mListKey.add(new menu_option(key.key_CAIDAT,0,0,"Cài đặt"));
        if (MyAppication.mTaiKhoan.getIdTK() != -1){
            mListKey.add(new menu_option(key.key_DANGXUAT,0,0,"Đăng xuất"));
        } else {
            txtV_TenTK_tk.setText(getResources().getText(R.string.dndk));
        }

        txtV_TenTK_tk.setOnClickListener(view -> {
            if (MyAppication.mTaiKhoan.getIdTK() == -1){
                Intent intent = new Intent(getContext(), DangNhapAct.class);
                requireContext().startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        menuop2Adap = new Menu_op2_Adap(getContext(), mListKey);
        recV_MenuTaiKhoan_tk.setLayoutManager(linearLayoutManager);
        recV_MenuTaiKhoan_tk.setAdapter(menuop2Adap);

        return mView;
    }

    private void AnhXa() {
        recV_MenuTaiKhoan_tk = mView.findViewById(R.id.recV_MenuTaiKhoan_tk);
        txtV_TenTK_tk = mView.findViewById(R.id.txtV_TenTK_tk);
    }
}
