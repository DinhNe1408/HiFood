package com.example.bctn.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.adapter.RecyclerAdapter.DsMA_tt_Adap;
import com.example.bctn.adapter.Menu_op1_Adap;
import com.example.bctn.domain.ctdh;
import com.example.bctn.domain.donhang;
import com.example.bctn.domain.key;
import com.example.bctn.domain.menu_option;
import com.example.bctn.fragment.QuanAn.QAThucDonFrag;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ThanhToanAct extends AppCompatActivity {

    private RecyclerView recV_DsMA_tt, recV_Menu_tt;
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
        int IDDH = intent.getIntExtra(key.key_IDDH, -1);
        if (IDDH == -1)
            return;

        mDonhang = MyAppication.mDao.DH(IDDH, key.key_dh_Nhap);

        mDonhang.setPhiVC(10000.0);
        mDonhang.setTongDH(mDonhang.getTongTienMAMap() + mDonhang.getPhiVC());
        mDonhang.setTenNN(MyAppication.mTaiKhoan.getTenTK());
        mDonhang.setSDTNN(MyAppication.mTaiKhoan.getSdtTK());
        mDonhang.setVitriDH("Số 6 Trần Văn Ơn, Phú Hòa, Thủ Dầu Một,Bình Dương");

        setData();

        btn_DatHang_tt.setOnClickListener(view -> {

            MyAppication.mDao.CapNhatDH(IDDH, mDonhang.getTenNN(), mDonhang.getSDTNN(),
                    mDonhang.getTongTienMAMap(), mDonhang.getPhiVC(), mDonhang.getTienGiam(), mDonhang.getTongDH(),
                    mDonhang.getVitriDH(), Calendar.getInstance().getTime().getTime(), 10L, key.key_dh_DangGiao);

            QAThucDonFrag.mDonhang.newCtdhMap();
            Toast.makeText(this, "Đặt hàng thành công!!!", Toast.LENGTH_SHORT).show();
            Intent mIntent = new Intent(ThanhToanAct.this, TheoDoiDonHang.class);
            mIntent.putExtra(key.key_IDDH, IDDH);
            mIntent.putExtra(key.key_TTGiao, key.key_dh_DangGiao);
            startActivity(mIntent);
        });

        txtV_DoiDiaChiNhan_tt.setOnClickListener(view -> {
            Toast.makeText(this, "DD", Toast.LENGTH_SHORT).show();
            openDialog();
        });
    }

    @SuppressLint("SetTextI18n")
    private void setThongTinNN() {
        txtV_TenTK_tt.setText(mDonhang.getTenNN() + " - " + mDonhang.getSDTNN());
        txtV_DiaChiNhan_tt.setText(mDonhang.getVitriDH());
    }


    private void setData() {

        tool3_ThanhToan.setNavigationOnClickListener(view -> onBackPressed());
        txtV_toolbar_title = tool3_ThanhToan.findViewById(R.id.txtV_toolbar_title);
        txtV_toolbar_title.setText("Thanh toán");

        setThongTinNN();
        tongDH_tt.setText(key.Dou2Money(mDonhang.getTongTienMAMap()));
        phiVC_tt.setText(key.Dou2Money(mDonhang.getPhiVC()));
        tongTien_tt.setText(key.Dou2Money(mDonhang.getTongDH()));

        List<ctdh> ctdhList = new ArrayList<ctdh>(mDonhang.getCthdMap().values());
        DsMA_tt_Adap dsMA_tt_adap = new DsMA_tt_Adap(this, ctdhList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recV_DsMA_tt.setLayoutManager(linearLayoutManager);
        recV_DsMA_tt.setAdapter(dsMA_tt_adap);

        List<menu_option> mListMenuOp = new ArrayList<>();
        mListMenuOp.add(new menu_option(key.key_KHUYENMAI, R.drawable.ic_round_paid_24, R.color.hifood5, "Chọn khuyến mãi, voucher"));
        mListMenuOp.add(new menu_option(key.key_PHUONGTHUCTHANHTOAN, R.drawable.ic_round_credit_card_24, R.color.hifood5, "Chọn phương thức thanh toán"));

        Menu_op1_Adap menu_op1_adap = new Menu_op1_Adap(this, mListMenuOp);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recV_Menu_tt.setLayoutManager(linearLayoutManager1);
        recV_Menu_tt.setAdapter(menu_op1_adap);
    }

    private void openDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_thanh_toan);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setGravity(Gravity.CENTER);

        EditText txtV_TenNN_dialog_tt = dialog.findViewById(R.id.txtV_TenNN_dialog_tt);
        EditText txtV_SDT_dialog_tt = dialog.findViewById(R.id.txtV_SDT_dialog_tt);
        EditText txtV_ViTri_dialog_tt = dialog.findViewById(R.id.txtV_ViTri_dialog_tt);
        ImageButton imgB_Dong_dialog_tt = dialog.findViewById(R.id.imgB_Dong_dialog_tt);
        Button btn_LuuThongTin_dialog_tt = dialog.findViewById(R.id.btn_LuuThongTin_dialog_tt);

        txtV_TenNN_dialog_tt.setText(mDonhang.getTenNN());
        txtV_SDT_dialog_tt.setText(mDonhang.getSDTNN());
        txtV_ViTri_dialog_tt.setText(mDonhang.getVitriDH());

        imgB_Dong_dialog_tt.setOnClickListener(view -> {
            dialog.dismiss();
        });

        btn_LuuThongTin_dialog_tt.setOnClickListener(view -> {
            if (txtV_TenNN_dialog_tt.getText().length() != 0 && txtV_SDT_dialog_tt.getText().length() != 0 && txtV_ViTri_dialog_tt.getText().length() != 0) {

                if (key.isSDT(txtV_SDT_dialog_tt.getText().toString())) {
                    mDonhang.setTenNN(txtV_TenNN_dialog_tt.getText().toString());
                    mDonhang.setSDTNN(txtV_SDT_dialog_tt.getText().toString());
                    mDonhang.setVitriDH(txtV_ViTri_dialog_tt.getText().toString());
                    setThongTinNN();
                    Toast.makeText(this, "Thông tin người nhận đã được cập nhật", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Vui lòng nhập đúng định dạng Số điện thoại ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Hãy điền đủ các trường", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    private void AnhXa() {
        txtV_TenTK_tt = findViewById(R.id.txtV_TenTK_tt);
        txtV_DiaChiNhan_tt = findViewById(R.id.txtV_DiaChiNhan_tt);
        tongDH_tt = findViewById(R.id.tongDH_tt);
        phiVC_tt = findViewById(R.id.phiVC_tt);
        tongTien_tt = findViewById(R.id.tongTien_tt);
        txtV_DoiDiaChiNhan_tt = findViewById(R.id.txtV_DoiDiaChiNhan_tt);

        btn_DatHang_tt = findViewById(R.id.btn_DatHang_tt);
        recV_DsMA_tt = findViewById(R.id.recV_DsMA_tt);
        tool3_ThanhToan = findViewById(R.id.tool3_ThanhToan);
        recV_Menu_tt = findViewById(R.id.recV_Menu_tt);
    }
}