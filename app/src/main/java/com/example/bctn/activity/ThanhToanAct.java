package com.example.bctn.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.RecyclerAdapter.DsMA_tt_Adap;
import com.example.bctn.adapter.Menu_op1_Adap;
import com.example.bctn.domain.donhang;
import com.example.bctn.domain.key;
import com.example.bctn.domain.menu_option;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThanhToanAct extends AppCompatActivity {

    private RecyclerView recV_DsMA_tt, recV_Menu_tt;
    private List<menu_option> mListMenuOp;
    private DsMA_tt_Adap dsMA_tt_adap;
    private Toolbar tool3_ThanhToan;
    private Button btn_DatHang_tt;
    private TextView txtV_TenTK_tt, txtV_DiaChiNhan_tt, txtV_DoiDiaChiNhan_tt, tongDH_tt, phiVC_tt, tongTien_tt;
    private donhang mDonhang;
    TextView txtV_toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        AnhXa();

        Intent intent = getIntent();
        int IDQA = intent.getIntExtra(key.key_IDQA, -1);
        if (IDQA == -1)
            return;
        //List<ctdh> ctdhList = MyAppication.mDao.DHDonNhap(MyAppication.mTaiKhoan,IDQA)
        mDonhang = MyAppication.mDao.DHDonNhap(MyAppication.mTaiKhoan.getIdTK(),IDQA);
        mDonhang.setPhiVC(10000);
        mDonhang.setTongDH(mDonhang.getTongTienMA() + mDonhang.getPhiVC());
        mDonhang.setTenNN(MyAppication.mTaiKhoan.getTenTK());
        mDonhang.setSDTNN(MyAppication.mTaiKhoan.getSdtTK());
        mDonhang.setVitriDH("Số 6 Trần Văn Ơn, Phú Hòa, Thủ Dầu Một,Bình Dương");

        tool3_ThanhToan.setNavigationOnClickListener(view -> onBackPressed());
        txtV_toolbar_title = tool3_ThanhToan.findViewById(R.id.txtV_toolbar_title);
        txtV_toolbar_title.setText("Thanh toán");

        tongDH_tt.setText(key.Dou2Money(mDonhang.getTongTienMA()));
        phiVC_tt.setText(key.Dou2Money(mDonhang.getPhiVC()));
        tongTien_tt.setText(key.Dou2Money(mDonhang.getTongDH()));

        dsMA_tt_adap = new DsMA_tt_Adap(this, mDonhang.getCtdhList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recV_DsMA_tt.setLayoutManager(linearLayoutManager);
        recV_DsMA_tt.setAdapter(dsMA_tt_adap);


        mListMenuOp = new ArrayList<>();
        mListMenuOp.add(new menu_option(key.key_KHUYENMAI, R.drawable.ic_baseline_cancel_24, R.color.hifood5, "Chọn khuyến mãi, voucher"));
        mListMenuOp.add(new menu_option(key.key_PHUONGTHUCTHANHTOAN, R.drawable.ic_baseline_cancel_24, R.color.hifood5, "Chọn phương thức thanh toán"));

        Menu_op1_Adap menu_op1_adap = new Menu_op1_Adap(this, mListMenuOp);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recV_Menu_tt.setLayoutManager(linearLayoutManager1);
        recV_Menu_tt.setAdapter(menu_op1_adap);
        btn_DatHang_tt.setOnClickListener(view -> {
            MyAppication.mDao.CapNhatDH(MyAppication.mDao.isExistDonNhap(MyAppication.mTaiKhoan.getIdTK(),IDQA, key.key_dh_Nhap),
                    mDonhang.getTenNN(), mDonhang.getSDTNN(), mDonhang.getPhiVC(), mDonhang.getTienGiam(),mDonhang.getTongDH(),
                    mDonhang.getVitriDH(), Calendar.getInstance().getTime().getTime(),10L,key.key_dh_DangGiao);

            Toast.makeText(this, "Đặt hàng thành công!!!", Toast.LENGTH_SHORT).show();
            Intent mIntent = new Intent(ThanhToanAct.this, TheoDoiDonHang.class);
            startActivity(mIntent);
        });
    }

    private void AnhXa() {
        txtV_TenTK_tt = findViewById(R.id.txtV_TenTK_tt);
        txtV_DiaChiNhan_tt = findViewById(R.id.txtV_DiaChiNhan_tt);
        tongDH_tt = findViewById(R.id.tongDH_tt);
        phiVC_tt = findViewById(R.id.phiVC_tt);
        tongTien_tt = findViewById(R.id.tongTien_tt);

        btn_DatHang_tt = findViewById(R.id.btn_DatHang_tt);
        recV_DsMA_tt = findViewById(R.id.recV_DsMA_tt);
        tool3_ThanhToan = findViewById(R.id.tool3_ThanhToan);
        recV_Menu_tt = findViewById(R.id.recV_Menu_tt);
    }
}