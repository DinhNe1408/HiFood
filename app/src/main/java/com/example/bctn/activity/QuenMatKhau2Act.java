package com.example.bctn.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.domain.key;

public class QuenMatKhau2Act extends AppCompatActivity {

    private Button btn_QMK_qmk2;
    private TextView editT_MK_qmk2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau2);
        AnhXa();
        SuKien();
    }

    private void SuKien() {
        btn_QMK_qmk2.setOnClickListener(view -> {
            if (editT_MK_qmk2.getText() != null || editT_MK_qmk2.getText().length() > 0) {
                String mk = editT_MK_qmk2.getText().toString();
                if (key.isMk(mk)) {
                    if (MyAppication.mDao.isExistTK(mk)) {
                        Toast.makeText(this, "Mật khẩu đã được cập nhật", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Số điện thoại chưa được đăng ký ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Mật khẩu phải dài hơn 8 ký tự, có chữ in hoa, chữ thường và số", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(this, "Vui lòng điền mật khẩu mới", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void AnhXa() {
        editT_MK_qmk2 = findViewById(R.id.editT_MK_qmk2);
        btn_QMK_qmk2 = findViewById(R.id.btn_QMK_qmk2);
    }
}