package com.example.bctn.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.bctn.MyAppication;
import com.example.bctn.R;
import com.example.bctn.activity.DangKy2Act;
import com.example.bctn.domain.key;
import com.google.android.material.textfield.TextInputEditText;

public class QuenMatKhau1Act extends AppCompatActivity {

    private TextInputEditText editT_SDT_qmk1;
    private Button btn_DK2_qmk1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau1);
        AnhXa();
        SuKien();
    }

    private void SuKien() {
        btn_DK2_qmk1.setOnClickListener(view -> {
            if (editT_SDT_qmk1.getText() != null || editT_SDT_qmk1.getText().length() > 0) {
                String sdt = editT_SDT_qmk1.getText().toString();
                if (key.isSDT(sdt)) {
                    if (MyAppication.mDao.isExistTK(sdt)) {
                        Intent intent = new Intent(this, QuenMatKhau2Act.class);
                        intent.putExtra(key.key_SDT, sdt);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Số điện thoại chưa được đăng ký ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Vui lòng nhập đúng định dạng Số điện thoại ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Vui lòng điền số điện thoại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AnhXa() {
        btn_DK2_qmk1 = findViewById(R.id.btn_DK2_qmk1);
        editT_SDT_qmk1 = findViewById(R.id.editT_SDT_qmk1);
    }
}