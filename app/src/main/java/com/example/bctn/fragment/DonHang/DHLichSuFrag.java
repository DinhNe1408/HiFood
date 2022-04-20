package com.example.bctn.fragment.DonHang;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.RecyclerAdapter.DonHang1Adap;
import com.example.bctn.domain.donhang_dhfrag;
import com.example.bctn.domain.key;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class DHLichSuFrag extends Fragment {

    private View mView;
    private TextView txtV_ChonNgay;
    private RecyclerView recV_DHLichSu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_dh_lich_su, container, false);
        AnhXa();

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.add(Calendar.MONTH, -1);
        Long befor = calendar.getTimeInMillis();
        calendar.clear();


        Long today = MaterialDatePicker.todayInUtcMilliseconds();

        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setCalendarConstraints(new CalendarConstraints.Builder().setEnd(today).build());
        builder.setTitleText("Chọn ngày");
        builder.setSelection(new Pair<>(befor, today));

        final MaterialDatePicker materialDatePicker = builder.build();

        //txtV_ChonNgay.setText(materialDatePicker.getHeaderText());
        txtV_ChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getParentFragmentManager(), "ChonNgay");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                txtV_ChonNgay.setText(materialDatePicker.getHeaderText());
            }
        });

        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        setData();
    }

    private void setData() {
        List<donhang_dhfrag> donhang_dhfrags = MyAppication.mDao.DHHoanThanhFrag(MyAppication.mTaiKhoan.getIdTK(), key.key_dh_HoanThanh);
        DonHang1Adap donHang1Adap = new DonHang1Adap(mView.getContext(), donhang_dhfrags);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mView.getContext(), DividerItemDecoration.VERTICAL);

        recV_DHLichSu.addItemDecoration(itemDecoration);
        recV_DHLichSu.setLayoutManager(linearLayoutManager);
        recV_DHLichSu.setAdapter(donHang1Adap);
    }

    private void AnhXa() {
        txtV_ChonNgay = mView.findViewById(R.id.txtV_ChonNgay);
        recV_DHLichSu = mView.findViewById(R.id.recV_DHLichSu);
    }
}
