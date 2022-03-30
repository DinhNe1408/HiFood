package com.example.bctn.activity;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bctn.R;
import com.example.bctn.adapter.DsMA_tt_Adap;
import com.example.bctn.adapter.Menu_op1_Adap;
import com.example.bctn.domain.key;
import com.example.bctn.domain.menu_option;
import com.example.bctn.domain.monan;

import java.util.ArrayList;
import java.util.List;

public class ThanhToanAct extends AppCompatActivity {

    private RecyclerView recV_DsMA_tt, recV_Menu_tt;
    private List<monan> mListMA;
    private List<menu_option> mListMenuOp;
    private DsMA_tt_Adap dsMA_tt_adap;
    private Toolbar tool3_ThanhToan;
    TextView txtV_toolbar_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        AnhXa();

        //tool3_ThanhToan.setTitle("Thanh toán");
        tool3_ThanhToan.setNavigationOnClickListener(view -> onBackPressed());
        txtV_toolbar_title = tool3_ThanhToan.findViewById(R.id.txtV_toolbar_title);
        txtV_toolbar_title.setText("Thanh toán");

        mListMA = new ArrayList<>();
        mListMA.add(new monan(R.drawable.w42419,"Banh"));
        mListMA.add(new monan(R.drawable.w42419,"Banh"));
        mListMA.add(new monan(R.drawable.w42419,"Banh"));
        dsMA_tt_adap = new DsMA_tt_Adap(this,mListMA);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recV_DsMA_tt.setLayoutManager(linearLayoutManager);
        recV_DsMA_tt.setAdapter(dsMA_tt_adap);


        mListMenuOp =  new ArrayList<>();
        mListMenuOp.add(new menu_option(key.key_KHUYENMAI,R.drawable.ic_baseline_cancel_24,R.color.hifood5,"Chọn khuyến mãi, voucher"));
        mListMenuOp.add(new menu_option(key.key_PHUONGTHUCTHANHTOAN,R.drawable.ic_baseline_cancel_24,R.color.hifood5,"Chọn phương thức thanh toán"));

        Menu_op1_Adap menu_op1_adap = new Menu_op1_Adap(this, mListMenuOp);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recV_Menu_tt.setLayoutManager(linearLayoutManager1);
        recV_Menu_tt.setAdapter(menu_op1_adap);
    }

    private void AnhXa() {
        recV_DsMA_tt = findViewById(R.id.recV_DsMA_tt);
        tool3_ThanhToan = findViewById(R.id.tool3_ThanhToan);
        recV_Menu_tt = findViewById(R.id.recV_Menu_tt);
    }
}