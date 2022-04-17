package com.example.bctn.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bctn.DataLocalManager;
import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.domain.key;
import com.google.android.material.textfield.TextInputEditText;

public class DoiMatKhau extends AppCompatActivity {

    private Toolbar tool3_DoiMatKhau;
    private TextInputEditText editT_MatKhauCu_dmk, editT_MatKhauMoi_dmk, editT_XacNhanMatKhauMoi_dmk;
    private Button btn_XacNhan_ttnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        AnhXa();

        tool3_DoiMatKhau.setNavigationOnClickListener(view -> onBackPressed());
        TextView txtV_toolbar_title = tool3_DoiMatKhau.findViewById(R.id.txtV_toolbar_title);
        txtV_toolbar_title.setText("Đổi mật khẩu");


        btn_XacNhan_ttnd.setOnClickListener(view -> {
            if (editT_MatKhauCu_dmk.getText() != null && editT_MatKhauMoi_dmk.getText() != null && editT_XacNhanMatKhauMoi_dmk.getText() != null &&
                    editT_MatKhauCu_dmk.getText().length() != 0 && editT_MatKhauMoi_dmk.getText().length() != 0 && editT_XacNhanMatKhauMoi_dmk.getText().length() != 0) {

                String MkCu = editT_MatKhauCu_dmk.getText().toString().trim();
                String MkMoi = editT_MatKhauMoi_dmk.getText().toString().trim();
                String XNMkMoi = editT_XacNhanMatKhauMoi_dmk.getText().toString().trim();
                if (MyAppication.mTaiKhoan.getMkTK().equals(MkCu)){
                    if (key.isMk(MkMoi)){
                        if (MkMoi.equals(XNMkMoi)){
                            MyAppication.mDao.CapNhatMK(MyAppication.mTaiKhoan.getIdTK(), MkMoi);
                            MyAppication.mTaiKhoan.setMkTK(MkMoi);

                            DataLocalManager.setTaiKhoan(MyAppication.mTaiKhoan.getSdtTK(), MyAppication.mTaiKhoan.getMkTK());
                            Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Mật khẩu mới không trùng", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Mật khẩu phải dài hơn 8 ký tự, có chữ in hoa, chữ thường và số", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(this, "Mật khẩu cũ không trùng khớp", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Hãy điền đủ các trường", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void AnhXa() {
        tool3_DoiMatKhau = findViewById(R.id.tool3_DoiMatKhau);

        editT_MatKhauCu_dmk = findViewById(R.id.editT_MatKhauCu_dmk);
        editT_MatKhauMoi_dmk = findViewById(R.id.editT_MatKhauMoi_dmk);
        editT_XacNhanMatKhauMoi_dmk = findViewById(R.id.editT_XacNhanMatKhauMoi_dmk);

        btn_XacNhan_ttnd = findViewById(R.id.btn_XacNhan_ttnd);

    }
}