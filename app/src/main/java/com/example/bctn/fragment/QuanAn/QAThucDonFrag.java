package com.example.bctn.fragment.QuanAn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.R;
import com.example.bctn.activity.QuanAnAct;
import com.example.bctn.adapter.RecyclerAdapter.MonAn1Adap;
import com.example.bctn.domain.quanan;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class QAThucDonFrag extends Fragment {

    private View mView;
    private RecyclerView recV_ThucDon_QA;
    private quanan quanan;
    private RelativeLayout relative1_QA;
    private TextView txtV_TSoL_gh;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_qa_thuc_don, container, false);
        AnhXa();

        quanan = QuanAnAct.quanan;

        getData_RecV();

        relative1_QA.setOnClickListener(view -> {
            OpenBotSheet();
        });

        //OpenBotSheet();
        return mView;
    }

    private void getData_RecV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        MonAn1Adap monAn1Adap = new MonAn1Adap(getContext(), quanan.getDsMA(), quanan.getIdQA());
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mView.getContext(),DividerItemDecoration.VERTICAL);

        recV_ThucDon_QA.setLayoutManager(linearLayoutManager);
        recV_ThucDon_QA.setAdapter(monAn1Adap);
        recV_ThucDon_QA.addItemDecoration(itemDecoration);
    }

    private void OpenBotSheet() {
        View view = getLayoutInflater().inflate(R.layout.bottomsheet_gio_hang, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mView.getContext());
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
        ImageButton imgB_Dong = view.findViewById(R.id.imgB_Dong);

        //bottomSheetDialog.set
        imgB_Dong.setOnClickListener(view1 -> {
            bottomSheetDialog.dismiss();
        });

    }



    private void AnhXa() {
        recV_ThucDon_QA = mView.findViewById(R.id.recV_ThucDon_QA);
        relative1_QA = mView.findViewById(R.id.relative1_QA);
        txtV_TSoL_gh = mView.findViewById(R.id.txtV_TSoL_gh);
    }

}
