package com.example.bctn.activity.quanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.Menu_op2_Adap;
import com.example.bctn.domain.key;
import com.example.bctn.domain.menu_option;
import com.example.bctn.domain.taikhoan;

import java.util.ArrayList;
import java.util.List;

public class QuanAn extends AppCompatActivity {

    private Toolbar tool3_QuanAn;
    private RecyclerView recV_QuanAnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_an2);

        AnhXa();
        tool3_QuanAn.setNavigationOnClickListener(view -> onBackPressed());
        TextView txtV_toolbar_title = tool3_QuanAn.findViewById(R.id.txtV_toolbar_title);
        txtV_toolbar_title.setText("Quán ăn");
        if (MyAppication.mTaiKhoan.getIDQA() == -1)
            return;

        List<menu_option> menu_optionList = new ArrayList<>();
        menu_optionList.add(new menu_option(key.key_ThongTinQA, R.drawable.ic_round_store_mall_directory_24, 0, "Thông tin quán ăn"));
        menu_optionList.add(new menu_option(key.key_QLMonAn, R.drawable.ic_round_restaurant_24, 0, "Quản lý món ăn"));
        menu_optionList.add(new menu_option(key.key_DonHangQA, R.drawable.ic_round_receipt_long_24, 0, "Đơn hàng"));
        menu_optionList.add(new menu_option(key.key_ThongKeQA, R.drawable.ic_round_query_stats_24, 0, "Thống kê"));
        menu_optionList.add(new menu_option(key.key_DANGXUAT, R.drawable.ic_round_logout_24, 0, "Đăng xuất"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        Menu_op2_Adap menu_op2_adap = new Menu_op2_Adap(this, menu_optionList);
        recV_QuanAnMenu.setAdapter(menu_op2_adap);
        recV_QuanAnMenu.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(QuanAn.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Thông báo")
                .setMessage("Bạn có chắc muốn đăng xuất?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyAppication.mTaiKhoan = new taikhoan();
                        finish();
                    }
                })
                .setNegativeButton("Không", null)
                .show();
    }

    private void AnhXa() {
        recV_QuanAnMenu = findViewById(R.id.recV_QuanAnMenu);
        tool3_QuanAn = findViewById(R.id.tool3_QuanAn);
    }
}