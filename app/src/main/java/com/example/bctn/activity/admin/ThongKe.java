package com.example.bctn.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.domain.key;
import com.example.bctn.domain.thongke;
import com.example.bctn.fragment.QuanAn.QAThucDonFrag;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThongKe extends AppCompatActivity {

    private ImageButton imgB_ThongKe_tkqtv;
    private TextView txtV_ChonNgay_tkqtv;
    private BarChart bar_ThongKe_tkqtv;
    String mDay;
    int month, year;

    ArrayList<String> lableName;
    ArrayList<thongke> thongkeArrayList;
    ArrayList<BarEntry> barEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        AnhXa();
        Calendar today = Calendar.getInstance();
        year = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH) + 1;

        txtV_ChonNgay_tkqtv.setOnClickListener(view -> {
            OpenDialog();
        });

        imgB_ThongKe_tkqtv.setOnClickListener(view -> {
            setData();
        });
        setData();
    }

    private void setData() {
        barEntries = new ArrayList<>();
        lableName = new ArrayList<>();
        thongkeArrayList = MyAppication.mDao.ThongKe(mDay);

        for (int i = 0; i < thongkeArrayList.size(); i++) {
            barEntries.add(new BarEntry( i, (float) thongkeArrayList.get(i).getTien()));
            lableName.add(thongkeArrayList.get(i).getTen());
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Doanh thu");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        bar_ThongKe_tkqtv.setFitBars(true);
        bar_ThongKe_tkqtv.setData(barData);
        bar_ThongKe_tkqtv.getDescription().setText("Ví dụ bar");

        XAxis xAxis = bar_ThongKe_tkqtv.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(lableName));

        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(lableName.size());
        xAxis.setLabelRotationAngle(270);

        bar_ThongKe_tkqtv.animateY(2000);
        bar_ThongKe_tkqtv.invalidate();
    }


    private void OpenDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_monthyear);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setGravity(Gravity.CENTER);

        NumberPicker numpick_month = dialog.findViewById(R.id.numpick_month);
        NumberPicker numpick_year = dialog.findViewById(R.id.numpick_year);
        TextView txtV_Huy = dialog.findViewById(R.id.txtV_Huy);
        TextView txtV_OK = dialog.findViewById(R.id.txtV_OK);

        numpick_month.setMinValue(1);
        numpick_month.setMaxValue(12);
        numpick_year.setMinValue(2000);
        numpick_year.setMaxValue(2050);

        numpick_month.setValue(month);
        numpick_year.setValue(year);
        numpick_month.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                if (numberPicker.getMaxValue() == i && numberPicker.getMinValue() == i1) {
                    int year = numpick_year.getValue();
                    numpick_year.setValue(year + 1);
                }
                if (numberPicker.getMaxValue() == i1 && numberPicker.getMinValue() == i) {
                    int year = numpick_year.getValue();
                    numpick_year.setValue(year - 1);
                }
            }
        });
        numpick_month.getValue();

        txtV_OK.setOnClickListener(view -> {
            month = numpick_month.getValue();
            year = numpick_year.getValue();
            mDay = numpick_year.getValue() + " " + key.addZero(numpick_month.getValue());
            txtV_ChonNgay_tkqtv.setText(numpick_month.getValue() + "/" + numpick_year.getValue());
            dialog.dismiss();
        });

        txtV_Huy.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.show();
    }


    private void AnhXa() {
        imgB_ThongKe_tkqtv = findViewById(R.id.imgB_ThongKe_tkqtv);
        txtV_ChonNgay_tkqtv = findViewById(R.id.txtV_ChonNgay_tkqtv);
        bar_ThongKe_tkqtv = findViewById(R.id.bar_ThongKe_tkqtv);
    }
}