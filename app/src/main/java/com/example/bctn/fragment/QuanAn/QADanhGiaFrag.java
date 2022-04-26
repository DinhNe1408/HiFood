package com.example.bctn.fragment.QuanAn;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.activity.QuanAnAct;
import com.example.bctn.adapter.RecyclerAdapter.DanhGiaAdap;
import com.example.bctn.domain.danhgia;

import java.util.ArrayList;
import java.util.List;

public class QADanhGiaFrag extends Fragment {
    private View mView;
    private RecyclerView recV_DSDanhGia;
    List<danhgia> danhgiaList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        danhgiaList = MyAppication.mDao.ListDG(QuanAnAct.quanan.getIdQA());

        if (danhgiaList.size() == 0) {
            mView = inflater.inflate(R.layout.layout1, container, false);
            TextView txtV_NoiDung = mView.findViewById(R.id.txtV_NoiDung);
            txtV_NoiDung.setText("Quán ăn chưa có đánh giá");
            return mView;
        }
        mView = inflater.inflate(R.layout.frag_qa_danh_gia, container, false);
        AnhXa();
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        danhgiaList = MyAppication.mDao.ListDG(QuanAnAct.quanan.getIdQA());

        if (danhgiaList.size() != 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            DanhGiaAdap danhGiaAdap = new DanhGiaAdap(getContext(), danhgiaList);
            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mView.getContext(), DividerItemDecoration.VERTICAL);

            recV_DSDanhGia.addItemDecoration(itemDecoration);
            recV_DSDanhGia.setLayoutManager(linearLayoutManager);
            recV_DSDanhGia.setAdapter(danhGiaAdap);
        }

    }

    private void AnhXa() {
        recV_DSDanhGia = mView.findViewById(R.id.recV_DSDanhGia);
    }
}
