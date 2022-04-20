package com.example.bctn.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.ThongBaoAdap;
import com.example.bctn.domain.thongbao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ThongBaoFrag extends Fragment {

    private View mView;
    private RecyclerView recV_ThongBao;
    private List<thongbao> mListTB;
    private ThongBaoAdap thongBaoAdap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (MyAppication.mTaiKhoan.getIdTK() == -1){
            mView = inflater.inflate(R.layout.layout_ban_chua_dang_nhap,container,false);

            return mView;
        }

        mView = inflater.inflate(R.layout.frag_thong_bao,container,false);

        AnhXa();

        mListTB = new ArrayList<>();

        if(Build.VERSION.SDK_INT > 26){
            ;
            mListTB.add(new thongbao(R.drawable.bell,"Thông báo 1", LocalDateTime.parse("2015-02-20T06:30:00")));
            mListTB.add(new thongbao(R.drawable.bell,"Thông báo 2", LocalDateTime.parse("2022-03-04T06:30:00")));
            mListTB.add(new thongbao(R.drawable.bell,"Thông báo 3", LocalDateTime.parse("2022-03-06T06:30:00")));
            mListTB.add(new thongbao(R.drawable.bell,"Thông báo 4", LocalDateTime.parse("2022-03-06T13:30:00")));

            mListTB.add(new thongbao(R.drawable.sale,"Khuyến mãi 1", LocalDateTime.now()));
            mListTB.add(new thongbao(R.drawable.sale,"Khuyến mãi 2", LocalDateTime.now()));
            mListTB.add(new thongbao(R.drawable.sale,"Khuyến mãi 3", LocalDateTime.now()));
            mListTB.add(new thongbao(R.drawable.sale,"Khuyến mãi 4", LocalDateTime.now()));
        }

        thongBaoAdap = new ThongBaoAdap(getContext(),mListTB);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL, false);

        recV_ThongBao.setLayoutManager(linearLayoutManager);
        recV_ThongBao.setAdapter(thongBaoAdap);
        return mView;
    }

    private void AnhXa() {
        recV_ThongBao = mView.findViewById(R.id.recV_ThongBao);

    }
}
