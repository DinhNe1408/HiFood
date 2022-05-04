package com.example.bctn.activity.quanan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.domain.key;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;

public class ThongKeQA extends AppCompatActivity {

    private TextView txtV_ChonNgay_tkqa, txtV_TongDonHang, txtV_DoanhThu;
    private ImageButton imgB_ThongKe_tkqa;
    private PieChart pie_ThongKe_tkqa;
    DatePickerDialog.OnDateSetListener setListener;
    int IDQA;
    String mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_qa);
        AnhXa();

        Intent intent = getIntent();
        IDQA = intent.getIntExtra(key.key_IDQA, -1);
        if (IDQA == -1)
            return;

        SuKien();
        setData();
    }

    @SuppressLint("SetTextI18n")
    private void setData() {
        ArrayList<PieEntry> pieEntries = MyAppication.mDao.ThongKeQA(IDQA, mDay);

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Thống kê");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16);

        PieData pieData = new PieData(pieDataSet);
        pie_ThongKe_tkqa.setData(pieData);
        pie_ThongKe_tkqa.getDescription().setEnabled(false);
        pie_ThongKe_tkqa.setCenterText("Sản phẩm đã bán");
        pie_ThongKe_tkqa.animate();
        pie_ThongKe_tkqa.invalidate();
        pie_ThongKe_tkqa.refreshDrawableState();

        txtV_TongDonHang.setText("Tổng đơn hàng: " + MyAppication.mDao.TongDonHangQA(IDQA, mDay));
        txtV_DoanhThu.setText("Tổng doanh thu: " + key.Dou2Money(MyAppication.mDao.TongDoanhThuQA(IDQA, mDay)));
    }

    private void SuKien() {

        Calendar today = Calendar.getInstance();
        final int year = today.get(Calendar.YEAR);
        final int month = today.get(Calendar.MONTH);
        final int day = today.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtV_ChonNgay_tkqa.setText(simpleDateFormat.format(today.getTime()));
        mDay = year + " " + key.addZero(month + 1) + " " + key.addZero(day);

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 += 1;
                String date = i2 + "/" + i1 + "/" + i;

                mDay = i + " " + key.addZero(i1) + " " + key.addZero(i2);
                txtV_ChonNgay_tkqa.setText(date);
            }
        };

        txtV_ChonNgay_tkqa.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    android.R.style.Theme_Holo_Dialog_MinWidth, setListener, year, month, day);

            datePickerDialog.getDatePicker().setMaxDate(today.getTime().getTime());
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });

        imgB_ThongKe_tkqa.setOnClickListener(view -> {
            setData();
        });
    }

    private void AnhXa() {
        txtV_ChonNgay_tkqa = findViewById(R.id.txtV_ChonNgay_tkqa);
        imgB_ThongKe_tkqa = findViewById(R.id.imgB_ThongKe_tkqa);
        pie_ThongKe_tkqa = findViewById(R.id.pie_ThongKe_tkqa);
        txtV_TongDonHang = findViewById(R.id.txtV_TongDonHang);
        txtV_DoanhThu = findViewById(R.id.txtV_DoanhThu);
    }
}