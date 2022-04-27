package com.example.bctn.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;

import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.example.bctn.DAO;
import com.example.bctn.DataLocalManager;
import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.activity.admin.QuanTri;
import com.example.bctn.activity.quanan.QuanAn;
import com.example.bctn.domain.taikhoan;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class DangNhapAct extends AppCompatActivity {
    private TextInputEditText editT_SDT_dn, editT_MK_dn;
    //private TextInputLayout txtL_SDT_dn, txtL_MK_dn;
    private TextView txtV_TaoTK_dn;
    private Button btn_DN_dn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        AnhXa();

        btn_DN_dn.setOnClickListener(view -> {
            if (editT_SDT_dn.getText() != null && editT_MK_dn.getText() != null
                    && editT_SDT_dn.length() > 0 && editT_MK_dn.length() > 0) {

                String sdt = editT_SDT_dn.getText().toString().trim();
                String mk = editT_MK_dn.getText().toString().trim();

                taikhoan mTaikhoan = MyAppication.mDao.GetTK(sdt, mk);

                if (mTaikhoan != null) {
                    if (!mTaikhoan.isKhoa()) {
                        MyAppication.mTaiKhoan = mTaikhoan;
                        MyAppication.mTaiKhoan.setCurVitri(MyAppication.curViTri);

                        if (mTaikhoan.getRole().equals("user")) {
                            Intent mIntent = new Intent(DangNhapAct.this, TrangChuAct.class);
                            startActivity(mIntent);
                            DataLocalManager.setTaiKhoan(mTaikhoan.getSdtTK(), mTaikhoan.getMkTK());
                            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                        } else if (mTaikhoan.getRole().equals("admin")) {
                            Intent mIntent = new Intent(DangNhapAct.this, QuanTri.class);
                            startActivity(mIntent);

                        } else if (mTaikhoan.getRole().equals("diner")){
                            if(MyAppication.mDao.isQAKhoa(mTaikhoan.getIdTK()) == 0){
                                MyAppication.mTaiKhoan.setIDQA(MyAppication.mDao.TKQA(mTaikhoan.getIdTK()));
                                Intent mIntent = new Intent(DangNhapAct.this, QuanAn.class);
                                startActivity(mIntent);
                            } else {
                                Toast.makeText(this, "Quán ăn đã bị khóa", Toast.LENGTH_SHORT).show();
                            }

                        }

                    } else {
                        Toast.makeText(this, "Tài khoản của bạn đã bị khóa.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "Vui lòng kiểm tra lại Số điện thoại hoặc Mật khẩu", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Vui lòng điền đầy đủ các trường", Toast.LENGTH_SHORT).show();
            }
        });

        txtV_TaoTK_dn.setOnClickListener(view -> {
            Intent intent = new Intent(DangNhapAct.this, DangKy2Act.class);
            startActivity(intent);
        });

    }

    private void AnhXa() {
        btn_DN_dn = findViewById(R.id.btn_DN_dn);
        txtV_TaoTK_dn = findViewById(R.id.txtV_TaoTK_dn);

        editT_SDT_dn = findViewById(R.id.editT_SDT_dn);
        editT_MK_dn = findViewById(R.id.editT_MK_dn);

        //txtL_SDT_dn = findViewById(R.id.txtL_SDT_dn);
        //txtL_MK_dn = findViewById(R.id.txtL_MK_dn);
    }
}